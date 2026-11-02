package me.darragh.befunge4j.interpreter.type.java;

import me.darragh.befunge4j.ast.Token;
import me.darragh.befunge4j.ast.TokenType;
import me.darragh.befunge4j.interpreter.BefungeInterpreterConfiguration;
import me.darragh.befunge4j.interpreter.BefungeInterpreterEngine;
import me.darragh.befunge4j.parser.type.java.JavaBefungeParserEngine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Stack;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public final class JavaBefungeInterpreterEngine extends BefungeInterpreterEngine {
    public JavaBefungeInterpreterEngine(@NotNull BefungeInterpreterConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void execute(@Nullable Token @NotNull [] tokens) {
        int width = configuration.width(), height = configuration.height(); // for ease of access

        Stack<Integer> stack = new Stack<>();

        boolean stringMode = false;

        Direction currentDirection = Direction.RIGHT;

        // it is easier to reconstruct the index from xy
        int[] position = new int[] { 0, 0 };

        boolean reachedEnd = false; // TODO: Cleaner solution?
        while (!reachedEnd) {
            // fetch the current token
            int currentIndex = position[1] * width + position[0];
            Token currentToken = tokens[currentIndex];

            // handle string mode
            if (stringMode) {
                if (currentToken != null && currentToken.getType() == TokenType.TOGGLE_STRING_MODE) { // end string mode
                    stringMode = false;
                } else {
                    stack.push(currentToken == null ? (int) ' ' : (int) currentToken.getSource()); // push char
                }
                step(currentDirection, position, width, height);
                continue;
            }

            if (currentToken == null) {
                step(currentDirection, position, width, height);
                continue;
            }

            if (currentToken.getType() == TokenType.TOGGLE_STRING_MODE) { // early enter string mode
                stringMode = true;
                step(currentDirection, position, width, height);
                continue;
            }

            // execute the token
            switch (currentToken.getType()) {
                //region Math
                // instead of writing
                // int a = stack.pop(), b = stack.pop();
                // stack.push(a + b);
                case MATH_ADDITION -> stack.push(pop(stack) + pop(stack)); // a + b
                case MATH_SUBTRACTION -> {
                    int a = pop(stack), b = pop(stack);
                    stack.push(b - a);
                }
                case MATH_MULTIPLICATION -> stack.push(pop(stack) * pop(stack)); // a * b
                case MATH_INT_DIVISION -> {
                    int a = pop(stack), b = pop(stack);
                    if (a == 0) {
                        stack.push(this.configuration.console().inInteger());
                    } else {
                        stack.push(b / a);
                    }
                }
                case MATH_MODULO -> {
                    int a = pop(stack), b = pop(stack);
                    stack.push(Math.floorMod(b, a));
                }
                //endregion
                //region Logic
                case LOGICAL_NOT -> {
                    int value = pop(stack);
                    stack.push(value == 0 ? 1 : 0);
                }
                case LOGICAL_GREATER_THAN -> {
                    int a = pop(stack), b = pop(stack);
                    stack.push(b > a ? 1 : 0);
                }
                //endregion
                //region Program Counter
                case PC_CHANGE_DIRECTION_LEFT -> currentDirection = Direction.LEFT;
                case PC_CHANGE_DIRECTION_RIGHT -> currentDirection = Direction.RIGHT;
                case PC_CHANGE_DIRECTION_UP -> currentDirection = Direction.UP;
                case PC_CHANGE_DIRECTION_DOWN -> currentDirection = Direction.DOWN;
                case PC_CHANGE_DIRECTION_RANDOM -> currentDirection = Direction.choose();
                case PC_IF_HORIZONTAL -> {
                    int value = pop(stack);
                    currentDirection = value == 0 ? Direction.RIGHT : Direction.LEFT;
                }
                case PC_IF_VERTICAL -> {
                    int value = pop(stack);
                    currentDirection = value == 0 ? Direction.DOWN : Direction.UP;
                }
                case PC_BRIDGE -> step(currentDirection, position, width, height);
                //region Complex
                case PC_GET -> {
                    int y = pop(stack), x = pop(stack);
                    if (x < 0 || x >= width || y < 0 || y >= height) {
                        stack.push(0);
                    } else {
                        Token token = tokens[y * width + x];
                        char ch = token == null ? ' ' : token.getSource();
                        stack.push(Character.isDigit(ch) ? ch - '0' : ch);
                    }
                }
                case PC_PUT -> {
                    int y = pop(stack), x = pop(stack), v = pop(stack);
                    if (x < 0 || x >= width || y < 0 || y >= height) {
                        throw new IllegalStateException("PUT out of bounds");
                    }
                    Token newToken = JavaBefungeParserEngine.tokenise(this.configuration.parserConfiguration().mappings(), (char) v);
                    tokens[y * width + x] = newToken;
                }
                //endregion
                //endregion
                //region Stack
                case STACK_DUPLICATE -> stack.push(stack.isEmpty() ? 0 : stack.peek());
                case STACK_SWAP -> {
                    int a = pop(stack), b = pop(stack);
                    stack.push(a);
                    stack.push(b);
                }
                case STACK_POP_DISCARD -> pop(stack);
                case STACK_POP_OUTPUT_INT -> this.configuration.console().outInteger(pop(stack));
                case STACK_POP_OUTPUT_CHAR -> this.configuration.console().outChar((char) pop(stack));
                //endregion
                //region Miscellaneous
                case INPUT_INT -> stack.push(this.configuration.console().inInteger());
                case INPUT_CHAR -> stack.push((int) this.configuration.console().inChar());
                case END -> reachedEnd = true;
                //endregion
                case CONSTANT -> {
                    char ch = currentToken.getSource();
                    if (Character.isDigit(ch)) {
                        stack.push(ch - '0');
                    }
                }
            }

            step(currentDirection, position, width, height);
        }
    }

    //region Helpers
    private static void step(Direction direction, int[] position, int width, int height) {
        switch (direction) {
            case LEFT -> position[0] = (position[0] - 1 + width) % width;
            case RIGHT -> position[0] = (position[0] + 1) % width;
            case UP -> position[1] = (position[1] - 1 + height) % height;
            case DOWN -> position[1] = (position[1] + 1) % height;
        }
    }

    private static int pop(Stack<Integer> stack) {
        return stack.isEmpty() ? 0 : stack.pop();
    }
    //endregion

    private enum Direction {
        LEFT, RIGHT, UP, DOWN;

        private static final Direction[] VALUES = values(); // avoid #values() each #choose()

        public static @NotNull Direction choose() {
            return VALUES[(int) (Math.random() * VALUES.length)];
        }
    }
}
