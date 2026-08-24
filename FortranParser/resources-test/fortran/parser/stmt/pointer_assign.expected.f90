PROGRAM POINTER_ASSIGN
    IMPLICIT NONE

    ! Targets
    INTEGER, TARGET :: scalar_target = 42
    INTEGER, TARGET :: array_target(5) = [10, 20, 30, 40, 50]

    ! Pointers
    INTEGER, POINTER :: ptr_scalar => null()
    INTEGER, POINTER :: ptr_copy => null()
    INTEGER, POINTER :: ptr_array(:) => null()
    INTEGER, POINTER :: ptr_slice(:) => null()
    INTEGER, POINTER :: ptr_lbound(:) => null()
    INTEGER, POINTER :: ptr_bounds(:) => null()
    INTEGER, POINTER :: ptr_2d(:, :) => null()

    ! Procedure Pointer
    ABSTRACT INTERFACE
        FUNCTION func_interface(x) RESULT(res)
            INTEGER, INTENT(IN) :: x
            INTEGER :: res
        END FUNCTION func_interface
    END INTERFACE
    PROCEDURE(func_interface), POINTER :: ptr_proc => null()

    ! 1. Scalar pointer assignment
    ptr_scalar => scalar_target
    PRINT *, "1. Scalar pointer:", ptr_scalar

    ! 2. Pointer-to-pointer assignment
    ptr_copy => ptr_scalar
    PRINT *, "2. Pointer copy:", ptr_copy

    ! 3. Whole array assignment
    ptr_array => array_target
    PRINT *, "3. Whole array:", ptr_array

    ! 4. Array section / slice assignment
    ptr_slice => array_target(2:4)
    PRINT *, "4. Array slice (2:4):", ptr_slice

    ! 5. Lower bound remapping
    ptr_lbound(10:) => array_target
    PRINT *, "5. Lower bound remapped | LBound:", lbound(ptr_lbound, 1), "| ptr(10):", ptr_lbound(10)

    ! 6. Full bounds remapping (same rank)
    ptr_bounds(0:4) => array_target
    PRINT *, "6. Full bounds remapped | LBound:", lbound(ptr_bounds, 1), "| ptr(0):", ptr_bounds(0)

    ! 7. Rank remapping (1D contiguous target to 2D pointer)
    ptr_2d(1:2, 1:2) => array_target(1:4)
    PRINT *, "7. Rank remapped 2D pointer (element 2,2):", ptr_2d(2, 2)

    ! 8. Procedure pointer assignment
    ptr_proc => double_val
    PRINT *, "8. Procedure pointer result:", ptr_proc(5)

    ! 9. Nullification pointer assignments
    ptr_scalar => null()
    ptr_copy => null()
    ptr_array => null()
    ptr_slice => null()
    ptr_lbound => null()
    ptr_bounds => null()
    ptr_2d => null()
    ptr_proc => null()

    PRINT *, "9. Any pointers still associated?", associated(ptr_scalar) .OR. associated(ptr_proc)

CONTAINS

    FUNCTION double_val(x) RESULT(res)
        INTEGER, INTENT(IN) :: x
        INTEGER :: res
        res = x * 2
    END FUNCTION double_val

END PROGRAM POINTER_ASSIGN