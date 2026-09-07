import Query from "@specs-feup/lara/api/weaver/Query.ts";
import LoopUnrollPass from "../pass/LoopUnrollPass.ts";
import { Subroutine } from "../Joinpoints.ts";

const FACTOR = 4;

const targetSubroutine = Query.search(Subroutine, { moduleName: 'kernel_3mm'}).getFirst();

if (targetSubroutine) {
  console.log(`Found ${targetSubroutine.moduleName}, unrolling innermost loops by factor ${FACTOR}`);

  const loopUnrollPass = new LoopUnrollPass(FACTOR);
  loopUnrollPass.apply(targetSubroutine);
} else {
  console.log(`Finish: targetSubroutine has not been found`);
}
