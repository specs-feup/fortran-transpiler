import JavaTypes, {
    type JavaClasses,
} from "@specs-feup/lara/api/lara/util/JavaTypes.ts";

// oxlint-disable-next-line typescript/no-namespace
export namespace FortranJavaClasses {
    /* oxlint-disable typescript/no-empty-object-type */
    export interface AstFactory extends JavaClasses.JavaClass {}
    /* oxlint-enable typescript/no-empty-object-type */
}

export default class FortranJavaTypes {
    static get AstFactory() {
        return JavaTypes.getType(
            "pt.up.fe.specs.fortran.weaver.importable.AstFactory"
        ) as FortranJavaClasses.AstFactory;
    }
}
