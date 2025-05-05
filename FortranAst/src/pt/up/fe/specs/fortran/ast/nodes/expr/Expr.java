package pt.up.fe.specs.fortran.ast.nodes.expr;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

/**
 * R1022 expr
 */
public abstract class Expr extends FortranNode {

    public Expr(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
