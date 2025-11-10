package com.random.problems.leetCode.minOperationsToConvertAllElementsToZero

class Solution1 {
/*
    1, 3, 7, 3, 5, 5, 3, 2, 1, 0, 1
    0  1  2  3. 4. 5. 6. 7. 8. 9. 10

    split the array by zeroes
    sum the minOperations of each subarray  ([0,8], [10,10])
    a, [0,8]
    -> [0,8]
    0, 3, 7, 3, 5, 5, 3, 2, 0, 0, 1
    -> [1, 7]       (could do [7,7] or [6,7] too ... )
    0, 3, 7, 3, 5, 5, 3, 0, 0, 0, 1
    -> [1, 6]
    0, 0, 7, 0, 5, 5, 0, 0, 0, 0, 1

    split the array by zeroes
    sum the minOperations of each subarray ([2,2], [4,5])
    a/a, -> [2,2]
    0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 1
    a/b, -> [4,5]
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1

    b, [10,10]
    -> [10,10]
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0

    =======
    1, 3, 7, 3, 5, 5, 3, 2, 1, 0, 1
    0  1  2  3. 4. 5. 6. 7. 8. 9. 10

    minIndexes = 9.
    paddedMinIndexes                = [0,9,10]
    split: 0..(minIndex - 1)        = [0,8]
    (minIndex+1)..lastIndex  = [10,10]

    minIndexes = [0,8]
    paddedMinIndexes                        = [0,0,8,8]
    split: 0..(minIndex - 1)               = [0, -1]   INVALID => don't take
    (minIndex+1..nextMindIndex-1)   = [1, 7]
    minIndex+1..8                   = [9, 8]    INVALID => don't take
    paddedMinIndexes = [0,0,8,8]
*/


    fun minOperations(nums: IntArray): Int {
        return minOperations3(nums)
    }

    // 1. Tracking min indexes and splitting array recursively
    //      TC: O(n ^ 2)
    //      SC: O(n ^ 2)
    fun minOperations1(nums: IntArray): Int {
        val memo = mutableMapOf<Pair<Int,Int>, Int>()
        return minOperations1(nums, 0, nums.lastIndex, memo)
    }

    fun minOperations1(nums: IntArray, start: Int, end: Int, memo: MutableMap<Pair<Int,Int>, Int>): Int {
        if(memo.containsKey(start to end)) return memo[start to end]!!
        if (start == end) return if (nums[start] == 0) 0 else 1
        var minValue = nums[start]
        var minIndexes = mutableListOf<Int>()

        for(i in start..end) {
            if(nums[i] < minValue) {
                minIndexes = mutableListOf(i)
                minValue = nums[i]
            } else if(nums[i] == minValue) {
                minIndexes.add(i)
            }
        }

        val transCost = if(minValue == 0) 0 else 1

        val paddedMinIndexes = mutableListOf(start-1) + minIndexes + mutableListOf(end+1)

        var subarrayCosts = 0
        for(i in 0..<paddedMinIndexes.lastIndex) {
            val minIndex = paddedMinIndexes[i]
            val nextMinIndex = paddedMinIndexes[i + 1]
            val startIndex = minIndex+1
            val endIndex = nextMinIndex-1
            if(startIndex <= endIndex) subarrayCosts += minOperations1(nums, startIndex, endIndex, memo)
        }

        memo[start to end] = transCost + subarrayCosts
        return transCost + subarrayCosts
    }


    fun minOperations2(nums: IntArray): Int {
        //val memo = mutableMapOf<Pair<Int, Int>, Int>()
        val stack = ArrayDeque<Pair<Int, Int>>()
        stack.add(0 to nums.lastIndex)
        var totalCost = 0

        while (stack.isNotEmpty()) {
            val (start, end) = stack.removeLast()
            /*if (memo.containsKey(start to end)) {
                totalCost += memo[start to end]!!
                continue
            }*/
            if (start > end) continue

            var minValue = nums[start]
            val minIndexes = mutableListOf<Int>()
            for (i in start..end) {
                if (nums[i] < minValue) {
                    minIndexes.clear()
                    minIndexes.add(i)
                    minValue = nums[i]
                } else if (nums[i] == minValue) {
                    minIndexes.add(i)
                }
            }

            val transCost = if (minValue == 0) 0 else 1
            val padded = listOf(start - 1) + minIndexes + listOf(end + 1)

            for (i in 0 until padded.lastIndex) {
                val s = padded[i] + 1
                val e = padded[i + 1] - 1
                if (s <= e) stack.add(s to e)
            }

            //memo[start to end] = transCost
            totalCost += transCost
        }

        return totalCost
    }


    // 3. Monotonic Increasing Stack
    //      TC: O(n)
    //      SC: O(n)
    fun minOperations3(nums: IntArray): Int {
        val stack = ArrayDeque<Int>()
        var operations = 0

        for(num in nums) {
            while(stack.isNotEmpty() && stack.last() > num) {
                stack.removeLast()
            }

            if(num > 0 && (stack.isEmpty() || num > stack.last())) {
                operations++
                stack.add(num)
            }
        }

        return operations
    }


}