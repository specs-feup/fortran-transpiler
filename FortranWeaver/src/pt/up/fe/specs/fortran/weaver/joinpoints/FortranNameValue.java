package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.utils.NameValue;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ANameValue;

public class FortranNameValue<Self extends FortranNameValue<Self>> extends ANameValue<Self> {

    public FortranNameValue(NameValue node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public NameValue getNodeImpl() {
        return (NameValue) super.getNodeImpl();
    }

    @Override
    public String getNameImpl() {
        return this.getNodeImpl().getName();
    }
}
