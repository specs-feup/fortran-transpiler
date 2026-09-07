package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.expr.Designator;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ADesignator;

public class FortranDesignator<Self extends FortranDesignator<Self>> extends ADesignator<Self> {

    public FortranDesignator(Designator node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public Designator getNodeImpl() {
        return (Designator) super.getNodeImpl();
    }
}
