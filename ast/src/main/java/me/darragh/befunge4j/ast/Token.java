package me.darragh.befunge4j.ast;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 1.0.0
 */
@Getter
public class Token {
    private final @NotNull TokenType type;

    public Token(@NotNull TokenType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                '}';
    }
}
