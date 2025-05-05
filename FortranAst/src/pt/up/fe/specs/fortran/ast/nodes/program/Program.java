package pt.up.fe.specs.fortran.ast.nodes.program;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * R501 program
 * <p>
 * The root node of a Fortran application. Contains one or more ProgramUnit children.
 */
public class Program extends FortranNode {


    public Program(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public List<ProgramUnit> getProgramUnits() {
        return getChildren(ProgramUnit.class);
    }

    @Override
    public String getCode() {

        // Print each program unit
        return getProgramUnits().stream()
                .map(ProgramUnit::getCode)
                .collect(Collectors.joining("\n"));

    }
}
