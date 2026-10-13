package me.darragh.befunge4j.parser;

import lombok.RequiredArgsConstructor;
import me.darragh.befunge4j.ast.Token;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author darraghd493
 * @since 1.0.0
 */
@RequiredArgsConstructor
public abstract class BefungeParserEngine {
    protected final @NotNull BefungeParserConfiguration configuration;

    public abstract @NotNull Token @Nullable [] parse(@NotNull String source) throws BefungeParserException;
}
