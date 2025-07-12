package com.random.problems.other.dragonByte.numberPairs

class Solution {
    fun solve(n: Long): String {
        if(n % 9L != 0L) return "NONE"
        return (n / 9L).let { "$it ${10 * it }"  }
    }
}