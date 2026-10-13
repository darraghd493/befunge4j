package me.darragh.befunge4j.parser.type.java;

import me.darragh.befunge4j.ast.Token;
import me.darragh.befunge4j.ast.Tokens;
import me.darragh.befunge4j.parser.BefungeParserConfiguration;
import me.darragh.befunge4j.parser.BefungeParserEngine;
import me.darragh.befunge4j.parser.BefungeParserException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public final class JavaBefungeParserEngine extends BefungeParserEngine {
    public JavaBefungeParserEngine(@NotNull BefungeParserConfiguration configuration) {
        super(configuration);
    }

    @Override
    public @NotNull Token @Nullable [] parse(@NotNull String source) throws BefungeParserException {
        MatrixHelper matrixHelper = new MatrixHelper(this.configuration.width(), this.configuration.height());
        char[] characterMatrix = matrixHelper.createCharacterMatrix(source);
        Token[] tokenMatrix = new Token[this.configuration.width() * this.configuration.height()];

        // go through the matrix and map tokens
        for (int i = 0; i < source.length(); i++) {
            char c = characterMatrix[i];
            Token token = this.toToken(c);
            tokenMatrix[i] = token;
        }

        return tokenMatrix;
    }

    private @Nullable Token toToken(char c) {
        // unfortunately switch does not support calling a method for a value
        // it makes sense due to how it fundamentally works but...

        //region Math
        if (c == this.configuration.mathAdditionCharacter()) return Tokens.Math.addition();
        else if (c == this.configuration.mathSubtractionCharacter()) return Tokens.Math.subtraction();
        else if (c == this.configuration.mathMultiplicationCharacter()) return Tokens.Math.multiplication();
        else if (c == this.configuration.mathIntegerDivisionCharacter()) return Tokens.Math.integerDivision();
        else if (c == this.configuration.mathModuloCharacter()) return Tokens.Math.modulo();
        //endregion
        //region Logic
        else if (c == this.configuration.logicNotCharacter()) return Tokens.Logical.not();
        else if (c == this.configuration.logicGreaterThanCharacter()) return Tokens.Logical.greaterThan();
        //endregion
        //region Program Counter
        else if (c == this.configuration.programCounterMoveLeftCharacter()) return Tokens.ProgramCounter.changeDirectionLeft();
        else if (c == this.configuration.programCounterMoveRightCharacter()) return Tokens.ProgramCounter.changeDirectionRight();
        else if (c == this.configuration.programCounterMoveUpCharacter()) return Tokens.ProgramCounter.changeDirectionUp();
        else if (c == this.configuration.programCounterMoveDownCharacter()) return Tokens.ProgramCounter.changeDirectionDown();
        else if (c == this.configuration.programCounterMoveRandomCharacter()) return Tokens.ProgramCounter.changeDirectionRandom();
        else if (c == this.configuration.programCounterIfHorizontalCharacter()) return Tokens.ProgramCounter.ifHorizontal();
        else if (c == this.configuration.programCounterIfVerticalCharacter()) return Tokens.ProgramCounter.ifVertical();
        else if (c == this.configuration.programCounterBridgeCharacter()) return Tokens.ProgramCounter.bridge();
        //region Complex
        else if (c == this.configuration.programCounterGetCharacter()) return Tokens.ProgramCounter.get();
        else if (c == this.configuration.programCounterPutCharacter()) return Tokens.ProgramCounter.put();
        //endregion
        //endregion
        //region Stack
        else if (c == this.configuration.stackDuplicateCharacter()) return Tokens.Stack.duplicate();
        else if (c == this.configuration.stackSwapCharacter()) return Tokens.Stack.swap();
        else if (c == this.configuration.stackPopDiscardCharacter()) return Tokens.Stack.popDiscard();
        else if (c == this.configuration.stackPopOutputIntCharacter()) return Tokens.Stack.popOutputInteger();
        else if (c == this.configuration.stackPopOutputCharCharacter()) return Tokens.Stack.popOutputCharacter();
        //endregion
        //region Miscellaneous
        else if (c == this.configuration.miscellaneousToggleStringModeCharacter()) return Tokens.Miscellaneous.toggleStringMode();
        else if (c == this.configuration.miscellaneousInputIntCharacter()) return Tokens.Miscellaneous.inputInteger();
        else if (c == this.configuration.miscellaneousInputCharCharacter()) return Tokens.Miscellaneous.inputCharacter();
        else if (c == this.configuration.miscellaneousEndCharacter()) return Tokens.Miscellaneous.end();
        //endregion
        //region Constants
        else if (Character.isDigit(c)) return Tokens.constant((byte) Character.getNumericValue(c));
        else if ((int) c < 256) return Tokens.constant((byte) c); // ASCII
        //endregion

        else return null;
    }
}
