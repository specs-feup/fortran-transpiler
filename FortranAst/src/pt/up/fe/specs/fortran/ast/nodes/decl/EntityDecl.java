package pt.up.fe.specs.fortran.ast.nodes.decl;

import org.suikasoft.jOptions.Datakey.DataKey;
import org.suikasoft.jOptions.Datakey.KeyFactory;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

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


}
