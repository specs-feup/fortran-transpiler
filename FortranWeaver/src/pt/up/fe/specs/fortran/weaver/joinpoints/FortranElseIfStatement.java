package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.ElseIfStmt;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AElseIfStatement;

public class FortranElseIfStatement<Self extends FortranElseIfStatement<Self>> extends AElseIfStatement<Self> {

    public FortranElseIfStatement(ElseIfStmt node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public ElseIfStmt getNodeImpl() {
        return (ElseIfStmt) super.getNodeImpl();
    }

    @Override
    public AExpr<?> getConditionImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getCondition(), getWeaverEngine(), AExpr.class);
    }
}
