package com.random.problems.leetCode.generateParentheses

import kotlin.math.max

class Solution1 {
    fun generateParenthesis(n: Int): List<String> {
        return generateParenthesis2(n)
    }

    // Solution 1: backtracking
    //  - generate a string with n '('-s concatenated
    //  - go through it and generate all the combinations where:
    //      1. a ')' is placed after a '(' or
    //      2. if it is kept and placed later
    //  n=1
    //  "()"
    //  n=2
    //  "()()", "(())"
    //  n=3
    //  "()()()", "(())()", "()(())", "((()))"

    //      (   (   (
    //
    //       ).  ).  )      1.
    //           )). )      2.
    //           )   ))     3.
    //               )))    4.
    //
    //      TC:     O(n * 4^n / √n)
    //      SC:     O(4^n / √n)
    fun generateParenthesis1(n: Int): List<String> {
        val parentheses = mutableSetOf<String>()

        val baseString = (1..n).joinToString("") { "(" }

        fun findPlaceable(string: String, i: Int, placed: Int, maxPlaceable: Int) {
            var placeable = maxPlaceable
            if (placed == n) parentheses.add(string)
            if (i >= string.length) return
            if (string[i] == '(') placeable++
            if (placeable > 0) {
                val nextString = string.substring(0, i + 1) + ")" + string.substring(i + 1)
                findPlaceable(nextString, i + 1, placed + 1, placeable - 1)
            }
            findPlaceable(string, i + 1, placed, placeable)
        }

        findPlaceable(baseString, 0, 0, 0)
        return parentheses.toList()
    }


    // Solution 2 : backtracking
    //      TC:     O(4^n / √n)
    //      SC:     O(4^n / √n)
    fun generateParenthesis2(n: Int): List<String> {
        val result = mutableListOf<String>()

        fun backtrack(current: String, open: Int, close: Int) {
            if (current.length == n * 2) {
                result.add(current)
                return
            }
            if (open < n) backtrack("$current(", open + 1, close)
            if (close < open) backtrack("$current)", open, close + 1)
        }

        backtrack("", 0, 0)
        return result
    }

}
