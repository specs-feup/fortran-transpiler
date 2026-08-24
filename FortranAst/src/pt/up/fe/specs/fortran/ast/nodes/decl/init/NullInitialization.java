package pt.up.fe.specs.fortran.ast.nodes.decl.init;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.NullInit;

import java.util.Collection;

public class NullInitialization extends Initialization {
    public NullInitialization(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public NullInit getNullInit() {
        return getChild(NullInit.class, 0);
    }

    @Override
    public String getCode() {
        return " => " + getNullInit().getCode();
    }
}
