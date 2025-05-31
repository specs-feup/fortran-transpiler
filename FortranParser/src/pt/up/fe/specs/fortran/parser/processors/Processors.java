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
        var children = attrs.getList(FlangName.PROGRAM_UNIT, Object::toString).stream()
                .map(this::getChild)
                .toList();

        program.setChildren(children);

        System.out.println(children);
    }


    public void mainProgram(MainProgram mainProgram) {
        var attrs = getAttrs(mainProgram.get(MainProgram.ID));
/*
        if(hasStmt(attrs, FlangName.PROGRAM_STMT)) {
            var programName = getStringFrom(attrs, getStmtAttr(FlangName.PROGRAM_STMT) )
        }
*/
        /*
                [program-stmt] [specification-part] [execution-part]
        [internal-subprogram-part] end-program-stmt
 */
        // Always an end-program-stmt
    }

}
