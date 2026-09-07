/**
 * Copyright 2016 SPeCS.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package pt.up.fe.specs.fortran.weaver;

import java.util.List;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.decl.EntityDecl;
import pt.up.fe.specs.fortran.ast.nodes.decl.ExprInitialization;
import pt.up.fe.specs.fortran.ast.nodes.decl.FortranDecl;
import pt.up.fe.specs.fortran.ast.nodes.decl.Initialization;
import pt.up.fe.specs.fortran.ast.nodes.expr.ArraySubscriptExpr;
import pt.up.fe.specs.fortran.ast.nodes.expr.BinaryOperator;
import pt.up.fe.specs.fortran.ast.nodes.expr.DataRef;
import pt.up.fe.specs.fortran.ast.nodes.expr.Designator;
import pt.up.fe.specs.fortran.ast.nodes.expr.Expr;
import pt.up.fe.specs.fortran.ast.nodes.expr.IntLiteral;
import pt.up.fe.specs.fortran.ast.nodes.expr.Literal;
import pt.up.fe.specs.fortran.ast.nodes.expr.RealLiteral;
import pt.up.fe.specs.fortran.ast.nodes.expr.SectionSubscript;
import pt.up.fe.specs.fortran.ast.nodes.expr.StringLiteral;
import pt.up.fe.specs.fortran.ast.nodes.expr.Subscript;
import pt.up.fe.specs.fortran.ast.nodes.loops.LoopControl;
import pt.up.fe.specs.fortran.ast.nodes.loops.RangeLoopControl;
import pt.up.fe.specs.fortran.ast.nodes.omp.OmpBlockConstruct;
import pt.up.fe.specs.fortran.ast.nodes.omp.OmpConstruct;
import pt.up.fe.specs.fortran.ast.nodes.omp.OmpLoopConstruct;
import pt.up.fe.specs.fortran.ast.nodes.omp.clause.OmpClause;
import pt.up.fe.specs.fortran.ast.nodes.omp.clause.OmpDataSharingClause;
import pt.up.fe.specs.fortran.ast.nodes.omp.clause.OmpOrderedClause;
import pt.up.fe.specs.fortran.ast.nodes.omp.clause.OmpReductionClause;
import pt.up.fe.specs.fortran.ast.nodes.program.Application;
import pt.up.fe.specs.fortran.ast.nodes.program.Execution;
import pt.up.fe.specs.fortran.ast.nodes.program.MainProgram;
import pt.up.fe.specs.fortran.ast.nodes.program.ProgramUnit;
import pt.up.fe.specs.fortran.ast.nodes.program.Specification;
import pt.up.fe.specs.fortran.ast.nodes.program.StmtBlock;
import pt.up.fe.specs.fortran.ast.nodes.program.Subroutine;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ActionStmt;
import pt.up.fe.specs.fortran.ast.nodes.stmt.AssignmentStmt;
import pt.up.fe.specs.fortran.ast.nodes.stmt.CompilerDirective;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ExecutableStmt;
import pt.up.fe.specs.fortran.ast.nodes.stmt.SpecificationStmt;
import pt.up.fe.specs.fortran.ast.nodes.stmt.Stmt;
import pt.up.fe.specs.fortran.ast.nodes.stmt.TypeDeclarationStmt;
import pt.up.fe.specs.fortran.ast.nodes.stmt.UseStmt;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.ElseBlock;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.ElseIfBlock;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.ElseIfStmt;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.IfConstruct;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.IfStmt;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.IfThenBlock;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.IfThenStmt;
import pt.up.fe.specs.fortran.ast.nodes.stmt.loop.DoConstruct;
import pt.up.fe.specs.fortran.ast.nodes.type.attributes.AttributeSpecifier;
import pt.up.fe.specs.fortran.ast.nodes.type.attributes.KeywordAttributeSpecifier;
import pt.up.fe.specs.fortran.ast.nodes.type.attributes.ParameterKeyword;
import pt.up.fe.specs.fortran.ast.nodes.utils.NameValue;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AJoinpoint;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranActionStatement;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranArraySubscriptExpr;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranAssignmentStatement;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranAttributeSpecifier;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranBinaryOperator;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranCompilerDirective;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranDataRef;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranDesignator;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranDoStatement;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranElseBlock;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranElseIfBlock;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranElseIfStatement;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranEntityDecl;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranExecutableStatement;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranExecution;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranExpr;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranExprInitialization;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranFile;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranFortranDecl;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranIfConstruct;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranIfStatement;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranIfThenBlock;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranIfThenStatement;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranInitialization;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranIntLiteral;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranJoinpoint;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranKeywordAttributeSpecifier;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranLiteral;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranLoopControl;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranMainProgram;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranNameValue;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranOmpBlockConstruct;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranOmpClause;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranOmpConstruct;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranOmpDataSharingClause;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranOmpLoopConstruct;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranOmpOrderedClause;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranOmpReductionClause;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranParameterKeyword;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranProgram;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranProgramUnit;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranRangeLoopControl;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranRealLiteral;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranSectionSubscript;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranSpecification;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranSpecificationStatement;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranStatement;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranStatementBlock;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranStringLiteral;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranSubroutine;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranSubscript;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranTypeDeclarationStatement;
import pt.up.fe.specs.fortran.weaver.joinpoints.FortranUseStatement;
import pt.up.fe.specs.util.SpecsCollections;
import pt.up.fe.specs.util.SpecsLogs;
import pt.up.fe.specs.util.classmap.BiFunctionClassMap;

public class FortranJoinpoints {

    private static final BiFunctionClassMap<FortranNode, FortranWeaver, AJoinpoint<?>> JOINPOINT_FACTORY;

    static {
        JOINPOINT_FACTORY = new BiFunctionClassMap<>();

        JOINPOINT_FACTORY.put(Application.class, FortranProgram::new);
        JOINPOINT_FACTORY.put(pt.up.fe.specs.fortran.ast.nodes.program.FortranFile.class, FortranFile::new);
        JOINPOINT_FACTORY.put(Stmt.class, FortranStatement::new);
        JOINPOINT_FACTORY.put(ActionStmt.class, FortranActionStatement::new);
        JOINPOINT_FACTORY.put(ArraySubscriptExpr.class, FortranArraySubscriptExpr::new);
        JOINPOINT_FACTORY.put(AssignmentStmt.class, FortranAssignmentStatement::new);
        JOINPOINT_FACTORY.put(BinaryOperator.class, FortranBinaryOperator::new);
        JOINPOINT_FACTORY.put(CompilerDirective.class, FortranCompilerDirective::new);
        JOINPOINT_FACTORY.put(DataRef.class, FortranDataRef::new);
        JOINPOINT_FACTORY.put(Designator.class, FortranDesignator::new);
        JOINPOINT_FACTORY.put(DoConstruct.class, FortranDoStatement::new);
        JOINPOINT_FACTORY.put(ExecutableStmt.class, FortranExecutableStatement::new);
        JOINPOINT_FACTORY.put(Execution.class, FortranExecution::new);
        JOINPOINT_FACTORY.put(Expr.class, FortranExpr::new);
        JOINPOINT_FACTORY.put(IntLiteral.class, FortranIntLiteral::new);
        JOINPOINT_FACTORY.put(Literal.class, FortranLiteral::new);
        JOINPOINT_FACTORY.put(LoopControl.class, FortranLoopControl::new);
        JOINPOINT_FACTORY.put(NameValue.class, FortranNameValue::new);
        JOINPOINT_FACTORY.put(RangeLoopControl.class, FortranRangeLoopControl::new);
        JOINPOINT_FACTORY.put(RealLiteral.class, FortranRealLiteral::new);
        JOINPOINT_FACTORY.put(StmtBlock.class, FortranStatementBlock::new);
        JOINPOINT_FACTORY.put(StringLiteral.class, FortranStringLiteral::new);
        JOINPOINT_FACTORY.put(Specification.class, FortranSpecification::new);
        JOINPOINT_FACTORY.put(OmpConstruct.class, FortranOmpConstruct::new);
        JOINPOINT_FACTORY.put(OmpClause.class, FortranOmpClause::new);
        JOINPOINT_FACTORY.put(OmpLoopConstruct.class, FortranOmpLoopConstruct::new);
        JOINPOINT_FACTORY.put(OmpBlockConstruct.class, FortranOmpBlockConstruct::new);
        JOINPOINT_FACTORY.put(OmpDataSharingClause.class, FortranOmpDataSharingClause::new);
        JOINPOINT_FACTORY.put(UseStmt.class, FortranUseStatement::new);
        JOINPOINT_FACTORY.put(ProgramUnit.class, FortranProgramUnit::new);
        JOINPOINT_FACTORY.put(Subroutine.class, FortranSubroutine::new);
        JOINPOINT_FACTORY.put(OmpReductionClause.class, FortranOmpReductionClause::new);
        JOINPOINT_FACTORY.put(OmpOrderedClause.class, FortranOmpOrderedClause::new);
        JOINPOINT_FACTORY.put(IfStmt.class, FortranIfStatement::new);
        JOINPOINT_FACTORY.put(IfConstruct.class, FortranIfConstruct::new);
        JOINPOINT_FACTORY.put(IfThenBlock.class, FortranIfThenBlock::new);
        JOINPOINT_FACTORY.put(IfThenStmt.class, FortranIfThenStatement::new);
        JOINPOINT_FACTORY.put(ElseIfBlock.class, FortranElseIfBlock::new);
        JOINPOINT_FACTORY.put(ElseIfStmt.class, FortranElseIfStatement::new);
        JOINPOINT_FACTORY.put(ElseBlock.class, FortranElseBlock::new);
        JOINPOINT_FACTORY.put(MainProgram.class, FortranMainProgram::new);
        JOINPOINT_FACTORY.put(SpecificationStmt.class, FortranSpecificationStatement::new);
        JOINPOINT_FACTORY.put(TypeDeclarationStmt.class, FortranTypeDeclarationStatement::new);
        JOINPOINT_FACTORY.put(AttributeSpecifier.class, FortranAttributeSpecifier::new);
        JOINPOINT_FACTORY.put(KeywordAttributeSpecifier.class, FortranKeywordAttributeSpecifier::new);
        JOINPOINT_FACTORY.put(ParameterKeyword.class, FortranParameterKeyword::new);
        JOINPOINT_FACTORY.put(FortranDecl.class, FortranFortranDecl::new);
        JOINPOINT_FACTORY.put(EntityDecl.class, FortranEntityDecl::new);
        JOINPOINT_FACTORY.put(Initialization.class, FortranInitialization::new);
        JOINPOINT_FACTORY.put(ExprInitialization.class, FortranExprInitialization::new);
        JOINPOINT_FACTORY.put(Subscript.class, FortranSubscript::new);
        JOINPOINT_FACTORY.put(SectionSubscript.class, FortranSectionSubscript::new);
        JOINPOINT_FACTORY.put(FortranNode.class, FortranJoinpoints::defaultFactory);
    }

    private static AJoinpoint<?> defaultFactory(FortranNode node, FortranWeaver weaver) {
        SpecsLogs.debug(() -> "Factory not defined for nodes of class '" + node.getClass().getSimpleName() + "'");
        return new FortranJoinpoint<>(node, weaver);
    }

    public static AJoinpoint<?> create(FortranNode node, FortranWeaver weaver) {
        if (node == null) {
            SpecsLogs.debug("FortranJoinpoints: tried to create join point from null node, returning undefined");
            return null;
        }

        return JOINPOINT_FACTORY.apply(node, weaver);
    }

    public static <T extends AJoinpoint<?>> T create(FortranNode node, FortranWeaver weaver, Class<T> targetClass) {
        if (targetClass == null) {
            throw new RuntimeException("Check if you meant to call 'create' with a single argument");
        }

        return targetClass.cast(create(node, weaver));
    }

    public static <T extends AJoinpoint<?>> T[] create(List<? extends FortranNode> nodes, FortranWeaver weaver,
            Class<T> targetClass) {
        return nodes.stream()
                .map(node -> create(node, weaver, targetClass))
                .toArray(size -> SpecsCollections.newArray(targetClass, size));
    }
}
