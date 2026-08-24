package pt.up.fe.specs.fortran.ast.nodes.decl.proc.attr;

import org.suikasoft.jOptions.Datakey.DataKey;
import org.suikasoft.jOptions.Datakey.KeyFactory;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.decl.enums.ProcAttrKind;

import java.util.Collection;

public class OtherProcAttr extends ProcAttr {
    public static final DataKey<ProcAttrKind> KIND = KeyFactory.enumeration("kind", ProcAttrKind.class);

    public OtherProcAttr(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public ProcAttrKind getKind() {
        return get(KIND);
    }

    @Override
    public String getCode() {
        return encase(getKind().name());
    }
}
