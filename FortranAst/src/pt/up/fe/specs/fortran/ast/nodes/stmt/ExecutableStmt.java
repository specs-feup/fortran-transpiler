package pt.up.fe.specs.fortran.ast.nodes.stmt;

import org.suikasoft.jOptions.Datakey.DataKey;
import org.suikasoft.jOptions.Datakey.KeyFactory;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;
import java.util.Optional;

/**
 * R514 executable-construct
 */
public abstract class ExecutableStmt extends FortranNode {

    // DATAKEYS BEGIN

    /**
     * The original source of this statement.
     */
    public final static DataKey<String> SOURCE = KeyFactory.string("source");

    /**
     * An integer, from 1 to 99999, representing the possible label of the statement.
     */
    public final static DataKey<Optional<Integer>> LABEL = KeyFactory.optional("label");


    // DATAKEYS END

    public ExecutableStmt(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
