package me.darragh.befunge4j.interpreter.type.java;

import me.darragh.befunge4j.ast.Token;
import me.darragh.befunge4j.ast.TokenType;
import me.darragh.befunge4j.interpreter.BefungeInterpreterConfiguration;
import me.darragh.befunge4j.interpreter.BefungeInterpreterEngine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.Queue;
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

        Queue<Byte> numberComponents = new LinkedList<>(); // used for building the number constant from a series of constant instructions

        Stack<Integer> stack = new Stack<>();

        boolean stringMode = false;

        Direction currentDirection = Direction.RIGHT;

        // it is easier to reconstruct the index from xy
        int currentX = 0, currentY = 0;

        boolean reachedEnd = false; // TODO: Cleaner solution?
        while (!reachedEnd) {
            // fetch the current token
            int currentIndex = currentY * width + currentX;
            Token currentToken = tokens[currentIndex];

            // update the current index
            //noinspection DuplicatedCode
            switch (currentDirection) {
                case LEFT -> currentX = (currentX - 1 + width) % width;
                case RIGHT -> currentX = (currentX + 1) % width;
                case UP -> currentY = (currentY - 1 + height) % height;
                case DOWN -> currentY = (currentY + 1) % height;
            }

            if (currentToken == null) continue;

            // handle string mode
            if (currentToken.getType() == TokenType.TOGGLE_STRING_MODE) {
                stringMode = !stringMode;
                continue;
            }

            if (stringMode) {
                stack.push((int) currentToken.getSource());
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
                    // currentX = currentX + (value == 0 ? 1 : -1) % width;
                    currentDirection = value == 0 ? Direction.RIGHT : Direction.LEFT;
                }
                case PC_IF_VERTICAL -> {
                    int value = pop(stack);
                    // currentY = currentY + (value == 0 ? 1 : -1) % height;
                    currentDirection = value == 0 ? Direction.DOWN : Direction.UP;
                }
                case PC_BRIDGE -> {
                    //noinspection DuplicatedCode
                    switch (currentDirection) {
                        case LEFT -> currentX = (currentX - 1 + width) % width;
                        case RIGHT -> currentX = (currentX + 1) % width;
                        case UP -> currentY = (currentY - 1 + height) % height;
                        case DOWN -> currentY = (currentY + 1) % height;
                    }
                }
                //region Complex
                case PC_GET -> {
                    int y = pop(stack), x = pop(stack);
                    if (x < 0 || x >= width || y < 0 || y >= height) {
                        stack.push(0);
                    } else {
                        Token token = tokens[y * width + x];
                        int value = token == null ? ' ' : token.getSource();
                        stack.push(value);
                    }
                }
                case PC_PUT -> {
                    int y = pop(stack), x = pop(stack), v = pop(stack);
                    if (x < 0 || x >= width || y < 0 || y >= height) {
                        throw new IllegalStateException("PUT out of bounds");
                    }
                    // TODO: Fix this - stale token type
                    Token token = tokens[y * width + x];
                    TokenType tokenType = token == null ? TokenType.CONSTANT : token.getType();
                    Token newToken = new Token(tokenType, (char) v);
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
                case TOGGLE_STRING_MODE -> stringMode = !stringMode;
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
        }
    }

    //region Helpers
    private static int pop(Stack<Integer> stack) {
        return stack.isEmpty() ? 0 : stack.pop();
    }
    //endregion

    private enum Direction {
        LEFT, RIGHT, UP, DOWN;

        private static Direction[] VALUES = values(); // avoid #values() each #chooseRandom()

        public static @NotNull Direction choose() {
            return VALUES[(int) (Math.random() * VALUES.length)];
        }
    }
}
