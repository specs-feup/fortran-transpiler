import Query from "@specs-feup/lara/api/weaver/Query.ts";
import { Subroutine } from "../Joinpoints.ts";
import LoopFissionPass from "../pass/LoopFissionPass.ts";


// Collect loops with more than one body statement before any modifications
const targetSubroutine = Query.search(Subroutine, ($jp) => $jp.moduleName === 'kernel_3mm').getFirst()


if (targetSubroutine) {
  console.log(`Found ${targetSubroutine.moduleName} loop(s) to fission`);

  const loopFisionPass = new LoopFissionPass()
  
  loopFisionPass.apply(targetSubroutine)
} else {
  console.log(`Finish: targetSubroutine has not been found`);
}
