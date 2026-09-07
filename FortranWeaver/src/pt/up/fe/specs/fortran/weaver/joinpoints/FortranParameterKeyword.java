package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.type.attributes.ParameterKeyword;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AParameterKeyword;

public class FortranParameterKeyword<Self extends FortranParameterKeyword<Self>> extends AParameterKeyword<Self> {

    public FortranParameterKeyword(ParameterKeyword node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public ParameterKeyword getNodeImpl() {
        return (ParameterKeyword) super.getNodeImpl();
    }
}
