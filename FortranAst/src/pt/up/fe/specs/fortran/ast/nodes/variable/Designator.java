package pt.up.fe.specs.fortran.ast.nodes.variable;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.Expr;

import java.util.Collection;

public class Designator extends Expr {
    public Designator(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
