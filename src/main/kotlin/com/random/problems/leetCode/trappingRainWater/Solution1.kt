package com.random.problems.leetCode.trappingRainWater

class Solution1 {
    fun trap(height: IntArray): Int {
        return trap2(height)
    }

    // Solution1:
    //      determine the rain trapped on the same level to the RIGHT
    //  we can look at height as a 2D map with index as x and height as y coordinates
    //      go through each y (height) and collect how much rain can be trapped on the given level
    //      get all the numbers with that height, go through them and check the water trapped to the RIGHT of that height
    //      - find the number of adjacent cells that can trap water =
    //          find the length of the pool = how many heights to the right are smaller than the current level
    //      - if we have a bigger dropdown than 1 (for example 4 -> 2), we need to also check the water trapped
    //          on the lower levels (for example trapped to the right of 3)
    //
    //      Time complexity:    O(n * n) (theoretically = O(n) * O(max(poolLength))
    //      Space complexity:   O(1)
    private fun trap1(height: IntArray): Int {
        val maxHeight = height.max()
        var totalRain = 0

        for (level in 1..maxHeight) {
            for ((blockIndex, block) in height.withIndex()) {
                if (block != level) continue

                var rainInPoolOnCurrentLevel = collectRainInPoolOnLevel(height, blockIndex, level)
                // explore lower levels if we dont have a stair-like 1 step decrease
                rainInPoolOnCurrentLevel += collectRainInPoolOnLowerLevel(height, blockIndex, level)
                totalRain += rainInPoolOnCurrentLevel

            }
        }

        return totalRain
    }

    private fun collectRainInPoolOnLevel(height: IntArray, index: Int, currentLevel: Int): Int {
        var i = 0
        while(index + i + 1 < height.size && height[index + i + 1] < currentLevel) i++
        return if(index + i == height.size - 1) 0 else i
    }

    private fun collectRainInPoolOnLowerLevel(height: IntArray, index: Int, level: Int): Int {
        var extraWater = 0
        var i = 1
        while(index + 1 < height.size && level - i > height[index + 1]) {
            extraWater += collectRainInPoolOnLevel(height, index,level - i)
            i++
        }
        return extraWater
    }


    // Solution 2:
    //      determine the rain trapped OVER each tile
    //
    //      the max rain trapped over a tile will be the difference of the height of the tile and the smaller of the
    //          highest peaks to the left and right
    //
    //      - go through each tile and collect the highest to the left and to the right of them
    //      - go through each tile and get the difference between its height and the smaller of the highest surrounding
    //          peaks
    //
    //      Time complexity:    O(n)
    //      Space complexity:   O(n)
    //
    private fun trap2(height: IntArray): Int {
        val maxLeft = IntArray(height.size)
        val maxRight = IntArray(height.size)

        for(i in height.indices) {
            maxLeft[i] = if(i == 0) 0 else maxOf(height[i - 1], maxLeft[i - 1])
            val right = height.size - i - 1
            maxRight[right] = if(i == 0) 0 else maxOf(height[right + 1], maxRight[right + 1])
        }

        return height.withIndex().sumOf { (i, level) -> maxOf(0, minOf(maxLeft[i], maxRight[i]) - level) }
    }
}