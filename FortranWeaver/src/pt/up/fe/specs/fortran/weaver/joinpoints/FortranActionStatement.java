package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.stmt.ActionStmt;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AActionStatement;

public class FortranActionStatement<Self extends FortranActionStatement<Self>> extends AActionStatement<Self> {

    public FortranActionStatement(ActionStmt node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public ActionStmt getNodeImpl() {
        return (ActionStmt) super.getNodeImpl();
    }
}
