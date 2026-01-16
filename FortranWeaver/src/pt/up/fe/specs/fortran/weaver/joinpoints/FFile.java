package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.program.FortranFile;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AFile;

public class FFile extends AFile {

    private final FortranFile file;

    public FFile(FortranFile file) {
        this.file = file;
    }

    @Override
    public FortranNode getNode() {
        return file;
    }

    @Override
    public String getNameImpl() {
        return file.get(FortranFile.FILE_NAME);
    }

    @Override
    public String getFoldernameImpl() {
        return file.get(FortranFile.FOLDER_NAME);
    }
}
