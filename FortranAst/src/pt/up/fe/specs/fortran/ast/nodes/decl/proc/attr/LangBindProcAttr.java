package pt.up.fe.specs.fortran.ast.nodes.decl.proc.attr;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.specification.LanguageBindingSpec;

import java.util.Collection;

public class LangBindProcAttr extends ProcAttr {
    public LangBindProcAttr(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public LanguageBindingSpec getLanguageBindingSpec() {
        return getChild(LanguageBindingSpec.class, 0);
    }

    @Override
    public String getCode() {
        return getLanguageBindingSpec().getCode();
    }
}
