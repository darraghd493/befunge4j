package me.darragh.befunge4j.interpreter;

import lombok.experimental.UtilityClass;
import me.darragh.befunge4j.interpreter.type.java.JavaBefungeInterpreterEngine;
import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 1.0.0
 */
@UtilityClass
public final class BefungeInterpreterEngines {
    public static @NotNull JavaBefungeInterpreterEngine javaBefungeInterpreterEngine() {
        return new JavaBefungeInterpreterEngine(BefungeInterpreterConfiguration.DEFAULT);
    }

    public static @NotNull JavaBefungeInterpreterEngine javaBefungeInterpreterEngine(@NotNull BefungeInterpreterConfiguration configuration) {
        return new JavaBefungeInterpreterEngine(configuration);
    }
}
