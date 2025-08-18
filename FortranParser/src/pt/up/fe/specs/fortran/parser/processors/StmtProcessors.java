package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.nodes.stmt.ActionStmt;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ExecutableStmt;
import pt.up.fe.specs.fortran.ast.nodes.stmt.FormatStmt;
import pt.up.fe.specs.fortran.ast.nodes.stmt.PrintStmt;
import pt.up.fe.specs.fortran.parser.FlangName;
import pt.up.fe.specs.fortran.parser.FortranJsonResult;

public class StmtProcessors extends ANodeProcessor {


    public StmtProcessors(FortranJsonResult data) {
        super(data);
    }

    private void executableStmt(ExecutableStmt executableStmt) {
        executableStmt.set(ExecutableStmt.SOURCE, attributes(executableStmt).getString("source"));

        var label = attributes(executableStmt).getString("label");
        if (!label.equals("null")) {
            var labelDecl = factory().labelDecl(Integer.valueOf(label));
            data().processorData().addLabelDecl(labelDecl);
            executableStmt.addChild(0, labelDecl);
        }
    }


    private void actionStmt(ActionStmt actionStmt) {
        executableStmt(actionStmt);
    }


    public void printStmt(PrintStmt printStmt) {
        actionStmt(printStmt);
        printStmt.addChild(getChild(printStmt, FlangName.FORMAT));
        printStmt.addChildren(getChildren(printStmt, FlangName.OUTPUT_ITEM));
    }

    public void formatStmt(FormatStmt formatStmt) {
        executableStmt(formatStmt);


    }


}
