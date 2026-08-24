package pt.up.fe.specs.fortran.ast.nodes.decl.proc;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

public abstract class ProcInterface extends FortranNode {
    public ProcInterface(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
