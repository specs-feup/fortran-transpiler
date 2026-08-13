package pt.up.fe.specs.fortran.ast.nodes.type.attributes.enums;

public enum AttrSpecKind {
    ALLOCATABLE,
    ASYNCHRONOUS,
    CONTIGUOUS,
    EXTERNAL,
    INTRINSIC,
    OPTIONAL,
    PARAMETER,
    POINTER,
    PROTECTED,
    SAVE,
    TARGET,
    VALUE,
    VOLATILE,

    // CUDA
    CONSTANT,
    DEVICE,
    MANAGED,
    PINNED,
    SHARED,
    TEXTURE;
}
