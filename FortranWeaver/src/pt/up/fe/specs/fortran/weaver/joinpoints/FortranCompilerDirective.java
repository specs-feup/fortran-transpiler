package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.stmt.CompilerDirective;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ANameValue;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ACompilerDirective;

public class FortranCompilerDirective<Self extends FortranCompilerDirective<Self>> extends ACompilerDirective<Self> {

    public FortranCompilerDirective(CompilerDirective node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public CompilerDirective getNodeImpl() {
        return (CompilerDirective) super.getNodeImpl();
    }

    @Override
    public ANameValue<?>[] getPairsImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getPairs(), getWeaverEngine(), ANameValue.class);
    }

    @Override
    public String getDirectiveStringImpl() {
        return this.getNodeImpl().getDirectiveString();
    }
}
