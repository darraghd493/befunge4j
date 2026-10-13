package me.darragh.befunge4j.parser.type.java;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 1.0.0
 */
@RequiredArgsConstructor
final class MatrixHelper {
    private final int width, height;

    char[] createCharacterMatrix(@NotNull String source) {
        char[] matrix = new char[this.width * this.height];
        int index = 0;
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (index < source.length()) {
                    matrix[y * this.width + x] = source.charAt(index++);
                } else {
                    matrix[y * this.width + x] = ' '; // TODO: Better handling?
                }
            }
        }
        return matrix;
    }
}
