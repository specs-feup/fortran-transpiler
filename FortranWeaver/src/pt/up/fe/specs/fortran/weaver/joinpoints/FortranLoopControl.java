package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.loops.LoopControl;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ALoopControl;

public class FortranLoopControl<Self extends FortranLoopControl<Self>> extends ALoopControl<Self> {

    public FortranLoopControl(LoopControl node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public LoopControl getNodeImpl() {
        return (LoopControl) super.getNodeImpl();
    }
}
