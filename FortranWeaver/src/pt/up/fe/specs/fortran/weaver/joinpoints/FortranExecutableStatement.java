package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.stmt.ExecutableStmt;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExecutableStatement;

public class FortranExecutableStatement<Self extends FortranExecutableStatement<Self>> extends AExecutableStatement<Self> {

    public FortranExecutableStatement(ExecutableStmt node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public ExecutableStmt getNodeImpl() {
        return (ExecutableStmt) super.getNodeImpl();
    }
}
