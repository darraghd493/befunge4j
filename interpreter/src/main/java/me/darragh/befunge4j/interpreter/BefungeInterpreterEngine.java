package me.darragh.befunge4j.interpreter;

import lombok.RequiredArgsConstructor;
import me.darragh.befunge4j.ast.Token;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author darraghd493
 * @since 1.0.0
 */
@RequiredArgsConstructor
public abstract class BefungeInterpreterEngine {
    protected final @NotNull BefungeInterpreterConfiguration configuration;

    public abstract void execute(@Nullable Token @NotNull [] tokens);
}
