package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.program.Specification;
import pt.up.fe.specs.fortran.ast.nodes.stmt.UseStmt;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ASpecification;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ASpecificationStatement;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AUseStatement;

public class FSpecification extends ASpecification {

    public final Specification specification;

    public FSpecification(Specification specification, FortranWeaver weaver) {
        super(new FStatementBlock(specification, weaver), weaver);
        this.specification = specification;
    }

    @Override
    public ASpecificationStatement[] getSpecificationStmtsArrayImpl() {
        return specification.getSpecificationStatements()
                .stream()
                .map(node -> FortranJoinpoints.create(node, getWeaverEngine()))
                .toList()
                .toArray(new ASpecificationStatement[0]);
    }

    @Override
    public void addUseStmtImpl(AUseStatement stmt) {
        specification.addUseStmt((UseStmt) stmt.getNode());
    }

    @Override
    public FortranNode getNode() {
        return null;
    }
}
