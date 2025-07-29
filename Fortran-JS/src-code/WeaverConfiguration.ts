import WeaverConfiguration from "@specs-feup/lara/code/WeaverConfiguration.js";
import path from "path";
import { fileURLToPath } from "url";

export const weaverConfig: WeaverConfiguration = {
  weaverName: "metafor",
  weaverPrettyName: "Fortran Transpiler",
  weaverFileName: "@specs-feup/lara/code/Weaver.js",
  jarPath: path.join(
    path.dirname(path.dirname(fileURLToPath(import.meta.url))),
    "./java-binaries/"
  ),
  javaWeaverQualifiedName: "pt.up.fe.specs.fortran.weaver.FortranWeaver",
  importForSideEffects: [
    "@specs-feup/metafor/api/Joinpoints.js",
    "@specs-feup/metafor/code/sideEffects.js",
  ],
};
