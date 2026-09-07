package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.IfThenStmt;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AIfThenStatement;

public class FortranIfThenStatement<Self extends FortranIfThenStatement<Self>> extends AIfThenStatement<Self> {

    public FortranIfThenStatement(IfThenStmt node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public IfThenStmt getNodeImpl() {
        return (IfThenStmt) super.getNodeImpl();
    }

    @Override
    public AExpr<?> getConditionImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getCondition(), getWeaverEngine(), AExpr.class);
    }
}
