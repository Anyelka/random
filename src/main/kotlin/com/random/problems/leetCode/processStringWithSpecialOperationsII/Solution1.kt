package com.random.problems.leetCode.processStringWithSpecialOperationsII

class Solution1 {
    fun processStr(s: String, k: Long): Char {
        var length = 0L
        for(c in s) {
            when(c) {
                '*' -> length--
                '#' -> length *= 2
                '%' -> {}
                else -> length++
            }
        }

        if(k >= length) return '.'
        var kInitial = k
        for(i in s.length - 1 downTo 0) {
            val c = s[i]
            when(c) {
                '*' -> length++
                '#' -> {
                    if(kInitial + 1 > (length+1) / 2) kInitial -= length / 2
                    length = (length + 1) /  2
                }
                '%' -> kInitial = length - 1 - kInitial
                else -> {
                    if(kInitial == length - 1) return c
                    length--
                }
            }
        }

        return '.'
    }
}