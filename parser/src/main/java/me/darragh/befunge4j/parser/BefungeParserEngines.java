package me.darragh.befunge4j.parser;

import lombok.experimental.UtilityClass;
import me.darragh.befunge4j.parser.type.java.JavaBefungeParserEngine;
import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 1.0.0
 */
@UtilityClass
public final class BefungeParserEngines {
    public static @NotNull JavaBefungeParserEngine javaBefungeParserEngine(@NotNull BefungeParserConfiguration configuration) {
        return new JavaBefungeParserEngine(configuration);
    }

    public static @NotNull JavaBefungeParserEngine javaBefungeParserEngine() {
        return new JavaBefungeParserEngine(BefungeParserConfiguration.DEFAULT);
    }
}
