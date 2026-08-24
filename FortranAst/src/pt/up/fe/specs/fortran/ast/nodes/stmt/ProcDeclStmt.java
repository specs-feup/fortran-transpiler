package pt.up.fe.specs.fortran.ast.nodes.stmt;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.FortranKeyword;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.decl.proc.ProcDecl;
import pt.up.fe.specs.fortran.ast.nodes.decl.proc.attr.ProcAttr;
import pt.up.fe.specs.fortran.ast.nodes.decl.proc.interfaces.ProcInterface;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProcDeclStmt extends SpecStmt {
    public ProcDeclStmt(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public Optional<ProcInterface> getProcInterface() {
        return getChildTry(ProcInterface.class, 0);
    }

    public List<ProcAttr> getProcAttrs() {
        return getChildrenOf(ProcAttr.class);
    }

    public List<ProcDecl> getProcDecls() {
        return getChildrenOf(ProcDecl.class);
    }

    @Override
    public String getStmtCode() {
        var interfaceCode = getProcInterface().map(ProcInterface::getCode).orElse("");
        var attrsCode = getProcAttrs().stream()
                .map(attr -> ", " + attr.getCode())
                .collect(Collectors.joining());
        var declsCode = getProcDecls().stream()
                .map(ProcDecl::getCode)
                .collect(Collectors.joining(", "));

        return keyword(FortranKeyword.PROCEDURE) + "(" + interfaceCode + ")" + attrsCode + " :: " + declsCode;
    }
}
