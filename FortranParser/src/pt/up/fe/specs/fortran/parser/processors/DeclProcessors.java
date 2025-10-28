package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.nodes.decl.EntityDecl;
import pt.up.fe.specs.fortran.parser.FortranJsonResult;

public class DeclProcessors extends ANodeProcessor {


    public DeclProcessors(FortranJsonResult data) {
        super(data);
    }

    public void entityDecl(EntityDecl entityDecl) {
        var nameId = attributes(entityDecl).getString("Name");
        var name = attributes().get(nameId).getString("source");

        entityDecl.set(EntityDecl.NAME, name);

        // TODO: Constant is not creating a node in JSON
        /*
        if (attributes(entityDecl).has(FlangName.INITIALIZATION)) {
            var init = getChild(entityDecl, FlangName.INITIALIZATION);
            System.out.println("INIT: " + init);
        }
        */
    }


}
