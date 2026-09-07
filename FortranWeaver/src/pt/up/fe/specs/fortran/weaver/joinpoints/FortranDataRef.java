package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.expr.DataRef;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ADataRef;

public class FortranDataRef<Self extends FortranDataRef<Self>> extends ADataRef<Self> {

    public FortranDataRef(DataRef node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public DataRef getNodeImpl() {
        return (DataRef) super.getNodeImpl();
    }

    @Override
    public String getNameImpl() {
        return this.getNodeImpl().getName();
    }
}
