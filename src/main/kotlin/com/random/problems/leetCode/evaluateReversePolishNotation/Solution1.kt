package com.random.problems.leetCode.evaluateReversePolishNotation

import java.util.*
import kotlin.collections.ArrayDeque

class Solution1 {
    fun evalRPN(tokens: Array<String>): Int {
        return evalRPN2(tokens)
    }

    // 1. Stack
    //  TC: O(n)
    //  SC: O(n)
    fun evalRPN1(tokens: Array<String>): Int {
        val operators: Map<String, (Int, Int) -> Int> = mapOf(
            "+" to { a, b -> a + b },
            "-" to { a, b -> a - b },
            "*" to { a, b -> a * b },
            "/" to { a, b -> a / b }
        )

        val stack = ArrayDeque<Int>()
        for(token in tokens) {
            if(operators.containsKey(token)) {
                val second = stack.removeLast()
                val first = stack.removeLast()
                stack.add(operators.getValue(token)(first, second))
            } else {
                stack.add(token.toInt())
            }
        }
        return stack.removeLast()
    }

    // 2. Stack in a functional way
    fun evalRPN2(tokens: Array<String>): Int = LinkedList<Int>().apply {
        tokens.forEach {
            push(
                when(it) {
                    "+" -> pop() + pop()
                    "*" -> pop() * pop()
                    "/" -> pop().let{pop() / it}
                    "-" -> pop().let{pop() - it}
                    else -> it.toInt()
                }
            )
        }
    }.pop()

}