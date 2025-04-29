package com.random.problems.leetCode.kokoEatingBananas

import com.random.util.test

fun main() {
    val input = listOf(
        (intArrayOf(3,6,7,11) to 8) to 4,
        (intArrayOf(30,11,23,4,20) to 5) to 30,
        (intArrayOf(30,11,23,4,20) to 6) to 23,
        (intArrayOf(332484035,524908576,855865114,632922376,222257295,690155293,112677673,679580077,337406589,290818316,877337160,901728858,679284947,688210097,692137887,718203285,629455728,941802184) to 823855818) to 14,
        (intArrayOf(1000000000) to 2) to 500000000,
        (intArrayOf(2,2) to 2) to 2
    )

    input.forEach {
        val (piles, h) = it.first
        it.test { Solution1().minEatingSpeed(piles, h) }
    }
}