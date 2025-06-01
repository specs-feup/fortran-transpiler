package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.nodes.stmt.PrintStmt;
import pt.up.fe.specs.fortran.parser.FlangName;
import pt.up.fe.specs.fortran.parser.FortranJsonResult;

public class StmtProcessors extends ANodeProcessor {


    public StmtProcessors(FortranJsonResult data) {
        super(data);
    }

    public void printStmt(PrintStmt printStmt) {
        printStmt.addChild(getChild(printStmt, FlangName.FORMAT));
        printStmt.addChildren(getChildren(printStmt, FlangName.OUTPUT_ITEM));
    }


}
