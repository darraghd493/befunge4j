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
public final class ConstantIntegerToken extends Token {
    private final byte value;

    public ConstantIntegerToken(@NotNull TokenType type, byte value) {
        super(type);
        this.value = value;
    }

    @Override
    public String toString() {
        return "ConstantIntegerToken{" +
                "type=" + this.getType() +
                ", value=" + value +
                '}';
    }
}
