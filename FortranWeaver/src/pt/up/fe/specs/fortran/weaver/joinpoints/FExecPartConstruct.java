package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.program.construct.ExecPartConstruct;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExecPartConstruct;

public class FExecPartConstruct extends AExecPartConstruct {
    private final ExecPartConstruct execPartConstruct;

    public FExecPartConstruct(ExecPartConstruct execPartConstruct) {
        this.execPartConstruct = execPartConstruct;
    }

    @Override
    public FortranNode getNode() {
        return execPartConstruct;
    }
}
