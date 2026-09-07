package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.type.attributes.ParameterKeyword;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AParameterKeyword;

public class FParameterKeyword extends AParameterKeyword {

    public final ParameterKeyword parameterKeyword;

    public FParameterKeyword(ParameterKeyword parameterKeyword, FortranWeaver weaver) {
        super(new FKeywordAttributeSpecifier(parameterKeyword, weaver), weaver);
        this.parameterKeyword = parameterKeyword;
    }

    @Override
    public FortranNode getNode() {
        return parameterKeyword;
    }
}
