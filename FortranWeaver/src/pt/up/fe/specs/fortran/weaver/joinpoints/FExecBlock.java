package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.program.ExecBlock;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExecBlock;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExecPartConstruct;

public class FExecBlock extends AExecBlock {
    private final ExecBlock execBlock;

    public FExecBlock(ExecBlock execBlock) {
        this.execBlock = execBlock;
    }

    @Override
    public AExecPartConstruct[] getConstructsArrayImpl() {
        return (AExecPartConstruct[]) execBlock.getConstructs()
                .stream()
                .map(FortranJoinpoints::create)
                .toArray();
    }

    @Override
    public FortranNode getNode() {
        return execBlock;
    }
}
