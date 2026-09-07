package pt.up.fe.specs.fortran.weaver;

import org.lara.interpreter.weaver.ast.AAstMethods;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AJoinpoint;

/**
 * Bridge between the Fortran AST and the interpreter, used for generic
 * traversal from the LARA side.
 */
public class FortranAstMethods extends AAstMethods<FortranNode> {

    private final FortranWeaver weaver;

    public FortranAstMethods(FortranWeaver weaver) {
        super(weaver);
        this.weaver = weaver;
    }

    @Override
    public Class<FortranNode> getNodeClass() {
        return FortranNode.class;
    }

    @Override
    protected AJoinpoint<?> toJavaJoinPointImpl(FortranNode node) {
        return FortranJoinpoints.create(node, weaver);
    }

    @Override
    protected String getJoinPointNameImpl(FortranNode node) {
        return toJavaJoinPointImpl(node).getJoinPointTypeImpl();
    }

    @Override
    protected Object[] getChildrenImpl(FortranNode node) {
        return node.getChildren().toArray();
    }

    @Override
    protected Object[] getScopeChildrenImpl(FortranNode node) {
        return getChildrenImpl(node);
    }

    @Override
    protected Object getParentImpl(FortranNode node) {
        return node.getParent();
    }
}
