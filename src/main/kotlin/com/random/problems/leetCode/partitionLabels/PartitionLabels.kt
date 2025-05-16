package com.random.problems.leetCode.partitionLabels

import com.random.util.test

fun main() {
    val input = listOf(
        "ababcbacadefegdehijhklij" to listOf(9,7,8),
        "eccbbbbdec" to listOf(10)
    )

    input.forEach {
        val s = it.first
        it.test { Solution1().partitionLabels(s) }
    }

}