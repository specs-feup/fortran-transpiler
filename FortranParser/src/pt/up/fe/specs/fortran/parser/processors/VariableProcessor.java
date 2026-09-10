package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.nodes.decl.DesignatorVariable;
import pt.up.fe.specs.fortran.ast.nodes.expr.dataref.NameDataRef;
import pt.up.fe.specs.fortran.ast.nodes.expr.dataref.StructureComponent;
import pt.up.fe.specs.fortran.ast.nodes.expr.enums.ScopeKind;
import pt.up.fe.specs.fortran.parser.FlangName;
import pt.up.fe.specs.fortran.parser.FortranJsonResult;

public class VariableProcessor extends ANodeProcessor {
    public VariableProcessor(FortranJsonResult data) {
        super(data);
    }

    public void nameDataRef(NameDataRef dataRef) {
        var name = attributes().getString(dataRef, "source", FlangName.NAME);
        var scope = attributes()
                .getOptionalString(dataRef, "scope", FlangName.NAME)
                .flatMap(ScopeKind::of);
        dataRef.set(NameDataRef.NAME, name);
        dataRef.set(NameDataRef.SCOPE, scope);
    }

    public void structureComponent(StructureComponent structureComponent) {
        var base = getChild(structureComponent, "base");
        structureComponent.addChild(base);

        var componentNameId = attributes().getString(structureComponent, "component");
        var componentName = attributes().get(componentNameId).getString("source");
        structureComponent.set(StructureComponent.COMPONENT_NAME, componentName);
    }

    public void designatorVariable(DesignatorVariable variable) {
        var designator = getChild(variable, FlangName.DESIGNATOR);
        variable.addChild(designator);
    }
}
