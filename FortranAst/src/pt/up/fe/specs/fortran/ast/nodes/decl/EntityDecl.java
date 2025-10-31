package pt.up.fe.specs.fortran.ast.nodes.decl;

import org.suikasoft.jOptions.Datakey.DataKey;
import org.suikasoft.jOptions.Datakey.KeyFactory;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.Expr;
import pt.up.fe.specs.fortran.ast.nodes.type.FortranType;

import java.util.Collection;
import java.util.Optional;

public class EntityDecl extends FortranDecl {

    // DATAKEYS BEGIN

    /**
     * The name of the entity.
     */
    public final static DataKey<String> NAME = KeyFactory.string("name");


    // DATAKEYS END

    public EntityDecl(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }


    public FortranType getType() {
        return getChild(FortranType.class, 0);
    }

    public Optional<Expr> getInitialization() {
        if (getNumChildren() < 2) {
            return Optional.empty();
        }

        return Optional.of(getChild(Expr.class, 1));
    }


    @Override
    public String getCode() {
        var code = new StringBuilder();

        code.append(get(NAME));

        getInitialization().ifPresent(init -> code.append(" = ").append(init.getCode()));

        return code.toString();
    }
}
