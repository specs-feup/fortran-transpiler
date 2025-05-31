package pt.up.fe.specs.fortran.parser;

import pt.up.fe.specs.util.SpecsStrings;
import pt.up.fe.specs.util.enums.EnumHelper;
import pt.up.fe.specs.util.lazy.Lazy;
import pt.up.fe.specs.util.providers.StringProvider;

import java.util.Optional;

public enum FlangName implements StringProvider {
    PROGRAM,
    MAIN_PROGRAM,
    PROGRAM_UNIT;

    private static final Lazy<EnumHelper<FlangName>> HELPER = EnumHelper.newLazyHelper(FlangName.class);

    private final String string;

    FlangName() {
        this.string = SpecsStrings.toCamelCase(name());
    }

    @Override
    public String getString() {
        return string;
    }

    /**
     * Converts string to enum using the name defined in this enum string (e.g., Program instead of PROGRAM).
     *
     * @param name
     * @return
     */
    public static Optional<FlangName> convertTry(String name) {
        return HELPER.get().fromNameTry(name);
    }

}
