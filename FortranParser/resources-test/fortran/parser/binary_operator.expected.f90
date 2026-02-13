PROGRAM test_exprs
  logical :: l
  integer :: i
  l = 0 < 1
  l = 2 <= 3
  l = 4 > 5
  l = 6 >= 7
  l = 8 == 9
  l = 0 /= 1

  i = 0 + 1
  i = 2 - 3
  i = 4 * 5
  i = 6 / 7

  i = 0 + 1 - 3 * 5 / 2 + 7

END PROGRAM test_exprs