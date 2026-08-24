package pt.up.fe.specs.fortran.ast.nodes.expr;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

/// Special case for the call "NULL()"
public class NullInit extends Call {
    public NullInit(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
