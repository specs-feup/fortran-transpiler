package pt.up.fe.specs.fortran.ast.nodes.expr.enums;

import pt.up.fe.specs.util.providers.StringProvider;

public enum BinaryOperatorKind implements StringProvider {
    Add,
    Sub,
    Mul,
    Div;

    public String getOpString() {
        switch (this) {
            case Add -> {
                return "+";
            }
            case Sub -> {
                return "-";
            }
            case Mul -> {
                return "*";
            }
            case Div -> {
                return "/";
            }
            default -> {
                return "<UNDEFINED_BINARY_OP_STRING:" + this + ">";
            }
        }
    }

    @Override
    public String getString() {
        return getOpString();
    }
}
