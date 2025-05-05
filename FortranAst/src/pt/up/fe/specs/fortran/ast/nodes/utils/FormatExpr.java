package pt.up.fe.specs.fortran.ast.nodes.utils;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

/**
 * Derived from R1215 format, incomplete
 */
public class FormatExpr extends Format {

    public FormatExpr(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
