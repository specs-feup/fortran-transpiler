program pointer_assign
    implicit none

    ! Targets
    integer, target :: scalar_target = 42
    integer, target :: array_target(5) = [10, 20, 30, 40, 50]

    ! Pointers
    integer, pointer :: ptr_scalar => null()
    integer, pointer :: ptr_copy => null()
    integer, pointer :: ptr_array(:) => null()
    integer, pointer :: ptr_slice(:) => null()
    integer, pointer :: ptr_lbound(:) => null()
    integer, pointer :: ptr_bounds(:) => null()
    integer, pointer :: ptr_2d(:, :) => null()

    ! Procedure Pointer
    abstract interface
        function func_interface(x) result(res)
            integer, intent(in) :: x
            integer :: res
        end function func_interface
    end interface
    procedure(func_interface), pointer :: ptr_proc => null()

    ! 1. Scalar pointer assignment
    ptr_scalar => scalar_target
    print *, "1. Scalar pointer:", ptr_scalar

    ! 2. Pointer-to-pointer assignment
    ptr_copy => ptr_scalar
    print *, "2. Pointer copy:", ptr_copy

    ! 3. Whole array assignment
    ptr_array => array_target
    print *, "3. Whole array:", ptr_array

    ! 4. Array section / slice assignment
    ptr_slice => array_target(2:4)
    print *, "4. Array slice (2:4):", ptr_slice

    ! 5. Lower bound remapping
    ptr_lbound(10:) => array_target
    print *, "5. Lower bound remapped | LBound:", lbound(ptr_lbound, 1), "| ptr(10):", ptr_lbound(10)

    ! 6. Full bounds remapping (same rank)
    ptr_bounds(0:4) => array_target
    print *, "6. Full bounds remapped | LBound:", lbound(ptr_bounds, 1), "| ptr(0):", ptr_bounds(0)

    ! 7. Rank remapping (1D contiguous target to 2D pointer)
    ptr_2d(1:2, 1:2) => array_target(1:4)
    print *, "7. Rank remapped 2D pointer (element 2,2):", ptr_2d(2, 2)

    ! 8. Procedure pointer assignment
    ptr_proc => double_val
    print *, "8. Procedure pointer result:", ptr_proc(5)

    ! 9. Nullification pointer assignments
    ptr_scalar => null()
    ptr_copy => null()
    ptr_array => null()
    ptr_slice => null()
    ptr_lbound => null()
    ptr_bounds => null()
    ptr_2d => null()
    ptr_proc => null()

    print *, "9. Any pointers still associated?", associated(ptr_scalar) .or. associated(ptr_proc)

contains

    function double_val(x) result(res)
        integer, intent(in) :: x
        integer :: res
        res = x * 2
    end function double_val

end program pointer_assign