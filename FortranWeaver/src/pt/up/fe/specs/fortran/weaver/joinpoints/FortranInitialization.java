package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.decl.Initialization;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AInitialization;

public class FortranInitialization<Self extends FortranInitialization<Self>> extends AInitialization<Self> {

    public FortranInitialization(Initialization node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public Initialization getNodeImpl() {
        return (Initialization) super.getNodeImpl();
    }
}
