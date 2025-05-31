package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.nodes.stmt.PrintStmt;
import pt.up.fe.specs.fortran.parser.FortranJsonResult;

public class StmtProcessors extends ANodeProcessor {


    public StmtProcessors(FortranJsonResult data) {
        super(data);
    }

    public void printStmt(PrintStmt printStmt) {
//        System.out.println(attributes().getAttrs(printStmt));
    }


}
