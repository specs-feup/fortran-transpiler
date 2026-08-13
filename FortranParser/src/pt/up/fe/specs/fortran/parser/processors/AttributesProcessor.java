package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.specification.enums.AccessKind;
import pt.up.fe.specs.fortran.ast.nodes.specification.shape.ArraySpec;
import pt.up.fe.specs.fortran.ast.nodes.specification.NamedConstantDef;
import pt.up.fe.specs.fortran.ast.nodes.type.attributes.*;
import pt.up.fe.specs.fortran.ast.nodes.type.attributes.enums.AttrSpecKind;
import pt.up.fe.specs.fortran.ast.nodes.type.attributes.enums.IntentKind;
import pt.up.fe.specs.fortran.parser.FlangName;
import pt.up.fe.specs.fortran.parser.FortranJsonResult;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AttributesProcessor extends ANodeProcessor {
    private static final Set<FlangName> KEYWORD_ATTRIBUTES = EnumSet.of(
            FlangName.ALLOCATABLE,
            FlangName.ASYNCHRONOUS
    );

    public AttributesProcessor(FortranJsonResult data) {
        super(data);
    }

    public void arraySpecification(ArraySpec arraySpecification) {
        var variantKey = attributes(arraySpecification).getVariantKey();
        List<FortranNode> shapes;
        Optional<FortranNode> additionalShape = Optional.empty();

        if (variantKey.equals(FlangName.ASSUMED_SIZE_SPEC.getString())) {
            String childSpec = attributes(arraySpecification).getVariantString();
            shapes = getChildren(childSpec, FlangName.EXPLICIT_SHAPE_SPEC);
            additionalShape = Optional.of(getChild(attributes().get(childSpec).getString(FlangName.ASSUMED_IMPLIED_SPEC)));
        }
        else if (variantKey.equals(FlangName.IMPLIED_SHAPE_SPEC.getString())) {
            String childSpec = attributes(arraySpecification).getVariantString();
            shapes = getChildren(childSpec, FlangName.ASSUMED_IMPLIED_SPEC);
        }
        else {
            shapes = getChildren(arraySpecification, variantKey);
        }

        arraySpecification.addChildren(shapes);
        additionalShape.ifPresent(arraySpecification::addChild);
    }

    public void accessAttrSpec(AccessAttrSpec accessAttrSpec) {
        var accessKindSrc = attributes().getString(accessAttrSpec, "value", FlangName.ACCESS_SPEC, FlangName.KIND);
        var accessKind = AccessKind.valueOf(accessKindSrc.toUpperCase());
        accessAttrSpec.set(AccessAttrSpec.ACCESS_KIND, accessKind);
    }

    public void codimAttrSpec(CodimAttrSpec codimAttrSpec) {
        var coarraySpec = getChild(codimAttrSpec, FlangName.COARRAY_SPEC);
        codimAttrSpec.addChild(coarraySpec);
    }

    public void dimAttrSpec(DimAttrSpec dimAttrSpec) {
        var arraySpec = getChild(dimAttrSpec, FlangName.ARRAY_SPEC);
        dimAttrSpec.addChild(arraySpec);
    }

    public void intentAttrSpec(IntentAttrSpec intentAttrSpec) {
        var intentKindSrc = attributes().getString(intentAttrSpec, "intent", FlangName.INTENT_SPEC);
        var intentKind = IntentKind.convertTry(intentKindSrc)
                .orElseThrow(() -> new RuntimeException("Invalid intent kind: " + intentKindSrc));
        intentAttrSpec.set(IntentAttrSpec.KIND, intentKind);
    }

    public void langBindAttrSpec(LangBindAttrSpec langBindAttrSpec) {
        var languageBindingSpec = getChild(langBindAttrSpec, FlangName.LANGUAGE_BINDING_SPEC);
        langBindAttrSpec.addChild(languageBindingSpec);
    }

    public void otherAttrSpec(OtherAttrSpec otherAttrSpec) {
        var variantKey = attributes(otherAttrSpec).getVariantKey();
        var kind = AttrSpecKind.valueOf(variantKey.toUpperCase());
        otherAttrSpec.set(OtherAttrSpec.KIND, kind);
    }

    public void namedConstantDef(NamedConstantDef namedConstantDef) {
        namedConstantDef.addChild(getChild(namedConstantDef, FlangName.NAMED_CONSTANT));
        namedConstantDef.addChild(getChild(namedConstantDef, FlangName.EXPR));
    }
}
