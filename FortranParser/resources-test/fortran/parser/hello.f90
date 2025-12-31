program hello
    ! This is a comment line; it is ignored by the compiler
    print *, 'Hello, World!'
    print 100, 'Hello, World!', 2
    100 FORMAT(A, I3)
    20 PRINT '(A, F6.3)', 'Value = ', 3
end program hello