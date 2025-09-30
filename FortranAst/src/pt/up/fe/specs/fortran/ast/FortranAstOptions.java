package pt.up.fe.specs.fortran.ast;

import org.suikasoft.jOptions.Datakey.DataKey;
import org.suikasoft.jOptions.Datakey.KeyFactory;
import org.suikasoft.jOptions.storedefinition.StoreDefinition;
import org.suikasoft.jOptions.storedefinition.StoreDefinitionBuilder;
import org.suikasoft.jOptions.storedefinition.StoreDefinitionProvider;

public interface FortranAstOptions extends StoreDefinitionProvider {

    DataKey<Boolean> LOWERCASE_KEYWORDS = KeyFactory.bool("lowercaseKeywords")
            .setLabel("Generates keywords in lower-case");

    DataKey<Boolean> SINGLE_QUOTE_STRINGS = KeyFactory.bool("singleQuoteStrings")
            .setLabel("Generates string literals in single quotes");

    StoreDefinition STORE_DEFINITION = new StoreDefinitionBuilder("FortranOptions")
            .addKeys(LOWERCASE_KEYWORDS, SINGLE_QUOTE_STRINGS)
            .build();

    @Override
    default StoreDefinition getStoreDefinition() {
        return STORE_DEFINITION;
    }
}
