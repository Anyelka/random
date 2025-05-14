package com.random.problems.leetCode.sumOfTwoIntegers

class Solution2 {
    fun getSum(a: Int, b: Int): Int {

        // a = 5
        // b = 7
        // s = 12

        // a =  101
        // b =  111
        //     1100

        //  1. iteration:
        //  - digit: x xor y    010
        //  - remainder:        101
        //  - shifted:         1010
        //  - x:                 10     =   2
        //  - y:               1010     =  10
        //  2. iteration:
        //  - digit: x xor y   1000
        //  - remainder:         10
        //  - shifted:          100
        //  - x:               1000     =  8
        //  - y:                100     =  4
        //  3. iteration:
        //  - digit: x xor y   1100
        //  - remainder:          0
        //  - shifted:           00
        //  - x:               1100     =   12
        //  - y:                  0     =   0

        //  a   =   2   10
        //  b   =   3  100
        //  c   =   5  110

        var x = a
        var y = b
        while(y != 0) {
            val digit = x xor y
            val remainder = x and y
            val temp = remainder shl 1
            x = digit
            y = temp
        }
        return x
    }
}