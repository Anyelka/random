package com.random.problems.leetCode.editDistance

import com.random.util.test

fun main() {
    val input = listOf(
        ("horse" to "ros") to 3,
        ("intention" to "execution") to 5,
        ("park" to "spake") to 3,
        ("dinitrophenylhydrazine" to "acetylphenylhydrazine") to 6
    )

    input.forEach {
        val (word1, word2) = it.first
        it.test { Solution1().minDistance(word1, word2) }
    }
}