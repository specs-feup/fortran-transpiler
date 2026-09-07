package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.IfStmt;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AActionStatement;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AIfStatement;

public class FortranIfStatement<Self extends FortranIfStatement<Self>> extends AIfStatement<Self> {

    public FortranIfStatement(IfStmt node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public IfStmt getNodeImpl() {
        return (IfStmt) super.getNodeImpl();
    }

    @Override
    public AExpr<?> getConditionImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getCondition(), getWeaverEngine(), AExpr.class);
    }

    @Override
    public AActionStatement<?> getStatementImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getThenAction(), getWeaverEngine(), AActionStatement.class);
    }
}
