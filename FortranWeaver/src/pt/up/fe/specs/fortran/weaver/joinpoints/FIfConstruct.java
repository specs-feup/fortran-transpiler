package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.IfConstruct;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.*;

public class FIfConstruct extends AIfConstruct {

    public final IfConstruct ifConstruct;

    public FIfConstruct(IfConstruct ifConstruct, FortranWeaver weaver) {
        super(new FExecutableStatement(ifConstruct, weaver), weaver);
        this.ifConstruct = ifConstruct;
    }

    @Override
    public AElseBlock getElseBlockImpl() {
        return FortranJoinpoints.create(ifConstruct.getElseBlock().get(), getWeaverEngine(), AElseBlock.class);
    }

    @Override
    public AElseIfBlock[] getElseIfBlocksArrayImpl() {
        return ifConstruct.getElseIfBlocks()
                .stream()
                .map(node -> FortranJoinpoints.create(node, getWeaverEngine()))
                .toList()
                .toArray(new AElseIfBlock[0]);
    }

    @Override
    public AIfThenBlock getIfThenBlockImpl() {
        return FortranJoinpoints.create(ifConstruct.getIfThenBlock(), getWeaverEngine(), AIfThenBlock.class);
    }

    @Override
    public FortranNode getNode() {
        return ifConstruct;
    }
}
