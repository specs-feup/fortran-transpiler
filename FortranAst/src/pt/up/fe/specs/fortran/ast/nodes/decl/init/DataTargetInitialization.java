package pt.up.fe.specs.fortran.ast.nodes.decl.init;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.Designator;

import java.util.Collection;

public class DataTargetInitialization extends Initialization {
    public DataTargetInitialization(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public Designator getInitialDataTarget() {
        return getChild(Designator.class, 0);
    }

    @Override
    public String getCode() {
        return " => " + getInitialDataTarget().getCode();
    }
}
