import config from "@specs-feup/lara/oxlint.config.ts";
import { defineConfig } from "oxlint";

export default defineConfig({
  extends: [config],
  overrides: [
    {
      // Generated file: enum constants are declared together with a same-named
      // type alias (const objects are used instead of enums, which are not
      // erasable syntax).
      files: ["api/Joinpoints.ts"],
      rules: { "eslint/no-redeclare": "off" },
    },
  ],
});
