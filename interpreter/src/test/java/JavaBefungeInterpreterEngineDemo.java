import me.darragh.befunge4j.ast.Token;
import me.darragh.befunge4j.interpreter.BefungeInterpreterEngine;
import me.darragh.befunge4j.interpreter.BefungeInterpreterEngines;
import me.darragh.befunge4j.parser.BefungeParserEngine;
import me.darragh.befunge4j.parser.BefungeParserEngines;

// https://esolangs.org/wiki/Befunge#99_Bottles_of_Beer
String PROGRAM_BY_Tomrs123_2 = """
        55+55+*1->:." ,llaw eht no reeb fo selttob"v
                    v,,,,,,,,,,,,,,,,,,,,,,,,,,,,, <
                    >:.".reeb fo selttob"v
                       v,,,,,,,,,,,,,,,, <
                       >55+," ,dnuora ti ssap dna nwod eno ekaT"v
                            v,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,, <
                            >1-:.".llaw eht no reeb fo selttob"v
                                 v,,,,,,,,,,,,,,,,,,,,,,,,,,,, <
                                 >55+:,,:2-v
                 ^                         _v
        v                                +55<
        >".reeb fo selttob 2 ,llaw eht no reeb fo selttob 2"v
        v ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,, <
        >:,".llaw eht no reeb fo elttob 1 ,dnuora ti ssap dna nwod eno ekaT"v
        v ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,   <
        >::,,".reeb fo elttob 1 ,llaw eht no reeb fo elttob 1"v
        v,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,      <
        >:,".llaw eht no reeb fo selttob erom on ,dnuora ti ssap dna nwod eno ekaT"v
        v,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,   <
        >55+:,".reeb fo selttob erom on ,llaw eht no reeb fo selttob erom oN"v
        v,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,       <
        >,".llaw eht no reeb fo selttob 99 ,erom emos yub dna erots eht ot oG"v
        @,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,   <""";

void main() {
    BefungeParserEngine parser = BefungeParserEngines.javaBefungeParserEngine();
    Token[] tokens = parser.parse(PROGRAM_BY_Tomrs123_2);

    BefungeInterpreterEngine interpreter = BefungeInterpreterEngines.javaBefungeInterpreterEngine();
    interpreter.execute(tokens);
}