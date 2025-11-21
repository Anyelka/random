package com.random.problems.leetCode.evaluateReversePolishNotation

class Solution2 {
    fun evalRPN(tokens: Array<String>): Int {
        val stack = ArrayDeque<Int>()

        // if you remove let, the operation will change the order of the operands !!!
        fun apply(operation: (Int,Int) -> Int): Int = stack.removeLast().let { b -> operation(stack.removeLast(), b) }

        tokens.forEach { token ->
            val result = when (token) {
                "+" -> apply(Int::plus)
                "-" -> apply(Int::minus)
                "*" -> apply(Int::times)
                "/" -> apply(Int::div)
                else -> token.toInt()
            }
            stack.addLast(result)
        }

        return stack.last()

    }
}