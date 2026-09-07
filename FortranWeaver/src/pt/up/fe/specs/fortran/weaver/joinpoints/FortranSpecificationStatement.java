package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.stmt.SpecificationStmt;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ASpecificationStatement;

public class FortranSpecificationStatement<Self extends FortranSpecificationStatement<Self>> extends ASpecificationStatement<Self> {

    public FortranSpecificationStatement(SpecificationStmt node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public SpecificationStmt getNodeImpl() {
        return (SpecificationStmt) super.getNodeImpl();
    }
}
