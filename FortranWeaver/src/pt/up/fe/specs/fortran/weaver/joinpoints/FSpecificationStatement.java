package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.stmt.SpecStmt;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ASpecificationStatement;

public class FSpecificationStatement extends ASpecificationStatement {

    public final SpecStmt specificationStmt;

    public FSpecificationStatement(SpecStmt specificationStmt) {
        super(new FStatement(specificationStmt));
        this.specificationStmt = specificationStmt;
    }

    @Override
    public FortranNode getNode() {
        return specificationStmt;
    }
}
