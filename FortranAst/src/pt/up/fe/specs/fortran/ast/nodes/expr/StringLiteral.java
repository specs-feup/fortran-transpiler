package pt.up.fe.specs.fortran.ast.nodes.expr;

import org.suikasoft.jOptions.Datakey.DataKey;
import org.suikasoft.jOptions.Datakey.KeyFactory;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.FortranContext;
import pt.up.fe.specs.fortran.ast.FortranOptions;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;
import java.util.Optional;

/**
 * R724 char-literal-constant
 */
public class StringLiteral extends Literal {

    // DATAKEYS BEGIN

    /**
     * A prefix indicating the kind of the string (e.g., encoding)
     */
    public final static DataKey<Optional<String>> KIND_PARAM = KeyFactory.optional("kindParam");


    // DATAKEYS END

    public StringLiteral(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    @Override
    public String getCode() {
        char delimiter = get(CONTEXT).get(FortranContext.FORTRAN_OPTIONS).get(FortranOptions.SINGLE_QUOTE_STRINGS) ?
                '\'' : '"';

        var code = new StringBuilder();

        // Get kind param prefix
        var prefix = get(KIND_PARAM).map(p -> p + "_").orElse("");

        // TODO: Check if needed
        // Escape literal according to delimiter
        var delimiterS = Character.toString(delimiter);
        var escapedString = getLiteral().replace(delimiterS, delimiterS + delimiterS);

        return prefix + delimiterS + escapedString + delimiterS;
    }
}
