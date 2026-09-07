package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.program.Specification;
import pt.up.fe.specs.fortran.ast.nodes.stmt.UseStmt;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ASpecificationStatement;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AUseStatement;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ASpecification;

public class FortranSpecification<Self extends FortranSpecification<Self>> extends ASpecification<Self> {

    public FortranSpecification(Specification node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public Specification getNodeImpl() {
        return (Specification) super.getNodeImpl();
    }

    @Override
    public ASpecificationStatement<?>[] getSpecificationStmtsImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getSpecificationStatements(), getWeaverEngine(), ASpecificationStatement.class);
    }

    @Override
    public void addUseStmtImpl(AUseStatement<?> stmt) {
        this.getNodeImpl().addUseStmt((UseStmt) stmt.getNodeImpl());
    }
}
