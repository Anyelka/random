package com.random.problems.leetCode.reverseInteger

import com.random.util.test

fun main() {
    val input = listOf(
        123 to 321,
        -123 to -321,
        120 to 21,
        1534236469 to 0,
        1563847412 to 0,
        -2147483648 to 0,
        1463847412 to 2147483641
    )

    input.forEach {
        val x = it.first
        it.test { Solution2().reverse(x) }
    }
}