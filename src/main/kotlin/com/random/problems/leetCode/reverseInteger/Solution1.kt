package com.random.problems.leetCode.reverseInteger

class Solution1 {
    fun reverse(x: Int): Int {
        return reverse2(x)
    }

    // Solution1:
    //  Use native functions to convert to string, reverse and then convert back to int
    //
    //      THIS USES 64 BIT LONG! = invalid solution
    //
    //      TC:     O(n)    =   O(n) + O(n) + O(n)
    //          - all of these take O(n):
    //              - converting the int to string
    //              - reversing
    //              - converting back
    //      SC:     O(n)
    //              - for storing the digits in a string
    //          - where n is the length of x = number of digits
    private fun reverse1(x: Int): Int {
        val isNegative = x.toString().startsWith("-")
        val number = if (isNegative) x.toString().substring(1) else x.toString()
        val reversedString = number.toCharArray().reversed().joinToString("")
        if (reversedString.toLong() > Int.MAX_VALUE) return 0
        val reversed = reversedString.toInt()
        return if (isNegative) -reversed else reversed
    }


    // Solution 2:
    //
    //  Store the reversed integer in an Int
    //      iterate through the digits by increasing putting the digits one-by-one from the original to the reversed number
    //      we can check if we go out of 32 bit bound by always checking if we are above/below the 32 bit threshold / 10
    //      this is because the last digit of 32 bit bounds is 7, which is greater than the first digit (1), so the reverse
    //      could not come in as an input
    //
    //      TC:     O(n)
    //      SC:     O(n)
    private fun reverse2(x: Int): Int {
        var reversed: Int = 0
        var number = x

        while(number != 0) {
            val digit = number % 10
            if(Int.MAX_VALUE / 10 < reversed || Int.MIN_VALUE / 10 > reversed) {
                return 0
            }
            reversed = reversed * 10 + digit
            number /= 10
        }

        return reversed
    }

}