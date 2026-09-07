package pt.up.fe.specs.fortran.weaver.joinpoints;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Stream;

import org.lara.interpreter.weaver.interf.enums.InsertPosition;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.utils.Position;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AJoinpoint;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AProgram;
import pt.up.fe.specs.util.SpecsLogs;
import pt.up.fe.specs.util.treenode.NodeInsertUtils;

/**
 * Editable class which contains the implementation shared by all join points.
 * This class will NOT be overwritten by the generator.
 */
public class FortranJoinpoint<Self extends FortranJoinpoint<Self>> extends AJoinpoint<Self> {

    public FortranJoinpoint(FortranNode node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public FortranWeaver getWeaverEngine() {
        return (FortranWeaver) super.getWeaverEngine();
    }

    @Override
    public FortranNode getNodeImpl() {
        return (FortranNode) super.getNodeImpl();
    }

    @Override
    public boolean getSameImpl(AJoinpoint<?> other) {
        return this.get_class().equals(other.get_class()) && this.getNodeImpl().equals(other.getNodeImpl());
    }

    /**
     * Compares the two join points based on their node reference of the used compiler/parsing tool.<br>
     * This is the default implementation for comparing two join points. <br>
     * <b>Note for developers:</b> A weaver may override this implementation in the editable abstract join point, so
     * the changes are made for all join points, or override this method in specific join points.
     */
    @Override
    public boolean getCompareNodesImpl(AJoinpoint<?> aJoinPoint) {
        return this.getNodeImpl().equals(aJoinPoint.getNodeImpl());
    }

    @Override
    public boolean getEqualsImpl(Self jp) {
        if (!(jp instanceof AJoinpoint)) {
            return false;
        }

        return this.getSameImpl(jp);
    }

    @Override
    public AProgram<?> getRootImpl() {
        return FortranJoinpoints.create(getWeaverEngine().getRootNode(), getWeaverEngine(), AProgram.class);
    }

    @Override
    public AJoinpoint<?> getParentImpl() {
        return FortranJoinpoints.create(getNodeImpl().getParent(), getWeaverEngine());
    }

    @Override
    public AJoinpoint<?> getJpParent() {
        return getParentImpl();
    }

    @Override
    public AJoinpoint<?>[] getChildrenImpl() {
        return FortranJoinpoints.create(getNodeImpl().getChildren(), getWeaverEngine(), AJoinpoint.class);
    }

    @Override
    public AJoinpoint<?>[] getDescendantsImpl() {
        return FortranJoinpoints.create(getNodeImpl().getDescendants(), getWeaverEngine(), AJoinpoint.class);
    }

    @Override
    public AJoinpoint<?>[] getScopeNodesImpl() {
        return getChildrenImpl();
    }

    @Override
    public Stream<AJoinpoint<?>> getJpChildrenStream() {
        return getNodeImpl().getChildrenStream()
                .map(node -> (AJoinpoint<?>) FortranJoinpoints.create(node, getWeaverEngine()));
    }

    @Override
    public String getCodeImpl() {
        return getNodeImpl().getCode();
    }

    @Override
    public Integer getLineImpl() {
        // The Fortran AST has no source location information
        return null;
    }

    @Override
    public Integer getColumnImpl() {
        // The Fortran AST has no source location information
        return null;
    }

    @Override
    public boolean getContainsImpl(AJoinpoint<?> jp) {
        FortranNode node = jp.getNodeImpl();

        return getNodeImpl().getDescendantsStream()
                .anyMatch(child -> child == node);
    }

    @Override
    public AJoinpoint<?> getGetAncestorImpl(String type) {
        Objects.requireNonNull(type, () -> "Missing type of ancestor in attribute 'ancestor'");

        if (type.equals("program")) {
            SpecsLogs.warn("Consider using attribute .root, instead of .ancestor('program')");
        }

        FortranNode currentNode = getNodeImpl();
        while (currentNode.hasParent()) {
            // Create join point for testing type
            AJoinpoint<?> parentJp = FortranJoinpoints.create(currentNode.getParent(), getWeaverEngine());

            if (parentJp.getInstanceOfImpl(type)) {
                return parentJp;
            }

            currentNode = parentJp.getNodeImpl();
        }

        return null;
    }

    @Override
    public int getIndexOfSelfImpl() {
        return getNodeImpl().indexOfSelf();
    }

    @Override
    public AJoinpoint<?> getLeftJpImpl() {
        return getNodeImpl().getLeft()
                .map(node -> FortranJoinpoints.create(node, getWeaverEngine()))
                .orElse(null);
    }

    @Override
    public AJoinpoint<?> getRightJpImpl() {
        return getNodeImpl().getRight()
                .map(node -> FortranJoinpoints.create(node, getWeaverEngine()))
                .orElse(null);
    }

    @Override
    public AJoinpoint<?>[] insertImpl(InsertPosition position, String code) {
        var insertedNode = getNodeImpl().insert(toPosition(position), code);

        return new AJoinpoint<?>[] { FortranJoinpoints.create(insertedNode, getWeaverEngine()) };
    }

    @Override
    public AJoinpoint<?>[] insertImpl(InsertPosition position, AJoinpoint<?> node) {
        var insertedNode = getNodeImpl().insert(toPosition(position), node.getNodeImpl());

        return new AJoinpoint<?>[] { FortranJoinpoints.create(insertedNode, getWeaverEngine()) };
    }

    private static Position toPosition(InsertPosition position) {
        return Position.valueOf(position.name());
    }

    @Override
    public AJoinpoint<?> insertAfterImpl(AJoinpoint<?> node) {
        var insertedNode = getNodeImpl().insert(Position.AFTER, node.getNodeImpl());

        return FortranJoinpoints.create(insertedNode, getWeaverEngine());
    }

    @Override
    public AJoinpoint<?> insertBeforeImpl(AJoinpoint<?> node) {
        var insertedNode = getNodeImpl().insert(Position.BEFORE, node.getNodeImpl());

        return FortranJoinpoints.create(insertedNode, getWeaverEngine());
    }

    @Override
    public AJoinpoint<?> insertAfterImpl(String code) {
        var insertedNode = getNodeImpl().insert(Position.AFTER, code);

        return FortranJoinpoints.create(insertedNode, getWeaverEngine());
    }

    @Override
    public AJoinpoint<?> insertBeforeImpl(String code) {
        var insertedNode = getNodeImpl().insert(Position.BEFORE, code);

        return FortranJoinpoints.create(insertedNode, getWeaverEngine());
    }

    public static FortranNode replace(FortranNode target, FortranNode newNode) {
        return NodeInsertUtils.replace(target, newNode);
    }

    @Override
    public AJoinpoint<?> replaceWithImpl(AJoinpoint<?> node) {
        return FortranJoinpoints.create(replace(getNodeImpl(), node.getNodeImpl()), getWeaverEngine());
    }

    @Override
    public AJoinpoint<?> replaceWithImpl(AJoinpoint<?>[] node) {
        // Insert nodes after in reverse order, to preserve order of comments and pragmas
        var reverseNodes = Arrays.asList(node);
        Collections.reverse(reverseNodes);

        AJoinpoint<?> topInserted = null;
        for (var nodeToInsert : reverseNodes) {
            topInserted = insertAfterImpl(nodeToInsert);
        }

        // Remove current node from the tree
        detachImpl();

        // Return the first inserted element
        return topInserted;
    }

    @Override
    public AJoinpoint<?> detachImpl() {
        FortranNode node = getNodeImpl();

        if (!node.hasParent()) {
            SpecsLogs.msgInfo(
                    "action detach: could not find a parent in joinpoint of type '" + getJoinPointTypeImpl() + "'");
            return this;
        }

        node.detach();
        return this;
    }

    @Override
    public int hashCode() {
        return getNodeImpl().hashCode();
    }

    @Override
    public AJoinpoint<?> copyImpl() {
        FortranNode copiedNode = getNodeImpl().copyShallow();
        return FortranJoinpoints.create(copiedNode, getWeaverEngine());
    }

    @Override
    public AJoinpoint<?> deepCopyImpl() {
        FortranNode copiedNode = getNodeImpl().copy();
        return FortranJoinpoints.create(copiedNode, getWeaverEngine());
    }
}
