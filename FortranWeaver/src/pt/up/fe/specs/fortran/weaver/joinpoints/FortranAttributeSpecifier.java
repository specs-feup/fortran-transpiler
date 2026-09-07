package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.type.attributes.AttributeSpecifier;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AAttributeSpecifier;

public class FortranAttributeSpecifier<Self extends FortranAttributeSpecifier<Self>> extends AAttributeSpecifier<Self> {

    public FortranAttributeSpecifier(AttributeSpecifier node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public AttributeSpecifier getNodeImpl() {
        return (AttributeSpecifier) super.getNodeImpl();
    }
}
