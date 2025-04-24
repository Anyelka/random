package com.random.problems.leetCode.reverseInteger

import com.random.util.absoluteValue
import com.random.util.pow
import kotlin.math.abs
import kotlin.math.pow

class Solution2 {
    fun reverse(x: Int): Int {
        //      123450
        //  1.  0
        //  2.  5
        //  3.  54
        //  4.  543
        //  5.  5432
        //  6.  54321
        // we need to iterate from right (lowest place value) to left (highest place value) and append the digits
        //  1-by-1 to the result

        var result = 0
        var number = x
        while(number != 0) {
            if(result > Int.MAX_VALUE / 10 || result < Int.MIN_VALUE / 10) return 0
            val digit = number % 10
            result = result * 10 + digit
            number /= 10
        }

        return result
    }

    fun Int.pow(power: Int): Int {
        return this.toDouble().pow(power).toInt()
    }
}