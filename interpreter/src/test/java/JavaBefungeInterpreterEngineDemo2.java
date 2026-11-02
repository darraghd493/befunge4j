import me.darragh.befunge4j.ast.Token;
import me.darragh.befunge4j.interpreter.BefungeInterpreterEngine;
import me.darragh.befunge4j.interpreter.BefungeInterpreterEngines;
import me.darragh.befunge4j.parser.BefungeParserEngine;
import me.darragh.befunge4j.parser.BefungeParserEngines;

// https://esolangs.org/wiki/Befunge#Random_Number_Generator
String PROGRAM_BY_Tomrs123_2 = """
        & :v>00g2/.@
        v00_^#!`/2g00:<
        >0p:1>>:10p` !|
        >+00p^?<*2g01:<
        ^ g00:<""";

void main() {
    BefungeParserEngine parser = BefungeParserEngines.javaBefungeParserEngine();
    Token[] tokens = parser.parse(PROGRAM_BY_Tomrs123_2);

    BefungeInterpreterEngine interpreter = BefungeInterpreterEngines.javaBefungeInterpreterEngine();
    interpreter.execute(tokens);
}