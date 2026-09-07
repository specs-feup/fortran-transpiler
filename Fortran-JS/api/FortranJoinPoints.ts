import * as Joinpoints from "./Joinpoints.ts";
import { unwrapJoinPoint, wrapJoinPoint } from "@specs-feup/lara/api/LaraJoinPoint.ts";
import Weaver from "@specs-feup/lara/api/weaver/Weaver.ts";
import FortranJavaTypes from "./FortranJavaTypes.ts";
import { flattenArgsArray } from "@specs-feup/lara/api/lara/core/LaraCore.ts";

export default class FortranJoinPoints {
    static ompLoopConstruct(loop: Joinpoints.DoStatement, clauses: Joinpoints.OmpClause[]): Joinpoints.OmpLoopConstruct {
        return wrapJoinPoint(FortranJavaTypes.AstFactory.ompLoopConstruct(
            Weaver.getWeaverEngine(),
            unwrapJoinPoint(loop),
            flattenArgsArray(clauses).map(unwrapJoinPoint)
        ));
    }

    static emptyOmpLoopConstruct(): Joinpoints.OmpLoopConstruct {
        return wrapJoinPoint(FortranJavaTypes.AstFactory.emptyOmpLoopConstruct(Weaver.getWeaverEngine()));
    }

    static ompPrivateClause(dataRefs: Joinpoints.DataRef[]): Joinpoints.OmpDataSharingClause {
        return wrapJoinPoint(FortranJavaTypes.AstFactory.ompPrivateClause(
            Weaver.getWeaverEngine(),
            flattenArgsArray(dataRefs).map(unwrapJoinPoint)
        ));
    }

    static dataRef(name: string): Joinpoints.DataRef {
        return wrapJoinPoint(FortranJavaTypes.AstFactory.dataRef(Weaver.getWeaverEngine(), name));
    }

    static useStatement(moduleName: string): Joinpoints.UseStatement {
        return wrapJoinPoint(FortranJavaTypes.AstFactory.useStatement(Weaver.getWeaverEngine(), moduleName));
    }

    static ompReductionClause(operator: string, dataRefs: Joinpoints.DataRef[]): Joinpoints.OmpReductionClause {
        return wrapJoinPoint(FortranJavaTypes.AstFactory.ompReductionClause(
            Weaver.getWeaverEngine(),
            operator,
            flattenArgsArray(dataRefs).map(unwrapJoinPoint)
        ));
    }

    static emptyOmpBlockConstruct(): Joinpoints.OmpBlockConstruct {
        return wrapJoinPoint(FortranJavaTypes.AstFactory.emptyOmpBlockConstruct(Weaver.getWeaverEngine()));
    }

    static execution(stmts: Joinpoints.ExecutableStatement[]): Joinpoints.Execution {
        return wrapJoinPoint(FortranJavaTypes.AstFactory.execution(
            Weaver.getWeaverEngine(),
            flattenArgsArray(stmts).map(unwrapJoinPoint)
        ));
    }

    static ompOrderedClause(value: number): Joinpoints.OmpOrderedClause {
        return wrapJoinPoint(FortranJavaTypes.AstFactory.ompOrderedClause(Weaver.getWeaverEngine(), value));
    }

    static intLiteral(value: number): Joinpoints.IntLiteral {
        return wrapJoinPoint(FortranJavaTypes.AstFactory.intLiteral(Weaver.getWeaverEngine(), value));
    }

    static binaryOperatorAdd(lhs: Joinpoints.Expr, rhs: Joinpoints.Expr): Joinpoints.BinaryOperator {
        return wrapJoinPoint(FortranJavaTypes.AstFactory.binaryOperatorAdd(
            Weaver.getWeaverEngine(),
            unwrapJoinPoint(lhs),
            unwrapJoinPoint(rhs)
        ));
    }

    static binaryOperatorSubtract(lhs: Joinpoints.Expr, rhs: Joinpoints.Expr): Joinpoints.BinaryOperator {
        return wrapJoinPoint(FortranJavaTypes.AstFactory.binaryOperatorSubtract(
            Weaver.getWeaverEngine(),
            unwrapJoinPoint(lhs),
            unwrapJoinPoint(rhs)
        ));
    }

    static binaryOperatorMultiply(lhs: Joinpoints.Expr, rhs: Joinpoints.Expr): Joinpoints.BinaryOperator {
        return wrapJoinPoint(FortranJavaTypes.AstFactory.binaryOperatorMultiply(
            Weaver.getWeaverEngine(),
            unwrapJoinPoint(lhs),
            unwrapJoinPoint(rhs)
        ));
    }

    static binaryOperatorDivide(lhs: Joinpoints.Expr, rhs: Joinpoints.Expr): Joinpoints.BinaryOperator {
        return wrapJoinPoint(FortranJavaTypes.AstFactory.binaryOperatorDivide(
            Weaver.getWeaverEngine(),
            unwrapJoinPoint(lhs),
            unwrapJoinPoint(rhs)
        ));
    }

    static rangeLoopControl(var_: Joinpoints.DataRef, lower: Joinpoints.Expr, upper: Joinpoints.Expr): Joinpoints.RangeLoopControl {
        return wrapJoinPoint(FortranJavaTypes.AstFactory.rangeLoopControl(
            Weaver.getWeaverEngine(),
            unwrapJoinPoint(var_), unwrapJoinPoint(lower), unwrapJoinPoint(upper)
        ));
    }

    static doStatement(control: Joinpoints.RangeLoopControl): Joinpoints.DoStatement {
        return wrapJoinPoint(FortranJavaTypes.AstFactory.doStatement(Weaver.getWeaverEngine(), unwrapJoinPoint(control)));
    }

    static intrinsicCall(name: string, args: Joinpoints.Expr[]): Joinpoints.Expr {
        return wrapJoinPoint(FortranJavaTypes.AstFactory.intrinsicCall(
            Weaver.getWeaverEngine(),
            name, args.map(unwrapJoinPoint)
        ));
    }
}
