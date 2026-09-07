package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.program.Execution;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExecutableStatement;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExecution;

public class FortranExecution<Self extends FortranExecution<Self>> extends AExecution<Self> {

    public FortranExecution(Execution node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public Execution getNodeImpl() {
        return (Execution) super.getNodeImpl();
    }

    @Override
    public AExecutableStatement<?>[] getExecutableStmtsImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getStatements(), getWeaverEngine(), AExecutableStatement.class);
    }

    @Override
    public void insertBeginImpl(AExecutableStatement<?> stmt) {
        this.getNodeImpl().addChild(0, stmt.getNodeImpl());
    }

    @Override
    public void insertEndImpl(AExecutableStatement<?> stmt) {
        this.getNodeImpl().addChild(stmt.getNodeImpl());
    }
}
