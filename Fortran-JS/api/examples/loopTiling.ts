import Query from "@specs-feup/lara/api/weaver/Query.ts";
import LoopTilingPass from "../pass/LoopTilingPass.ts";
import { Subroutine } from "../Joinpoints.ts";

const TILE_SIZE = 32;

const targetSubroutine = Query.search(Subroutine, { moduleName: 'kernel_3mm' }).getFirst();

if (targetSubroutine) {
  console.log(`Found ${targetSubroutine.moduleName}, tiling loops with tile size ${TILE_SIZE}`);
  new LoopTilingPass(TILE_SIZE).apply(targetSubroutine);
} else {
  console.log('Target subroutine not found');
}
