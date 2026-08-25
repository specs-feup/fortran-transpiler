package pt.up.fe.specs.fortran.ast.nodes.expr.dataref;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.Designator;

import java.util.Collection;

public abstract class DataRef extends Designator {
    public DataRef(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
