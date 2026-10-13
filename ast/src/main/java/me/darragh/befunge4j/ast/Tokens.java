package me.darragh.befunge4j.ast;

import lombok.experimental.UtilityClass;
import me.darragh.befunge4j.ast.type.*;
import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 1.0.0
 */
@UtilityClass
public final class Tokens {
    //region Math
    private static final @NotNull Token MATH_ADDITION = new Token(TokenType.MATH_ADDITION);
    private static final @NotNull Token MATH_SUBTRACTION = new Token(TokenType.MATH_SUBTRACTION);
    private static final @NotNull Token MATH_MULTIPLICATION = new Token(TokenType.MATH_MULTIPLICATION);
    private static final @NotNull Token MATH_INT_DIVISION = new Token(TokenType.MATH_INT_DIVISION);
    private static final @NotNull Token MATH_MODULO = new Token(TokenType.MATH_MODULO);
    //endregion
    //region Logic
    private static final @NotNull Token LOGICAL_NOT = new Token(TokenType.LOGICAL_NOT);
    private static final @NotNull Token LOGICAL_GREATER_THAN = new Token(TokenType.LOGICAL_GREATER_THAN);
    //endregion
    //region Program Counter
    private static final @NotNull Token PC_CHANGE_DIRECTION_LEFT = new Token(TokenType.PC_CHANGE_DIRECTION_LEFT);
    private static final @NotNull Token PC_CHANGE_DIRECTION_RIGHT = new Token(TokenType.PC_CHANGE_DIRECTION_RIGHT);
    private static final @NotNull Token PC_CHANGE_DIRECTION_UP = new Token(TokenType.PC_CHANGE_DIRECTION_UP);
    private static final @NotNull Token PC_CHANGE_DIRECTION_DOWN = new Token(TokenType.PC_CHANGE_DIRECTION_DOWN);
    private static final @NotNull Token PC_CHANGE_DIRECTION_RANDOM = new Token(TokenType.PC_CHANGE_DIRECTION_RANDOM);
    private static final @NotNull Token PC_IF_HORIZONTAL = new Token(TokenType.PC_IF_HORIZONTAL);
    private static final @NotNull Token PC_IF_VERTICAL = new Token(TokenType.PC_IF_VERTICAL);
    private static final @NotNull Token PC_BRIDGE = new Token(TokenType.PC_BRIDGE);
    //region Complex
    private static final @NotNull Token PC_GET = new Token(TokenType.PC_GET);
    private static final @NotNull Token PC_PUT = new Token(TokenType.PC_PUT);
    //endregion
    //endregion
    //region Stack
    private static final @NotNull Token STACK_DUPLICATE = new Token(TokenType.STACK_DUPLICATE);
    private static final @NotNull Token STACK_SWAP = new Token(TokenType.STACK_SWAP);
    private static final @NotNull Token STACK_POP_DISCARD = new Token(TokenType.STACK_POP_DISCARD);
    private static final @NotNull Token STACK_POP_OUTPUT_INT = new Token(TokenType.STACK_POP_OUTPUT_INT);
    private static final @NotNull Token STACK_POP_OUTPUT_CHAR = new Token(TokenType.STACK_POP_OUTPUT_CHAR);
    //endregion
    //region Miscellaneous
    private static final @NotNull Token TOGGLE_STRING_MODE = new Token(TokenType.TOGGLE_STRING_MODE);
    private static final @NotNull Token INPUT_INT = new Token(TokenType.INPUT_INT);
    private static final @NotNull Token INPUT_CHAR = new Token(TokenType.INPUT_CHAR);
    private static final @NotNull Token END = new Token(TokenType.END);
    //endregion

    @UtilityClass
    public static final class Math {
        public static @NotNull Token addition() {
            return MATH_ADDITION;
        }

        public static @NotNull Token subtraction() {
            return MATH_SUBTRACTION;
        }

        public static @NotNull Token multiplication() {
            return MATH_MULTIPLICATION;
        }

        public static @NotNull Token integerDivision() {
            return MATH_INT_DIVISION;
        }

        public static @NotNull Token modulo() {
            return MATH_MODULO;
        }
    }

    @UtilityClass
    public static final class Logical {
        public static @NotNull Token not() {
            return LOGICAL_NOT;
        }

        public static @NotNull Token greaterThan() {
            return LOGICAL_GREATER_THAN;
        }
    }

    @SuppressWarnings("unused")
    @UtilityClass
    public static final class ProgramCounter {
        public static final byte MOVE_DIRECTION_UP = 0;
        public static final byte MOVE_DIRECTION_DOWN = 1;
        public static final byte MOVE_DIRECTION_LEFT = 2;
        public static final byte MOVE_DIRECTION_RIGHT = 3;
        public static final byte IF_DIRECTION_HORIZONTAL = 0;
        public static final byte IF_DIRECTION_VERTICAL = 1;

        public static @NotNull Token changeDirectionLeft() {
            return PC_CHANGE_DIRECTION_LEFT;
        }

        public static @NotNull Token changeDirectionRight() {
            return PC_CHANGE_DIRECTION_RIGHT;
        }

        public static @NotNull Token changeDirectionUp() {
            return PC_CHANGE_DIRECTION_UP;
        }

        public static @NotNull Token changeDirectionDown() {
            return PC_CHANGE_DIRECTION_DOWN;
        }

        public static @NotNull Token changeDirectionRandom() {
            return PC_CHANGE_DIRECTION_RANDOM;
        }

        public static @NotNull Token ifHorizontal() {
            return PC_IF_HORIZONTAL;
        }

        public static @NotNull Token ifVertical() {
            return PC_IF_VERTICAL;
        }

        public static @NotNull Token bridge() {
            return PC_BRIDGE;
        }

        public static @NotNull Token get() {
            return PC_GET;
        }

        public static @NotNull Token put() {
            return PC_PUT;
        }
    }

    @UtilityClass
    public static final class Stack {
        public static @NotNull Token duplicate() {
            return STACK_DUPLICATE;
        }

        public static @NotNull Token swap() {
            return STACK_SWAP;
        }

        public static @NotNull Token popDiscard() {
            return STACK_POP_DISCARD;
        }

        public static @NotNull Token popOutputInteger() {
            return STACK_POP_OUTPUT_INT;
        }

        public static @NotNull Token popOutputCharacter() {
            return STACK_POP_OUTPUT_CHAR;
        }
    }

    @UtilityClass
    public static final class Miscellaneous {
        public static @NotNull Token toggleStringMode() {
            return TOGGLE_STRING_MODE;
        }

        public static @NotNull Token inputInteger() {
            return INPUT_INT;
        }

        public static @NotNull Token inputCharacter() {
            return INPUT_CHAR;
        }

        public static @NotNull Token end() {
            return END;
        }

    }

    public static @NotNull ConstantIntegerToken constant(byte value) {
        return new ConstantIntegerToken(TokenType.CONSTANT, value);
    }

    public static @NotNull ConstantCharacterToken constant(char value) {
        return new ConstantCharacterToken(TokenType.CONSTANT, value);
    }
}
