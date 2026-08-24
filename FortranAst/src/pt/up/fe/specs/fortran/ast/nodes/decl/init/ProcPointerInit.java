package pt.up.fe.specs.fortran.ast.nodes.decl.init;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

public abstract class ProcPointerInit extends FortranNode {
    public ProcPointerInit(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
