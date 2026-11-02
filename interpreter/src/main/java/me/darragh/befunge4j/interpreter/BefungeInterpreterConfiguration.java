package me.darragh.befunge4j.interpreter;

import me.darragh.befunge4j.parser.BefungeParserConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public record BefungeInterpreterConfiguration(
        @NotNull BefungeParserConfiguration parserConfiguration,
        @NotNull ConsoleIo console,
        //region Grid
        int width,
        int height
        //endregion
) {
    public static final BefungeInterpreterConfiguration DEFAULT = new BefungeInterpreterConfiguration(BefungeParserConfiguration.DEFAULT, ConsoleIo.UNSAFE_SYSTEM_CONSOLE, 80, 25);

    //region Console IO
    public interface ConsoleIo {
        ConsoleIo UNSAFE_SYSTEM_CONSOLE = new ConsoleIo() {
            Scanner scanner = new Scanner(System.in);

            @Override
            public char inChar() {
                String line = this.scanner.nextLine();
                while (line.isEmpty()) {
                    line = this.scanner.nextLine();
                }
                return line.charAt(0);
            }

            @Override
            public int inInteger() {
                while (!this.scanner.hasNextInt()) {
                    this.scanner.nextLine();
                    System.out.println("That's not an integer. Try again.");
                }
                int value = this.scanner.nextInt();
                this.scanner.nextLine();
                return value;
            }

            @Override
            public void outChar(char c) {
                System.out.print(c);
            }

            @Override
            public void outInteger(int c) {
                System.out.println(c);
            }
        };

        char inChar();

        int inInteger();

        void outChar(char c);

        void outInteger(int c);
    }
    //endregion
}
