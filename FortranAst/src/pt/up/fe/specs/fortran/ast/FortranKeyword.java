package pt.up.fe.specs.fortran.ast;

public enum FortranKeyword {
    END,
    PROGRAM;

    public String getKeyword(boolean lowercase) {
        var keyword = name();
        return lowercase ? keyword.toLowerCase() : keyword;
    }
}
