package com.random.problems.leetCode.coinChange

import com.random.util.test


fun main() {

    val input = listOf(
        (intArrayOf(1, 2, 5) to 11) to 3,
        (intArrayOf(2) to 3) to -1,
        (intArrayOf(1) to 0) to 0,
        (intArrayOf(2, 5, 10, 1) to 27) to 4,
        (intArrayOf(1, 2147483647) to 2) to 2,
        //
        (intArrayOf(1, 2, 5) to 100) to 20,
        (intArrayOf(474,83,404,3) to 264) to 8,
        (intArrayOf(186, 419, 83, 408) to 6249) to 20,
        (intArrayOf(461, 307, 4, 97, 352, 446, 479, 243) to 7265) to 16
    )



    input.forEach {
        val currentInput = it.first
        it.test {
            Solution3().coinChange(currentInput.first, currentInput.second)
        }
    }

}

