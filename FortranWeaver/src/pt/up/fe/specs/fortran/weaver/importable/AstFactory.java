package pt.up.fe.specs.fortran.weaver.importable;

import pt.up.fe.specs.fortran.ast.nodes.expr.Argument;
import pt.up.fe.specs.fortran.ast.nodes.expr.DataRef;
import pt.up.fe.specs.fortran.ast.nodes.expr.Expr;
import pt.up.fe.specs.fortran.ast.nodes.expr.enums.BinaryOperatorKind;
import pt.up.fe.specs.fortran.ast.nodes.loops.RangeLoopControl;
import pt.up.fe.specs.fortran.ast.nodes.omp.clause.OmpClause;
import pt.up.fe.specs.fortran.ast.nodes.omp.enums.OmpClauseKind;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ExecutableStmt;
import pt.up.fe.specs.fortran.ast.nodes.stmt.loop.DoConstruct;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.*;
import pt.up.fe.specs.util.SpecsCollections;

import java.util.List;
import java.util.stream.Collectors;

public class AstFactory {
    public static AOmpLoopConstruct ompLoopConstruct(FortranWeaver weaver, ADoStatement loop, Object[] args) {
        List<OmpClause> clauses = SpecsCollections.asListT(AOmpClause.class, args)
                .stream()
                .map(clause -> (OmpClause) clause.getNodeImpl())
                .toList();

        var doConstruct = (DoConstruct) loop.getNodeImpl();

        return FortranJoinpoints.create(weaver.getFactory().ompLoopConstruct(doConstruct, clauses), weaver, AOmpLoopConstruct.class);
    }

    public static AOmpLoopConstruct emptyOmpLoopConstruct(FortranWeaver weaver) {
        return FortranJoinpoints.create(weaver.getFactory().emptyOmpLoopConstruct(), weaver, AOmpLoopConstruct.class);
    }

    public static AOmpBlockConstruct emptyOmpBlockConstruct(FortranWeaver weaver) {
        return FortranJoinpoints.create(weaver.getFactory().emptyOmpBlockConstruct(), weaver, AOmpBlockConstruct.class);
    }

    public static AOmpDataSharingClause ompPrivateClause(FortranWeaver weaver, Object[] args) {
        List<DataRef> dataRefs = SpecsCollections.asListT(ADataRef.class, args)
                .stream()
                .map(clause -> (DataRef) clause.getNodeImpl())
                .toList();

        return FortranJoinpoints.create(weaver.getFactory().ompDataSharingClause(OmpClauseKind.PRIVATE, dataRefs), weaver, AOmpDataSharingClause.class);
    }

    public static ADataRef dataRef(FortranWeaver weaver, String name) {
        return FortranJoinpoints.create(weaver.getFactory().dataRef(name), weaver, ADataRef.class);
    }

    public static AUseStatement useStatement(FortranWeaver weaver, String moduleName) {
        return FortranJoinpoints.create(weaver.getFactory().useStmt(moduleName), weaver, AUseStatement.class);
    }

    public static AOmpReductionClause ompReductionClause(FortranWeaver weaver, String operator, Object[] args) {
        List<DataRef> dataRefs = SpecsCollections.asListT(ADataRef.class, args)
                .stream()
                .map(clause -> (DataRef) clause.getNodeImpl())
                .toList();

        BinaryOperatorKind kind = BinaryOperatorKind.valueOf(operator);

        return FortranJoinpoints.create(weaver.getFactory().ompReductionClause(kind, dataRefs), weaver, AOmpReductionClause.class);
    }

    public static AExecution execution(FortranWeaver weaver, Object[] args) {
        List<ExecutableStmt> stmts = SpecsCollections.asListT(AExecutableStatement.class, args)
                .stream()
                .map(stmt -> (ExecutableStmt) stmt.getNodeImpl())
                .toList();

        return FortranJoinpoints.create(weaver.getFactory().execution(stmts), weaver, AExecution.class);
    }

    public static AOmpOrderedClause ompOrderedClause(FortranWeaver weaver, int value) {
        return FortranJoinpoints.create(weaver.getFactory().ompOrderedClause(value), weaver, AOmpOrderedClause.class);
    }

    public static AIntLiteral intLiteral(FortranWeaver weaver, int value) {
        return FortranJoinpoints.create(weaver.getFactory().intLiteral(value), weaver, AIntLiteral.class);
    }

    public static ABinaryOperator binaryOperatorAdd(FortranWeaver weaver, AExpr lhs, AExpr rhs) {
        return binaryOperator(weaver, BinaryOperatorKind.ADD, lhs, rhs);
    }

    public static ABinaryOperator binaryOperatorSubtract(FortranWeaver weaver, AExpr lhs, AExpr rhs) {
        return binaryOperator(weaver, BinaryOperatorKind.SUBTRACT, lhs, rhs);
    }

    public static ABinaryOperator binaryOperatorMultiply(FortranWeaver weaver, AExpr lhs, AExpr rhs) {
        return binaryOperator(weaver, BinaryOperatorKind.MULTIPLY, lhs, rhs);
    }

    public static ABinaryOperator binaryOperatorDivide(FortranWeaver weaver, AExpr lhs, AExpr rhs) {
        return binaryOperator(weaver, BinaryOperatorKind.DIVIDE, lhs, rhs);
    }

    public static ARangeLoopControl rangeLoopControl(FortranWeaver weaver, ADataRef var, AExpr lower, AExpr upper) {
        DataRef varNode = (DataRef) var.getNodeImpl();
        Expr lowerNode = (Expr) lower.getNodeImpl();
        Expr upperNode = (Expr) upper.getNodeImpl();
        return FortranJoinpoints.create(
                weaver.getFactory().rangeLoopControl(varNode, lowerNode, upperNode),
                weaver,
                ARangeLoopControl.class
        );
    }

    public static ADoStatement doStatement(FortranWeaver weaver, ARangeLoopControl control) {
        RangeLoopControl ctrl = (RangeLoopControl) control.getNodeImpl();
        return FortranJoinpoints.create(
                weaver.getFactory().doConstruct(ctrl),
                weaver,
                ADoStatement.class
        );
    }

    public static AExpr intrinsicCall(FortranWeaver weaver, String name, Object[] args) {
        DataRef callee = weaver.getFactory().dataRef(name);
        List<Argument> argNodes = SpecsCollections.asListT(AExpr.class, args)
                .stream()
                .map(a -> weaver.getFactory().argument((Expr) a.getNodeImpl()))
                .collect(Collectors.toList());
        return FortranJoinpoints.create(
                weaver.getFactory().functionCall(callee, argNodes),
                weaver,
                AExpr.class);
    }

    private static ABinaryOperator binaryOperator(FortranWeaver weaver, BinaryOperatorKind kind, AExpr lhs, AExpr rhs) {
        Expr lhsNode = (Expr) lhs.getNodeImpl();
        Expr rhsNode = (Expr) rhs.getNodeImpl();
        return FortranJoinpoints.create(
                weaver.getFactory().binaryOperator(kind, lhsNode, rhsNode),
                weaver,
                ABinaryOperator.class
        );
    }
}
