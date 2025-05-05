package pt.up.fe.specs.fortran.ast.nodes.utils;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

/**
 * Derived from R1215 format
 */
public class FormatStar extends Format {


    public FormatStar(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    @Override
    public String getCode() {
        return "*";
    }
}
