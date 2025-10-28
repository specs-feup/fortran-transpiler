package pt.up.fe.specs.fortran.ast.nodes.type;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

/**
 * Represents Fortran integer types.
 */
public class IntegerType extends IntrinsicType {

    public IntegerType(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
