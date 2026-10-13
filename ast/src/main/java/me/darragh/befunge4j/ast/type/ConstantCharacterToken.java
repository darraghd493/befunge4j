package me.darragh.befunge4j.ast.type;

import lombok.Getter;
import me.darragh.befunge4j.ast.Token;
import me.darragh.befunge4j.ast.TokenType;
import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 0.0.1
 */
@Getter
public final class ConstantCharacterToken extends Token {
    private final char value;

    public ConstantCharacterToken(@NotNull TokenType type, char value) {
        super(type);
        this.value = value;
    }

    @Override
    public String toString() {
        return "ConstantCharacterToken{" +
                "type=" + this.getType() +
                ", value=" + value +
                '}';
    }
}
