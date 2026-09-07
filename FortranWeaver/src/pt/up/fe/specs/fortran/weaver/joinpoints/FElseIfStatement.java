package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.ElseIfStmt;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AElseIfStatement;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;

public class FElseIfStatement extends AElseIfStatement {

    public final ElseIfStmt elseIfStmt;

    public FElseIfStatement(ElseIfStmt elseIfStmt, FortranWeaver weaver) {
        super(weaver);
        this.elseIfStmt = elseIfStmt;
    }

    @Override
    public AExpr getConditionImpl() {
        return FortranJoinpoints.create(elseIfStmt.getCondition(), getWeaverEngine(), AExpr.class);
    }

    @Override
    public FortranNode getNode() {
        return elseIfStmt;
    }
}
