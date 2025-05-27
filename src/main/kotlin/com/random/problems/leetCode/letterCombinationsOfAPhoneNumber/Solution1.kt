package com.random.problems.leetCode.letterCombinationsOfAPhoneNumber

class Solution1 {
    fun letterCombinations(digits: String): List<String> {
        val result = mutableListOf<String>()

        fun letterCombinations(combination: String, i: Int) {
            if(i == digits.length) {
                if(combination.isNotEmpty()) result.add(combination)
                return
            }
            val possibleLetters = getPossibleLetters(digits[i].digitToInt())
            for(c in possibleLetters) {
                letterCombinations(combination + c, i + 1)
            }
        }

        letterCombinations("", 0)

        return result
    }

    fun getPossibleLetters(n: Int): List<Char> {
        return when(n) {
            2 -> listOf('a', 'b', 'c')
            3 -> listOf('d', 'e', 'f')
            4 -> listOf('g', 'h', 'i')
            5 -> listOf('j', 'k', 'l')
            6 -> listOf('m', 'n', 'o')
            7 -> listOf('p', 'q', 'r', 's')
            8 -> listOf('t', 'u', 'v')
            9 -> listOf('w', 'x', 'y', 'z')
            else -> throw IllegalArgumentException("Illegal digit: $n")
        }
    }
}