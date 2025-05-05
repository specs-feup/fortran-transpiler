/**
 * Copyright 2020 SPeCS.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License. under the License.
 */

package pt.up.fe.specs.fortran.ast;

import org.suikasoft.jOptions.DataStore.ADataClass;
import org.suikasoft.jOptions.Datakey.DataKey;
import org.suikasoft.jOptions.Datakey.KeyFactory;
import pt.up.fe.specs.util.utilities.IdGenerator;

/**
 * Represents global information about the program and common utilities.
 *
 * @author JoaoBispo
 */
public class FortranContext extends ADataClass<FortranContext> {

    /**
     * IDs generator
     */
    public final static DataKey<IdGenerator> ID_GENERATOR = KeyFactory
            .object("idGenerator", IdGenerator.class)
            .setDefault(() -> new IdGenerator())
            .setCopyFunction(id -> new IdGenerator(id));

    /**
     * Factory for building nodes.
     */
    public final static DataKey<FortranNodeFactory> FACTORY = KeyFactory.object("factory", FortranNodeFactory.class);

    @Override
    public String toString() {
        return "FortranContext " + hashCode();

    }
}
