package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.program.construct.ExecConstruct;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExecConstruct;

public class FExecConstruct extends AExecConstruct {
    public final ExecConstruct executableConstruct;

    public FExecConstruct(ExecConstruct executableConstruct) {
        super(new FExecPartConstruct(executableConstruct));
        this.executableConstruct = executableConstruct;
    }

    @Override
    public FortranNode getNode() {
        return executableConstruct;
    }
}
