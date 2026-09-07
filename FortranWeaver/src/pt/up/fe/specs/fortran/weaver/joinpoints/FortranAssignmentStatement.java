package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.stmt.AssignmentStmt;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ADataRef;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AAssignmentStatement;

public class FortranAssignmentStatement<Self extends FortranAssignmentStatement<Self>> extends AAssignmentStatement<Self> {

    public FortranAssignmentStatement(AssignmentStmt node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public AssignmentStmt getNodeImpl() {
        return (AssignmentStmt) super.getNodeImpl();
    }

    @Override
    public AExpr<?> getExprImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getExpression(), getWeaverEngine(), AExpr.class);
    }

    @Override
    public ADataRef<?> getVariableImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getVariable(), getWeaverEngine(), ADataRef.class);
    }
}
