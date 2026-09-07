package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.type.attributes.KeywordAttributeSpecifier;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AKeywordAttributeSpecifier;

public class FortranKeywordAttributeSpecifier<Self extends FortranKeywordAttributeSpecifier<Self>> extends AKeywordAttributeSpecifier<Self> {

    public FortranKeywordAttributeSpecifier(KeywordAttributeSpecifier node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public KeywordAttributeSpecifier getNodeImpl() {
        return (KeywordAttributeSpecifier) super.getNodeImpl();
    }
}
