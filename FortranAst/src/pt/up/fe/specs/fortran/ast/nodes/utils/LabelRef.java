package pt.up.fe.specs.fortran.ast.nodes.utils;

import org.suikasoft.jOptions.Datakey.DataKey;
import org.suikasoft.jOptions.Datakey.KeyFactory;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.decl.LabelDecl;

import java.util.Collection;

public class LabelRef extends FortranNode {

    // DATAKEYS BEGIN


    /**
     * An integer, from 1 to 99999, representing the possible label of the statement.
     */
    public final static DataKey<LabelDecl> LABEL_DECL = KeyFactory.object("labelDecl", LabelDecl.class);


    // DATAKEYS END


    public LabelRef(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    @Override
    public String getCode() {
        return get(LABEL_DECL).get(LabelDecl.LABEL).toString();
    }
}
