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

        BefungeParserConfiguration.Mappings mappings = this.configuration.mappings();

        // go through the matrix and map tokens
        for (int i = 0; i < characterMatrix.length; i++) {
            char c = characterMatrix[i];
            Token token = tokenise(mappings, c);
            tokenMatrix[i] = token;
        }

        return tokenMatrix;
    }

    public static @Nullable Token tokenise(@NotNull BefungeParserConfiguration.Mappings mappings, char c) { // used in the interpreter
        // unfortunately switch does not support calling a method for a value
        // it makes sense due to how it fundamentally works but...

        //region Math
        if (c == mappings.mathAdditionCharacter()) return Tokens.Math.addition(c);
        else if (c == mappings.mathSubtractionCharacter()) return Tokens.Math.subtraction(c);
        else if (c == mappings.mathMultiplicationCharacter()) return Tokens.Math.multiplication(c);
        else if (c == mappings.mathIntegerDivisionCharacter()) return Tokens.Math.integerDivision(c);
        else if (c == mappings.mathModuloCharacter()) return Tokens.Math.modulo(c);
        //endregion
        //region Logic
        else if (c == mappings.logicNotCharacter()) return Tokens.Logical.not(c);
        else if (c == mappings.logicGreaterThanCharacter()) return Tokens.Logical.greaterThan(c);
        //endregion
        //region Program Counter
        else if (c == mappings.programCounterMoveLeftCharacter()) return Tokens.ProgramCounter.changeDirectionLeft(c);
        else if (c == mappings.programCounterMoveRightCharacter()) return Tokens.ProgramCounter.changeDirectionRight(c);
        else if (c == mappings.programCounterMoveUpCharacter()) return Tokens.ProgramCounter.changeDirectionUp(c);
        else if (c == mappings.programCounterMoveDownCharacter()) return Tokens.ProgramCounter.changeDirectionDown(c);
        else if (c == mappings.programCounterMoveRandomCharacter()) return Tokens.ProgramCounter.changeDirectionRandom(c);
        else if (c == mappings.programCounterIfHorizontalCharacter()) return Tokens.ProgramCounter.ifHorizontal(c);
        else if (c == mappings.programCounterIfVerticalCharacter()) return Tokens.ProgramCounter.ifVertical(c);
        else if (c == mappings.programCounterBridgeCharacter()) return Tokens.ProgramCounter.bridge(c);
        //region Complex
        else if (c == mappings.programCounterGetCharacter()) return Tokens.ProgramCounter.get(c);
        else if (c == mappings.programCounterPutCharacter()) return Tokens.ProgramCounter.put(c);
        //endregion
        //endregion
        //region Stack
        else if (c == mappings.stackDuplicateCharacter()) return Tokens.Stack.duplicate(c);
        else if (c == mappings.stackSwapCharacter()) return Tokens.Stack.swap(c);
        else if (c == mappings.stackPopDiscardCharacter()) return Tokens.Stack.popDiscard(c);
        else if (c == mappings.stackPopOutputIntCharacter()) return Tokens.Stack.popOutputInteger(c);
        else if (c == mappings.stackPopOutputCharCharacter()) return Tokens.Stack.popOutputCharacter(c);
        //endregion
        //region Miscellaneous
        else if (c == mappings.miscellaneousToggleStringModeCharacter()) return Tokens.Miscellaneous.toggleStringMode(c);
        else if (c == mappings.miscellaneousInputIntCharacter()) return Tokens.Miscellaneous.inputInteger(c);
        else if (c == mappings.miscellaneousInputCharCharacter()) return Tokens.Miscellaneous.inputCharacter(c);
        else if (c == mappings.miscellaneousEndCharacter()) return Tokens.Miscellaneous.end(c);
        //endregion
        //region Constants
        else if ((int) c < 256) return Tokens.constant(c); // ASCII only
        //endregion

        else return null;
    }
}
