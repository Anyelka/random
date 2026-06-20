package com.random.problems.leetCode.processStringWithSpecialOperationsII

import com.random.util.test

fun main() {
    val input = listOf(
        ("a#b%*" to 1L) to 'a',
        ("cd%#*#" to 3L) to 'd',
        ("z*#" to 0L) to '.',
        ("jio#*g" to 1L) to 'i'
    )

    input.forEach {
        val (s, k) = it.first
        it.test { Solution1().processStr(s, k) }
    }
}