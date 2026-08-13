package pt.up.fe.specs.fortran.ast.nodes.type.attributes;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.FortranKeyword;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.specification.shape.ArraySpec;

import java.util.Collection;

public class DimAttrSpec extends AttrSpec {
    public DimAttrSpec(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    private ArraySpec getArraySpec() {
        return getChild(ArraySpec.class, 0);
    }

    @Override
    public String getCode() {
        var arraySpec = getArraySpec();
        return keyword(FortranKeyword.DIMENSION) + arraySpec.getCode();
    }
}
