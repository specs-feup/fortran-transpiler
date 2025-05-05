package pt.up.fe.specs.fortran.ast.nodes.expr;

import org.suikasoft.jOptions.Datakey.DataKey;
import org.suikasoft.jOptions.Datakey.KeyFactory;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

/**
 * R605 literal-constant
 */
public class Literal extends Expr {

    // DATAKEYS BEGIN

    /**
     * A string representing the literal
     */
    public final static DataKey<String> SOURCE_LITERAL = KeyFactory.string("sourceLiteral");

    // DATAKEYS END

    public Literal(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public String getLiteral() {
        return get(SOURCE_LITERAL);
    }
}
