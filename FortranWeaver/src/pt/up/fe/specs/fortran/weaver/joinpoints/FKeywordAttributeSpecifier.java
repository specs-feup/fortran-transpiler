package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.type.attributes.KeywordAttributeSpecifier;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AKeywordAttributeSpecifier;

public class FKeywordAttributeSpecifier extends AKeywordAttributeSpecifier {

    public final KeywordAttributeSpecifier keywordAttributeSpecifier;

    public FKeywordAttributeSpecifier(KeywordAttributeSpecifier keywordAttributeSpecifier, FortranWeaver weaver) {
        super(new FAttributeSpecifier(keywordAttributeSpecifier, weaver), weaver);
        this.keywordAttributeSpecifier = keywordAttributeSpecifier;
    }

    @Override
    public FortranNode getNode() {
        return keywordAttributeSpecifier;
    }
}
