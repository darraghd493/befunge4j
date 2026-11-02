package me.darragh.befunge4j.parser;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public record BefungeParserConfiguration(
        Mappings mappings,
        //region Grid
        int width,
        int height
        //endregion
) {
    public static final BefungeParserConfiguration DEFAULT = new BefungeParserConfiguration(Mappings.DEFAULT, 80, 25);

    public record Mappings(
            //region Math
            char mathAdditionCharacter,
            char mathSubtractionCharacter,
            char mathMultiplicationCharacter,
            char mathIntegerDivisionCharacter,
            char mathModuloCharacter,
            //endregion
            //region Logic
            char logicNotCharacter,
            char logicGreaterThanCharacter,
            //endregion
            //region Program Counter
            char programCounterMoveUpCharacter,
            char programCounterMoveDownCharacter,
            char programCounterMoveLeftCharacter,
            char programCounterMoveRightCharacter,
            char programCounterMoveRandomCharacter,
            char programCounterIfHorizontalCharacter,
            char programCounterIfVerticalCharacter,
            char programCounterBridgeCharacter,
            //region Complex
            char programCounterGetCharacter,
            char programCounterPutCharacter,
            //endregion
            //endregion
            //region Stack
            char stackDuplicateCharacter,
            char stackSwapCharacter,
            char stackPopDiscardCharacter,
            char stackPopOutputIntCharacter,
            char stackPopOutputCharCharacter,
            //endregion
            //region Miscellaneous
            char miscellaneousToggleStringModeCharacter,
            char miscellaneousInputIntCharacter,
            char miscellaneousInputCharCharacter,
            char miscellaneousEndCharacter
            //endregion
    ) {
        public static final Mappings DEFAULT = new Mappings('+', '-', '*', '/', '%', '!', '`', '^', 'v', '<', '>', '?', '_', '|', '#', 'g', 'p', ':', '\\', '$', '.', ',', '\"', '&', '~', '@');
    }
}
