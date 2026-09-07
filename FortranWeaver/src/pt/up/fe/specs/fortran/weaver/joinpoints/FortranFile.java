package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AFile;

public class FortranFile<Self extends FortranFile<Self>> extends AFile<Self> {

    public FortranFile(pt.up.fe.specs.fortran.ast.nodes.program.FortranFile node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public pt.up.fe.specs.fortran.ast.nodes.program.FortranFile getNodeImpl() {
        return (pt.up.fe.specs.fortran.ast.nodes.program.FortranFile) super.getNodeImpl();
    }

    @Override
    public String getNameImpl() {
        return this.getNodeImpl().get(pt.up.fe.specs.fortran.ast.nodes.program.FortranFile.FILE_NAME);
    }

    @Override
    public String getFoldernameImpl() {
        return this.getNodeImpl().get(pt.up.fe.specs.fortran.ast.nodes.program.FortranFile.FOLDER_NAME);
    }
}
