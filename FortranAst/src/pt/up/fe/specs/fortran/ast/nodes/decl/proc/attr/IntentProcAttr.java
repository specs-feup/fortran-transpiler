package pt.up.fe.specs.fortran.ast.nodes.decl.proc.attr;

import org.suikasoft.jOptions.Datakey.DataKey;
import org.suikasoft.jOptions.Datakey.KeyFactory;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.FortranKeyword;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.type.attributes.enums.IntentKind;

import java.util.Collection;

public class IntentProcAttr extends ProcAttr {
    public static final DataKey<IntentKind> INTENT_KIND = KeyFactory.enumeration("intentKind", IntentKind.class);

    public IntentProcAttr(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public IntentKind getIntentKind() {
        return get(INTENT_KIND);
    }

    @Override
    public String getCode() {
        return keyword(FortranKeyword.INTENT) + "(" + encase(getIntentKind().getString()) + ")";
    }
}
