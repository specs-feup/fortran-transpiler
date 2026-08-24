package pt.up.fe.specs.fortran.ast.nodes.expr;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

public abstract class ProcDesignator extends FortranNode {
    public ProcDesignator(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
