package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.nodes.program.Execution;
import pt.up.fe.specs.fortran.ast.nodes.program.MainProgram;
import pt.up.fe.specs.fortran.ast.nodes.program.Program;
import pt.up.fe.specs.fortran.parser.FlangName;
import pt.up.fe.specs.fortran.parser.FortranJsonResult;

public class Processors extends ANodeProcessor {


    public Processors(FortranJsonResult data) {
        super(data);
    }

    public void program(Program program) {
        program.setChildren(getChildren(program, FlangName.PROGRAM_UNIT));
    }

    public void mainProgram(MainProgram mainProgram) {

        var firstName = attributes().getOptionalString(mainProgram, "source", FlangName.PROGRAM_STMT, FlangName.NAME);
        // [specification-part]
        // [execution-part]
        // [internal-subprogram-part]
        var endName = attributes().getString(mainProgram, "source", FlangName.END_PROGRAM_STMT, FlangName.NAME);

        var name = firstName.orElse(endName);

        mainProgram.setOptional(MainProgram.PROGRAM_NAME, name);
    }

    public void execution(Execution execution) {
        execution.setChildren(getChildren(execution, FlangName.EXECUTION_PART_CONSTRUCT));
    }


}
