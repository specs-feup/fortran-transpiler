package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.AFortranWeaverJoinPoint;

public class GenericFortranJoinpoint extends AFortranWeaverJoinPoint {

    private final FortranNode node;

    public GenericFortranJoinpoint(FortranNode node, FortranWeaver weaver) {
        super(weaver);
        this.node = node;
    }

    @Override
    public FortranNode getNode() {
        return node;
    }
}
