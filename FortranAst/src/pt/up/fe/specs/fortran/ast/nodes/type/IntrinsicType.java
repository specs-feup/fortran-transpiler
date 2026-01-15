package pt.up.fe.specs.fortran.ast.nodes.type;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

/**
 * Represents a Fortran built-in type (e.g., integer, real).
 */
public class IntrinsicType extends FortranType {

    public IntrinsicType(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
