package me.darragh.befunge4j.parser;

import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public final class BefungeParserException extends RuntimeException {
    public BefungeParserException(@NotNull String message) {
        super(message);
    }
}
