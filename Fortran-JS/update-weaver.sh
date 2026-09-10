#!/bin/sh

ROOT_DIR=$(dirname "$0")

cd "$ROOT_DIR/../FortranWeaver/" || exit 1
gradle installDist || exit 1
cd "$ROOT_DIR/../Fortran-JS/" || exit 1
rm -rf java-binaries
cp -r "$ROOT_DIR/../FortranWeaver/build/install/FortranWeaver/lib" java-binaries