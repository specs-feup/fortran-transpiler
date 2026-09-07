package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.stmt.Stmt;
import pt.up.fe.specs.util.exceptions.NotImplementedException;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AStatement;

public class FortranStatement<Self extends FortranStatement<Self>> extends AStatement<Self> {

    public FortranStatement(Stmt node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public Stmt getNodeImpl() {
        return (Stmt) super.getNodeImpl();
    }

    @Override
    public boolean getIsFirstImpl() {
        throw new NotImplementedException(this);
    }

    @Override
    public boolean getIsLastImpl() {
        throw new NotImplementedException(this);
    }
}
