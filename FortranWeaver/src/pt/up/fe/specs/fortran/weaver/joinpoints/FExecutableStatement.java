package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ExecutableStmt;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExecutableStatement;

public class FExecutableStatement extends AExecutableStatement {

    private final ExecutableStmt executableStmt;

    public FExecutableStatement(ExecutableStmt executableStmt, FortranWeaver weaver) {
        super(new FStatement(executableStmt, weaver), weaver);
        this.executableStmt = executableStmt;
    }

    @Override
    public FortranNode getNode() {
        return executableStmt;
    }
}
