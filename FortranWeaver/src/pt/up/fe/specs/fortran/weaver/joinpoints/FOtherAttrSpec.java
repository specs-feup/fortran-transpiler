package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.type.attributes.OtherAttrSpec;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AOtherAttrSpec;

public class FOtherAttrSpec extends AOtherAttrSpec {
    private final OtherAttrSpec otherAttrSpec;

    public FOtherAttrSpec(OtherAttrSpec otherAttrSpec) {
        super(new FAttrSpec(otherAttrSpec));

        this.otherAttrSpec = otherAttrSpec;
    }

    @Override
    public FortranNode getNode() {
        return otherAttrSpec;
    }
}
