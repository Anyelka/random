package com.random.problems.leetCode.taskScheduler

import com.random.util.test

fun main() {
    val input = listOf(
        (charArrayOf('A','A','A','B','B','B') to 2) to 8,
        (charArrayOf('A','C','A','B','D','B') to 1) to 6,
        (charArrayOf('A','A','A', 'B','B','B') to 3) to 10
    )

    input.forEach {
        val (tasks, n) = it.first
        it.test { Solution1().leastInterval(tasks, n) }
    }
}