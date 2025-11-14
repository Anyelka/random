package com.random.problems.leetCode.maximumNumberOfOperationsToMoveOnesToTheEnd

import com.random.util.test

fun main() {
    val input = listOf(
        getInput680(),
        "1001101" to 4,
        "00111" to 0,
        "10101101" to 7,
        "10101100" to 7,
        "1110" to 3,
        "1010" to 3,
        "11010" to 5,
        "10110110" to (2 + 4 + 3),
        "0010011000110" to (2 + 4 + 3)
    )


    input.forEach {
        val s = it.first
        it.test { Solution1().maxOperations(s) }
    }
}