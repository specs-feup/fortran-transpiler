package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.nodes.program.MainProgram;
import pt.up.fe.specs.fortran.ast.nodes.program.Program;
import pt.up.fe.specs.fortran.parser.FlangName;
import pt.up.fe.specs.fortran.parser.FortranJsonResult;

public class Processors extends ANodeProcessor {


    public Processors(FortranJsonResult data) {
        super(data);
    }

    public void program(Program program) {

        var attrs = getAttrs(program.get(Program.ID));
        var children = getList(attrs, FlangName.PROGRAM_UNIT, Object::toString).stream()
                .map(this::getChild)
                .toList();

        program.setChildren(children);

        System.out.println(children);
    }


    public void mainProgram(MainProgram program) {
    }
}
