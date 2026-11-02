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
        if (c == this.configuration.mappings().mathAdditionCharacter()) return Tokens.Math.addition(c);
        else if (c == this.configuration.mappings().mathSubtractionCharacter()) return Tokens.Math.subtraction(c);
        else if (c == this.configuration.mappings().mathMultiplicationCharacter()) return Tokens.Math.multiplication(c);
        else if (c == this.configuration.mappings().mathIntegerDivisionCharacter()) return Tokens.Math.integerDivision(c);
        else if (c == this.configuration.mappings().mathModuloCharacter()) return Tokens.Math.modulo(c);
        //endregion
        //region Logic
        else if (c == this.configuration.mappings().logicNotCharacter()) return Tokens.Logical.not(c);
        else if (c == this.configuration.mappings().logicGreaterThanCharacter()) return Tokens.Logical.greaterThan(c);
        //endregion
        //region Program Counter
        else if (c == this.configuration.mappings().programCounterMoveLeftCharacter()) return Tokens.ProgramCounter.changeDirectionLeft(c);
        else if (c == this.configuration.mappings().programCounterMoveRightCharacter()) return Tokens.ProgramCounter.changeDirectionRight(c);
        else if (c == this.configuration.mappings().programCounterMoveUpCharacter()) return Tokens.ProgramCounter.changeDirectionUp(c);
        else if (c == this.configuration.mappings().programCounterMoveDownCharacter()) return Tokens.ProgramCounter.changeDirectionDown(c);
        else if (c == this.configuration.mappings().programCounterMoveRandomCharacter()) return Tokens.ProgramCounter.changeDirectionRandom(c);
        else if (c == this.configuration.mappings().programCounterIfHorizontalCharacter()) return Tokens.ProgramCounter.ifHorizontal(c);
        else if (c == this.configuration.mappings().programCounterIfVerticalCharacter()) return Tokens.ProgramCounter.ifVertical(c);
        else if (c == this.configuration.mappings().programCounterBridgeCharacter()) return Tokens.ProgramCounter.bridge(c);
        //region Complex
        else if (c == this.configuration.mappings().programCounterGetCharacter()) return Tokens.ProgramCounter.get(c);
        else if (c == this.configuration.mappings().programCounterPutCharacter()) return Tokens.ProgramCounter.put(c);
        //endregion
        //endregion
        //region Stack
        else if (c == this.configuration.mappings().stackDuplicateCharacter()) return Tokens.Stack.duplicate(c);
        else if (c == this.configuration.mappings().stackSwapCharacter()) return Tokens.Stack.swap(c);
        else if (c == this.configuration.mappings().stackPopDiscardCharacter()) return Tokens.Stack.popDiscard(c);
        else if (c == this.configuration.mappings().stackPopOutputIntCharacter()) return Tokens.Stack.popOutputInteger(c);
        else if (c == this.configuration.mappings().stackPopOutputCharCharacter()) return Tokens.Stack.popOutputCharacter(c);
        //endregion
        //region Miscellaneous
        else if (c == this.configuration.mappings().miscellaneousToggleStringModeCharacter()) return Tokens.Miscellaneous.toggleStringMode(c);
        else if (c == this.configuration.mappings().miscellaneousInputIntCharacter()) return Tokens.Miscellaneous.inputInteger(c);
        else if (c == this.configuration.mappings().miscellaneousInputCharCharacter()) return Tokens.Miscellaneous.inputCharacter(c);
        else if (c == this.configuration.mappings().miscellaneousEndCharacter()) return Tokens.Miscellaneous.end(c);
        //endregion
        //region Constants
        else if ((int) c < 256) return Tokens.constant(c); // ASCII only
        //endregion

        else return null;
    }
}
