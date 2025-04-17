package com.random.problems.leetCode.zigzagconversion

import com.random.util.test

fun main() {
    val input = listOf(
        ("PAYPALISHIRING" to 3) to "PAHNAPLSIIGYIR",
        ("PAYPALISHIRING" to 4) to "PINALSIGYAHRPI",
        ("A" to 1) to "A",
        ("AB" to 1) to "AB",
        ("" to 1) to "",
        ("CE" to 120000) to "CE"
    )

    input.forEach {
        val (s, numRows) = it.first
        it.test { Solution2().convert(s, numRows)}
    }
}
