package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.type.attributes.AttrSpec;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AAttrSpec;

public class FAttrSpec extends AAttrSpec {
    public final AttrSpec attrSpec;

    public FAttrSpec(AttrSpec attrSpec) {
        this.attrSpec = attrSpec;
    }

    @Override
    public FortranNode getNode() {
        return null;
    }
}
