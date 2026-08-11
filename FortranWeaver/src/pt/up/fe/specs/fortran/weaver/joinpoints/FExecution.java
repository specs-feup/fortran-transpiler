package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.program.Execution;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExecPartConstruct;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExecution;

public class FExecution extends AExecution {

    private final Execution execution;

    public FExecution(Execution execution) {
        super(new FExecBlock(execution));
        this.execution = execution;
    }

    @Override
    public void insertBeginImpl(AExecPartConstruct stmt) {
        execution.addChild(0, stmt.getNode());
    }

    @Override
    public void insertEndImpl(AExecPartConstruct stmt) {
        execution.addChild(stmt.getNode());
    }

    @Override
    public FortranNode getNode() {
        return execution;
    }
}
