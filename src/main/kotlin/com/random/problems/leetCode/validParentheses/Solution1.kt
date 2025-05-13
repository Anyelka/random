package com.random.problems.leetCode.validParentheses

import java.util.*

class Solution1 {
    fun isValid(s: String): Boolean {
        return isValid1(s)
    }

    //      TC: O(n)
    //      SC: O(n)
    private fun isValid1(s: String): Boolean {
        val stack = Stack<Char>()
        for (c in s) {
            if (c.isClosing()) {
                if (stack.isEmpty() || stack.pop() != c.pair()) return false
            } else {
                stack.push(c)
            }
        }
        return stack.isEmpty()
    }

    private fun Char.isClosing(): Boolean {
        return this == ')' || this == '}' || this == ']'
    }

    private fun Char.pair(): Char {
        return when(this) {
            ')' -> '('
            '}' -> '{'
            ']' -> '['
            else -> throw IllegalArgumentException("Invalid char: $this")
        }
    }
}