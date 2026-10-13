package me.darragh.befunge4j.ast;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public enum TokenType {
    //region Math
    MATH_ADDITION,
    MATH_SUBTRACTION,
    MATH_MULTIPLICATION,
    MATH_INT_DIVISION,
    MATH_MODULO,
    //endregion
    //region Logic
    LOGICAL_NOT,
    LOGICAL_GREATER_THAN,
    //endregion
    //region Program Counter
    PC_CHANGE_DIRECTION_LEFT,
    PC_CHANGE_DIRECTION_RIGHT,
    PC_CHANGE_DIRECTION_UP,
    PC_CHANGE_DIRECTION_DOWN,
    PC_CHANGE_DIRECTION_RANDOM,
    PC_IF_HORIZONTAL,
    PC_IF_VERTICAL,
    PC_BRIDGE,
    //region Complex
    PC_GET,
    PC_PUT,
    //endregion
    //endregion
    //region Stack
    STACK_DUPLICATE,
    STACK_SWAP,
    STACK_POP_DISCARD,
    STACK_POP_OUTPUT_INT,
    STACK_POP_OUTPUT_CHAR,
    //endregion
    //region Miscellaneous
    TOGGLE_STRING_MODE,
    INPUT_INT,
    INPUT_CHAR,
    END,
    //endregion
    CONSTANT
}
