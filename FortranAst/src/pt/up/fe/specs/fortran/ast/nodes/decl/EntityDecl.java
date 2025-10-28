package pt.up.fe.specs.fortran.ast.nodes.decl;

import org.suikasoft.jOptions.Datakey.DataKey;
import org.suikasoft.jOptions.Datakey.KeyFactory;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.type.FortranType;

import java.util.Collection;

public class EntityDecl extends FortranDecl {

    // DATAKEYS BEGIN

    /**
     * The name of the entity.
     */
    public final static DataKey<String> NAME = KeyFactory.string("name");


    // DATAKEYS END

    public EntityDecl(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }


    public FortranType getType() {
        return getChild(FortranType.class, 0);
    }

    @Override
    public String getCode() {
        // TODO: Add initialization when supported

        return get(NAME);
    }
}
