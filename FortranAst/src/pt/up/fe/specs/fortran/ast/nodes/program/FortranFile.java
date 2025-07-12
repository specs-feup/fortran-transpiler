package pt.up.fe.specs.fortran.ast.nodes.program;

import org.suikasoft.jOptions.Datakey.DataKey;
import org.suikasoft.jOptions.Datakey.KeyFactory;
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
public class FortranFile extends FortranNode {

    /**
     * The name of this file.
     */
    public static final DataKey<String> FILE_NAME = KeyFactory.string("fileName").setDefault(() -> "<no-filename>");

    /**
     * The name of the path corresponding to this file.
     */
    public static final DataKey<String> FOLDER_NAME = KeyFactory.string("folderName").setDefault(() -> "./");

    public FortranFile(DataStore data, Collection<? extends FortranNode> children) {
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
