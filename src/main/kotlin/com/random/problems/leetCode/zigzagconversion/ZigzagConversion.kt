package com.random.problems.leetCode.zigzagconversion

import com.random.util.isCorrectString

fun main() {
    val input = listOf(
        /*("PAYPALISHIRING" to 3) to "PAHNAPLSIIGYIR",
        ("PAYPALISHIRING" to 4) to "PINALSIGYAHRPI",
        ("A" to 1) to "A",*/
        ("AB" to 1) to "AB",
        ("" to 1) to "",
        ("CE" to 120000) to "CE"
    )

    input.forEach {it.test() }
}

private fun Pair<Pair<String, Int>, String>.test() {
    val result = Solution2().convert(first.first, first.second)
    println("Result for s = '${first.first}', numRows = ${first.second}: '$result' - ${isCorrectString(result, second)}")
}
