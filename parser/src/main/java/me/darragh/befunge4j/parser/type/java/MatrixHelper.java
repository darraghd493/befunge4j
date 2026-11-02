package me.darragh.befunge4j.parser.type.java;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author darraghd493
 * @since 1.0.0
 */
@RequiredArgsConstructor
final class MatrixHelper {
    private final int width, height;

    char[] createCharacterMatrix(@NotNull String source) {
        char[] matrix = new char[this.width * this.height];
        Arrays.fill(matrix, ' ');

        String[] lines = source.split("\\r?\\n");
        for (int y = 0; y < Math.min(lines.length, this.height); y++) {
            String line = lines[y];
            for (int x = 0; x < Math.min(line.length(), this.width); x++) {
                matrix[y * this.width + x] = line.charAt(x);
            }
        }
        return matrix;
    }
}
