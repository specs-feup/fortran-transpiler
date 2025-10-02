package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.FortranContext;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.program.Execution;
import pt.up.fe.specs.fortran.ast.nodes.program.FortranFile;
import pt.up.fe.specs.fortran.ast.nodes.program.MainProgram;
import pt.up.fe.specs.fortran.parser.FlangName;
import pt.up.fe.specs.fortran.parser.FortranJsonResult;
import pt.up.fe.specs.util.SpecsIo;

public class ProgramProcessors extends ANodeProcessor {


    public ProgramProcessors(FortranJsonResult data) {
        super(data);
    }

    public void program(FortranFile fortranFile) {
        fortranFile.setChildren(getChildren(fortranFile, FlangName.PROGRAM_UNIT));

        var lastParsedFile = fortranFile.get(FortranNode.CONTEXT).get(FortranContext.LAST_PARSED_FILE).orElse(null);
        if (lastParsedFile != null) {
            var fileName = lastParsedFile.getName();
            if (fileName.endsWith(".json")) {
                fileName = SpecsIo.removeExtension(fileName) + ".f90";
            }

            fortranFile.set(FortranFile.FILE_NAME, fileName);
            fortranFile.set(FortranFile.FOLDER_NAME, lastParsedFile.getParent());
        }
    }

    public void mainProgram(MainProgram mainProgram) {

        var firstName = attributes().getOptionalString(mainProgram, "source", FlangName.PROGRAM_STMT, FlangName.NAME);
        // [specification-part]
        // [execution-part]
        mainProgram.addChild(getChild(mainProgram, FlangName.EXECUTION_PART));
        // [internal-subprogram-part]
        var endName = attributes().getString(mainProgram, "source", FlangName.END_PROGRAM_STMT, FlangName.NAME);

        var name = firstName.orElse(endName);

        mainProgram.setOptional(MainProgram.PROGRAM_NAME, name);
    }

    public void execution(Execution execution) {
        if (attributes(execution).has(FlangName.EXECUTION_PART_CONSTRUCT)) {
            execution.setChildren(getChildren(execution, FlangName.EXECUTION_PART_CONSTRUCT));
        }
    }


}
