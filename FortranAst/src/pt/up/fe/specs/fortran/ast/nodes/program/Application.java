package pt.up.fe.specs.fortran.ast.nodes.program;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.util.SpecsCollections;

import java.util.Collection;
import java.util.List;

public class Application extends FortranNode {

    public Application(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public List<FortranFile> getFiles() {
        return SpecsCollections.cast(getChildren(), FortranFile.class);
    }

    /**
     * Collects the code of all the files.
     *
     * @return
     */
    @Override
    public String getCode() {

        var code = new StringBuilder();
        for (var file : getFiles()) {
            code.append("! File '" + file.get(FortranFile.FILE_NAME) + "'\n\n");
            code.append(file.getCode());
        }

        return code.toString();
    }
}
