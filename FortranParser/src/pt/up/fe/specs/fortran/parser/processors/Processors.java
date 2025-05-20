package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.nodes.program.MainProgram;
import pt.up.fe.specs.fortran.ast.nodes.program.Program;
import pt.up.fe.specs.fortran.parser.FortranJsonResult;

import java.util.List;

public class Processors {

    public static void program(Program program, FortranJsonResult data) {
        var attrs = data.attributes().get(program.get(Program.ID));
        var puId = (List<String>) attrs.get("program-unit");

        System.out.println(data.attributes().get(puId.get(0)));

    }


    public static void mainProgram(MainProgram program, FortranJsonResult data) {

    }
}
