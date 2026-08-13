package pt.up.fe.specs.fortran.ast.nodes.type.attributes;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.specification.LanguageBindingSpec;

import java.util.Collection;

public class LangBindAttrSpec extends AttrSpec {
    public LangBindAttrSpec(DataStore data, Collection<? extends FortranNode> children) {
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
