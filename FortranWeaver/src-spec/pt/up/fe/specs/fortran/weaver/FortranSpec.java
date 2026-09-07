package pt.up.fe.specs.fortran.weaver;

import org.lara.langspec2.dsl.WeaverSpec;

/**
 * Weaver specification for the FortranWeaver (Metafor), translated from the XML
 * specification files (joinPointModel.xml, artifacts.xml and actionModel.xml)
 * into the Java DSL.
 * <p>
 * The weaver prefix is {@code Fortran}, so concrete join point classes follow
 * the {@code Fortran<JpName>} naming convention (e.g., {@code FortranProgram}
 * for the {@code program} join point). The previous concrete classes used the
 * {@code F<JpName>} prefix and were renamed accordingly, except for
 * {@code FDoConstruct} (the {@code doStatement} join point), which became
 * {@code FortranDoStatement}. The join point names visible to LARA are exactly
 * the ones declared in the old XML files.
 */
public class FortranSpec extends WeaverSpec {

    @Override
    public void define() {
        weaverPrefix("Fortran");
        packageName("pt.up.fe.specs.fortran.weaver");
        rootJoinPoint("program");

        enumDef("DoKind")
                .value("while")
                .value("range")
                .value("concurrent")
                .end();

        enumDef("BinaryOperatorKind")
                .value("ADD")
                .value("SUBTRACT")
                .value("MULTIPLY")
                .value("DIVIDE")
                .value("POWER")
                .value("LT")
                .value("LE")
                .value("GT")
                .value("GE")
                .value("EQ")
                .value("NE")
                .value("AND")
                .end();

        // =====================================================================
        // Global attributes and actions (weaver-specific, not in BaseJoinPointSpec)
        // Excludes base contract: dump, joinPointType, node, self, children,
        // descendants, scopeNodes, parent, code, line, column, toString, equals,
        // compareNodes, same, instanceOf, insert
        // =====================================================================

        global()
                .attribute("root", jpRef("program"), "Returns the 'program' join point")
                .attribute("leftJp", jpRef("joinpoint"),
                        "Returns the node that came before this node, or undefined if there is none")
                .attribute("rightJp", jpRef("joinpoint"),
                        "Returns the node that comes after this node, or undefined if there is none")
                .attribute("getAncestor")
                    .tooltip("Looks for an ancestor joinpoint name, walking back on the AST")
                    .param("type", STRING)
                    .returns(jpRef("joinpoint"))
                .attribute("contains")
                    .tooltip("true if the given node is a descendant of this node")
                    .param("jp", jpRef("joinpoint"))
                    .returns(BOOLEAN)
                .attribute("indexOfSelf", INT, "Returns the index of this join point in relation to its parent")
                .action("replaceWith")
                    .tooltip("Replaces this node with the given node")
                    .param("node", jpRef("joinpoint"))
                    .returns(jpRef("joinpoint"))
                .action("replaceWith")
                    .tooltip("Overload which accepts a list of nodes")
                    .param("node", array(jpRef("joinpoint")))
                    .returns(jpRef("joinpoint"))
                .action("detach")
                    .tooltip("Removes node associated to the joinpoint from the AST")
                    .returns(jpRef("joinpoint"))
                .action("insertBefore")
                    .tooltip("Inserts the given join point before this join point")
                    .param("node", jpRef("joinpoint"))
                    .returns(jpRef("joinpoint"))
                .action("insertBefore")
                    .tooltip("Overload which accepts a string")
                    .param("node", STRING)
                    .returns(jpRef("joinpoint"))
                .action("insertAfter")
                    .tooltip("Inserts the given join point after this join point")
                    .param("node", jpRef("joinpoint"))
                    .returns(jpRef("joinpoint"))
                .action("insertAfter")
                    .tooltip("Overload which accepts a string")
                    .param("code", STRING)
                    .returns(jpRef("joinpoint"))
                .action("copy")
                    .tooltip("Performs a copy of the node and its children, but not of the nodes in its fields")
                    .returns(jpRef("joinpoint"))
                .action("deepCopy")
                    .tooltip("Performs a copy of the node and its children, including the nodes in their fields (only the first level of field nodes, this function is not recursive)")
                    .returns(jpRef("joinpoint"));

        // =====================================================================
        // Join point definitions
        // =====================================================================

        joinPoint("program")
                .tooltip("Represents the complete program and is the top-most join point in the hierarchy");

        joinPoint("file")
                .tooltip("Represents a source file (e.g., .f90)")
                .attribute("name", STRING, "the name of the file")
                .attribute("foldername", STRING, "the name of the folder")
                .defaultAttribute("name");

        joinPoint("statement")
                .tooltip("Represents a Fortran statement")
                .attribute("isFirst", BOOLEAN)
                .attribute("isLast", BOOLEAN);

        joinPoint("expr")
                .tooltip("Represents an expression");

        joinPoint("binaryOperator")
                .extending("expr")
                .tooltip("Represents a binary operation")
                .attribute("left", jpRef("expr"))
                .attribute("right", jpRef("expr"))
                .attribute("kind", enumRef("BinaryOperatorKind"))
                .action("setLeft")
                    .tooltip("Sets the left-hand side of the operation")
                    .param("lhs", jpRef("expr"))
                    .returns(VOID)
                .action("setRight")
                    .tooltip("Sets the right-hand side of the operation")
                    .param("rhs", jpRef("expr"))
                    .returns(VOID);

        joinPoint("executableStatement")
                .extending("statement")
                .tooltip("Represents an executable statement");

        joinPoint("actionStatement")
                .extending("executableStatement")
                .tooltip("Represents an action statement");

        joinPoint("assignmentStatement")
                .extending("actionStatement")
                .tooltip("Represents an assignment statement")
                .attribute("variable", jpRef("dataRef"))
                .attribute("expr", jpRef("expr"));

        joinPoint("designator")
                .extending("expr")
                .tooltip("Represents a designator");

        joinPoint("dataRef")
                .extending("designator")
                .tooltip("Represents a data reference")
                .attribute("name", STRING);

        joinPoint("literal")
                .extending("expr")
                .tooltip("Represents a literal")
                .attribute("literal", STRING);

        joinPoint("intLiteral")
                .extending("literal");

        joinPoint("stringLiteral")
                .extending("literal");

        joinPoint("realLiteral")
                .extending("literal");

        joinPoint("doStatement")
                .extending("executableStatement")
                .tooltip("Represents a do loop")
                .attribute("control", jpRef("loopControl"))
                .attribute("body", jpRef("execution"))
                .attribute("kind", enumRef("DoKind"))
                .action("copyScope")
                    .tooltip("Performs a copy of the do statement, including its controls, but not the body")
                    .returns(jpRef("doStatement"))
                .action("sameScope")
                    .tooltip("Returns true if the given do statement has the same loop control as this do statement")
                    .param("loop", jpRef("doStatement"))
                    .returns(BOOLEAN);

        joinPoint("loopControl")
                .tooltip("Represents the loop control structure, with specialized subclasses for different loop kinds");

        joinPoint("rangeLoopControl")
                .extending("loopControl")
                .attribute("var", jpRef("dataRef"))
                .attribute("lower", jpRef("expr"))
                .attribute("upper", jpRef("expr"))
                .attribute("step", jpRef("expr"))
                .action("setUpper")
                    .param("upper", jpRef("expr"))
                    .returns(VOID)
                .action("setStep")
                    .param("step", jpRef("expr"))
                    .returns(VOID);

        joinPoint("statementBlock")
                .attribute("stmts", array(jpRef("statement")));

        joinPoint("execution")
                .extending("statementBlock")
                .attribute("executableStmts", array(jpRef("executableStatement")))
                .action("insertBegin")
                    .param("stmt", jpRef("executableStatement"))
                    .returns(VOID)
                .action("insertEnd")
                    .param("stmt", jpRef("executableStatement"))
                    .returns(VOID);

        joinPoint("specification")
                .extending("statementBlock")
                .attribute("specificationStmts", array(jpRef("specificationStatement")))
                .action("addUseStmt")
                    .tooltip("Adds a UseStmt to a specification part. The statement is inserted at the beginning.")
                    .param("stmt", jpRef("useStatement"))
                    .returns(VOID);

        joinPoint("arraySubscriptExpr")
                .extending("dataRef")
                .tooltip("Represents an access to one (or several) elements of an array")
                .attribute("var", jpRef("dataRef"))
                .attribute("subscripts", array(jpRef("sectionSubscript")));

        joinPoint("compilerDirective")
                .extending("executableStatement")
                .attribute("pairs", array(jpRef("nameValue")))
                .attribute("directiveString", STRING, "Returns the directive's contents as a string");

        joinPoint("nameValue")
                .tooltip("Represents a name/value pair in a compiler directive")
                .attribute("name", STRING);

        joinPoint("ompConstruct")
                .extending("executableStatement")
                .tooltip("Represents a generic OpenMP construct")
                .attribute("clauses", array(jpRef("ompClause")))
                .action("setClauses")
                    .tooltip("Sets the construct's clauses")
                    .param("clauses", array(jpRef("ompClause")))
                    .returns(VOID)
                .action("setDirective")
                    .param("directive", STRING)
                    .returns(VOID);

        joinPoint("ompBlockConstruct")
                .extending("ompConstruct")
                .tooltip("Represents an OpenMP block construct (such as parallel or task)")
                .action("setBody")
                    .param("body", jpRef("execution"))
                    .returns(VOID);

        joinPoint("ompLoopConstruct")
                .extending("ompConstruct")
                .tooltip("Represents an OpenMP loop construct (such as do or do parallel)")
                .action("setLoop")
                    .param("loop", jpRef("doStatement"))
                    .returns(VOID);

        joinPoint("ompClause")
                .tooltip("Represents an OpenMP clause");

        joinPoint("ompDataSharingClause")
                .extending("ompClause")
                .tooltip("Represents an OpenMP datasharing clause (public, private, ...)");

        joinPoint("ompReductionClause")
                .extending("ompClause");

        joinPoint("ompOrderedClause")
                .extending("ompClause");

        joinPoint("useStatement")
                .extending("statement")
                .attribute("moduleName", STRING);

        joinPoint("programUnit")
                .attribute("specification", jpRef("specification"), "Returns the unit's specification part");

        joinPoint("mainProgram")
                .extending("programUnit");

        joinPoint("subroutine")
                .extending("programUnit")
                .attribute("moduleName", STRING);

        joinPoint("specificationStatement")
                .extending("statement");

        joinPoint("typeDeclarationStatement")
                .extending("specificationStatement")
                .attribute("decls", array(jpRef("entityDecl")))
                .attribute("attrs", array(jpRef("attributeSpecifier")));

        joinPoint("attributeSpecifier");

        joinPoint("keywordAttributeSpecifier")
                .extending("attributeSpecifier");

        joinPoint("parameterKeyword")
                .extending("keywordAttributeSpecifier");

        joinPoint("fortranDecl");

        joinPoint("entityDecl")
                .extending("fortranDecl")
                .attribute("name", STRING);

        joinPoint("initialization");

        joinPoint("exprInitialization")
                .extending("initialization")
                .attribute("expr", jpRef("expr"));

        joinPoint("ifStatement")
                .extending("actionStatement")
                .attribute("condition", jpRef("expr"))
                .attribute("statement", jpRef("actionStatement"));

        joinPoint("ifConstruct")
                .extending("executableStatement")
                .tooltip("Represents the root of an if construct")
                .attribute("ifThenBlock", jpRef("ifThenBlock"))
                .attribute("elseIfBlocks", array(jpRef("elseIfBlock")))
                .attribute("elseBlock", jpRef("elseBlock"));

        joinPoint("ifThenBlock")
                .tooltip("Represents the first block of an if construct")
                .attribute("header", jpRef("ifThenStatement"))
                .attribute("body", jpRef("statementBlock"));

        joinPoint("ifThenStatement")
                .tooltip("Represents the header of an 'if then' block")
                .attribute("condition", jpRef("expr"));

        joinPoint("elseIfBlock")
                .tooltip("Represents the optional 'else if' blocks of an if construct")
                .attribute("header", jpRef("elseIfStatement"))
                .attribute("body", jpRef("statementBlock"));

        joinPoint("elseIfStatement")
                .tooltip("Represents the header of an 'else if' block")
                .attribute("condition", jpRef("expr"));

        joinPoint("elseBlock")
                .tooltip("Represents the optional 'else' block of an if construct")
                .attribute("body", jpRef("statementBlock"));

        joinPoint("sectionSubscript")
                .tooltip("Represent a generic subscript for an array");

        joinPoint("subscript")
                .extending("sectionSubscript")
                .tooltip("Represent an integer subscript for an array")
                .attribute("expr", jpRef("expr"));
    }
}
