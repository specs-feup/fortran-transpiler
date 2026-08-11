package pt.up.fe.specs.fortran.ast.nodes.program.construct;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

public abstract class ExecPartConstruct extends FortranNode {
    public ExecPartConstruct(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
