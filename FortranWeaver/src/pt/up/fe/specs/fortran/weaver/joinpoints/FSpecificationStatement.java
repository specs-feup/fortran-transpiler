package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.stmt.SpecStmt;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ASpecificationStatement;

public class FSpecificationStatement extends ASpecificationStatement {

    public final SpecStmt specStmt;

    public FSpecificationStatement(SpecStmt specStmt) {
        super(new FStatement(specStmt));
        this.specStmt = specStmt;
    }

    @Override
    public FortranNode getNode() {
        return specStmt;
    }
}
