package com.random.problems.leetCode.kokoEatingBananas

class Solution1 {
    // Search in different speeds between 1 and max in piles with binary search
    //  TC: O(log(pmax)) * O(p) = O(p * log(maxp))
    //  SC: O(1)
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        var l = 1
        var r = piles.max()

        fun hoursTaken(k: Int) =
            if (k == 0) Long.MAX_VALUE else piles.sumOf { (it / k) + (it % k).coerceAtMost(1).toLong() }

        while (true) {
            val k = (r + l) / 2
            val hoursTaken = hoursTaken(k)
            if (h in hoursTaken..<hoursTaken(k - 1)) return k
            else if (hoursTaken > h) l = k + 1
            else r = k - 1
        }
    }
}