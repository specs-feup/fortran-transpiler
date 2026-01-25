package pt.up.fe.specs.fortran.ast.nodes.stmt;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.Expr;
import pt.up.fe.specs.fortran.ast.nodes.variable.DataRef;

import java.util.Collection;

public class AssignmentStmt extends ActionStmt {
    public AssignmentStmt(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    private DataRef getDataRef() {
        return getChild(DataRef.class);
    }

    private Expr getExpression() {
        return getChild(Expr.class);
    }

    @Override
    public String getCode() {
        // a = 1;
        var code = new StringBuilder();

        var dataRef = getDataRef();
        code.append(dataRef.getCode());

        var expression = getExpression();
        code.append(expression.getCode());

        return code.toString();
    }
}
