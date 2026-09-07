package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.stmt.UseStmt;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AUseStatement;

public class FortranUseStatement<Self extends FortranUseStatement<Self>> extends AUseStatement<Self> {

    public FortranUseStatement(UseStmt node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public UseStmt getNodeImpl() {
        return (UseStmt) super.getNodeImpl();
    }

    @Override
    public String getModuleNameImpl() {
        return this.getNodeImpl().getName();
    }
}
