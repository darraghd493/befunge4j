package me.darragh.befunge4j.ast;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 1.0.0
 */
@UtilityClass
public final class Tokens {

    @UtilityClass
    public static final class Math {
        public static @NotNull Token addition(char source) {
            return new Token(TokenType.MATH_ADDITION, source);
        }

        public static @NotNull Token subtraction(char source) {
            return new Token(TokenType.MATH_SUBTRACTION, source);
        }

        public static @NotNull Token multiplication(char source) {
            return new Token(TokenType.MATH_MULTIPLICATION, source);
        }

        public static @NotNull Token integerDivision(char source) {
            return new Token(TokenType.MATH_INT_DIVISION, source);
        }

        public static @NotNull Token modulo(char source) {
            return new Token(TokenType.MATH_MODULO, source);
        }
    }

    @UtilityClass
    public static final class Logical {
        public static @NotNull Token not(char source) {
            return new Token(TokenType.LOGICAL_NOT, source);
        }

        public static @NotNull Token greaterThan(char source) {
            return new Token(TokenType.LOGICAL_GREATER_THAN, source);
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

        public static @NotNull Token changeDirectionLeft(char source) {
            return new Token(TokenType.PC_CHANGE_DIRECTION_LEFT, source);
        }

        public static @NotNull Token changeDirectionRight(char source) {
            return new Token(TokenType.PC_CHANGE_DIRECTION_RIGHT, source);
        }

        public static @NotNull Token changeDirectionUp(char source) {
            return new Token(TokenType.PC_CHANGE_DIRECTION_UP, source);
        }

        public static @NotNull Token changeDirectionDown(char source) {
            return new Token(TokenType.PC_CHANGE_DIRECTION_DOWN, source);
        }

        public static @NotNull Token changeDirectionRandom(char source) {
            return new Token(TokenType.PC_CHANGE_DIRECTION_RANDOM, source);
        }

        public static @NotNull Token ifHorizontal(char source) {
            return new Token(TokenType.PC_IF_HORIZONTAL, source);
        }

        public static @NotNull Token ifVertical(char source) {
            return new Token(TokenType.PC_IF_VERTICAL, source);
        }

        public static @NotNull Token bridge(char source) {
            return new Token(TokenType.PC_BRIDGE, source);
        }

        public static @NotNull Token get(char source) {
            return new Token(TokenType.PC_GET, source);
        }

        public static @NotNull Token put(char source) {
            return new Token(TokenType.PC_PUT, source);
        }
    }

    @UtilityClass
    public static final class Stack {
        public static @NotNull Token duplicate(char source) {
            return new Token(TokenType.STACK_DUPLICATE, source);
        }

        public static @NotNull Token swap(char source) {
            return new Token(TokenType.STACK_SWAP, source);
        }

        public static @NotNull Token popDiscard(char source) {
            return new Token(TokenType.STACK_POP_DISCARD, source);
        }

        public static @NotNull Token popOutputInteger(char source) {
            return new Token(TokenType.STACK_POP_OUTPUT_INT, source);
        }

        public static @NotNull Token popOutputCharacter(char source) {
            return new Token(TokenType.STACK_POP_OUTPUT_CHAR, source);
        }
    }

    @UtilityClass
    public static final class Miscellaneous {
        public static @NotNull Token toggleStringMode(char source) {
            return new Token(TokenType.TOGGLE_STRING_MODE, source);
        }

        public static @NotNull Token inputInteger(char source) {
            return new Token(TokenType.INPUT_INT, source);
        }

        public static @NotNull Token inputCharacter(char source) {
            return new Token(TokenType.INPUT_CHAR, source);
        }

        public static @NotNull Token end(char source) {
            return new Token(TokenType.END, source);
        }
    }

    public static @NotNull Token constant(char source) {
        return new Token(TokenType.CONSTANT, source);
    }
}