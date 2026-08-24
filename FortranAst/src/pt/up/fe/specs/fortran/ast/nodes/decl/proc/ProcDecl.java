package pt.up.fe.specs.fortran.ast.nodes.decl.proc;

import org.suikasoft.jOptions.Datakey.DataKey;
import org.suikasoft.jOptions.Datakey.KeyFactory;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.decl.init.ProcPointerInit;

import java.util.Collection;
import java.util.Optional;

public class ProcDecl extends FortranNode {
    public static final DataKey<String> NAME = KeyFactory.string("name");

    public ProcDecl(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public String getName() {
        return get(NAME);
    }

    public Optional<ProcPointerInit> getInit() {
        return getChildTry(ProcPointerInit.class, 0);
    }

    @Override
    public String getCode() {
        var name = getName();
        var initCode = getInit().map(ProcPointerInit::getCode).orElse("");

        return name + initCode;
    }
}
