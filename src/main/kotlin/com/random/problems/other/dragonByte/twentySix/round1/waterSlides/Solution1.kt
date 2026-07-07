package com.random.problems.other.dragonByte.twentySix.round1.waterSlides


class Solution1 {
    fun solve(n: Int, k: Int, points: IntArray): List<String> {
        return solveK1(n,k,points)
    }

    fun solveK1(n: Int, k: Int, points: IntArray): List<String> {
        // we can assume that k = 1
        // only calculate the diff between min and max and keep indices
        var min = Int.MAX_VALUE
        var minIndex = -1
        var max = Int.MIN_VALUE
        var maxIndex = -1
        for((i,height) in points.withIndex()) {
            if(height < min) {
                min = height
                minIndex = i
            }
            if(height > max) {
                max = height
                maxIndex = i
            }
        }
        return listOf("$maxIndex $minIndex")
    }

    fun solveK2(n: Int, k: Int, points: IntArray): List<String> {
        // n <= 10
        // k <= 5
        val maxSlideQuality = 0L
        val optimalSolution = emptyList<List<Int>>()

        fun maxSlideQualityBetween(start: Int, end: Int, kLeft: Int): Long {
            if(kLeft == 0) return 0L
            if(start == end) return 0L

            var max = 0L
            for(i in start..end) {
                val top = points[i]
                val iNextStart = if(i == end) start else i + 1
                val iNextEnd = if(i == start) end else i - 1
                for(j in iNextStart..iNextEnd) {
                    // we check whether we can slide down from i to j
                    val bottom = points[j]
                    if(top > bottom) {
                        val diff = top - bottom
                        val jNextStart = if(j == end) start else j + 1
                        val jNextEnd = if(j == start) end else j - 1
                        for(kRemaining in 0..<kLeft-1) {
                            //we can build 0..k-1 to the left
                            // and the remaining to the other side
                            val maxToLeft = if(iNextStart < jNextEnd) maxSlideQualityBetween(iNextStart, jNextEnd, kRemaining) else 0
                            val maxToRight = if(jNextStart < iNextEnd) maxSlideQualityBetween(jNextStart, iNextEnd, kLeft - kRemaining) else 0
                            val nextMax = diff + maxToLeft + maxToRight
                            max = maxOf(max, nextMax)
                        }
                    }
                }
            }
            return max
        }

        val max = maxSlideQualityBetween(0, points.lastIndex, k)

        return listOf("" + max)
    }
}