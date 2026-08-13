package pt.up.fe.specs.fortran.ast.nodes.type.attributes;

import org.suikasoft.jOptions.Datakey.DataKey;
import org.suikasoft.jOptions.Datakey.KeyFactory;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.type.attributes.enums.AttrSpecKind;

import java.util.Collection;

public class OtherAttrSpec extends AttrSpec {
    public static final DataKey<AttrSpecKind> KIND = KeyFactory.enumeration("kind", AttrSpecKind.class);

    public OtherAttrSpec(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public AttrSpecKind getKind() {
        return get(KIND);
    }

    @Override
    public String getCode() {
        return encase(getKind().name());
    }
}
