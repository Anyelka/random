package com.random.problems.leetCode.maximizeConsecutiveElementsInArrayAfterModification

class Solution1 {
    fun maxSelectedElements(nums: IntArray): Int {
        return maxSelectedElements2(nums)
    }

    // 1. Inefficient, TC: O(2^n)
    fun maxSelectedElements1(nums: IntArray): Int {
        // TC: 2^n
        fun getAllPossibleFinalArrays(nums: IntArray): Set<IntArray> {
            val result = mutableSetOf<IntArray>()
            fun getFinalArray(i: Int, current: IntArray) {
                if(i == nums.size) {
                    result.add(current)
                    return
                }
                val nextArray = current.copyOf()
                getFinalArray(i+1, nextArray)
                val nextModifiedArray = current.copyOf()
                nextModifiedArray[i] += 1
                getFinalArray(i+1, nextModifiedArray)
            }
            getFinalArray(0,nums)
            return result
        }

        //TC: O(n*log(n) + n)
        fun longestConsecutiveLength(final: IntArray): Int {
            val sorted = final.sorted()
            var length = 1
            var maxLength = 1
            for(i in sorted.indices) {
                if(i == 0) continue
                when {
                    sorted[i] == sorted[i-1] -> {}
                    sorted[i] == sorted[i-1] + 1 -> {
                        length++
                    }
                    else -> {
                        length = 1
                    }
                }
                maxLength = maxOf(maxLength, length)
            }
            return maxLength
        }

        val possibleFinalArrays = getAllPossibleFinalArrays(nums)

        return possibleFinalArrays.maxOf { longestConsecutiveLength(it) }
    }

    // 2. Greedy solution
    // Greedy lifting: not proper
    fun maxSelectedElements2(nums: IntArray): Int {
        val sorted = nums.sorted()

        /*
        [1,2,4,4,6,8,9,10]
         0,1,2,3,4,5,6,7    = i

        lastCutoff = -1
        lastLift = -1
        maxLen = 1
        len = 0
        i   num prev    diff    dec     lastLift    lastCutOff
        0   1   1       0 <= 0  LIFT    -1          -1
                prev = 2
                lastLift = 0
                len++
        1   2   2       0 <= 0  LIFT    0           -1
                prev = 3
                lastLift = 1
                len++
        2   4   3       1 == 1  KEEP    1           -1
                prev = 4
                len++
        3   4   4       0 <= 0  LIFT    1           -1
                prev = 5
                lastLift = 3
                len++
        4   6   5       1 == 1  KEEP    3           -1
                prev = 6
                len++
        5   8   6       2 > 1   CUTOFF  3           -1
                len = (i-1) - lastCutoff = (5-1) - (-1) = 5
                i = lastLift + 1 = 4
                lastCutoff = lastLift = 3
                maxLen = maxOf(len, maxLen)
        4   6   6       0 <= 0  LIFT    3           3
                prev = 7
                lastLift = 4
        5   8   7       1 == 1  KEEP    4           3
                prev = 8
        6   9   8       1 == 1  keep    4           3
                prev = 9
        7   10  9       1 == 1  keep    4           3

        ==
        (8)
        len = (i-1) - lastCutOff = (8-1) - 3 =      5

        */

        var i = 0
        var maxLength = 1
        var prev = sorted[0]
        var lastLift = -1
        var lastCutoff = -1
        while(i <= sorted.lastIndex) {
            when {
                sorted[i] - prev > 1 -> {
                    if(i == lastLift + 1) {
                        prev = sorted[i] + 1
                        lastLift = i
                        i++
                        continue
                    }
                    //CUTOFF
                    val currentLength = (i-1) - lastCutoff
                    maxLength = maxOf(maxLength, currentLength)
                    i = lastLift + 1
                    lastCutoff = lastLift
                }
                sorted[i] - prev == 1 -> {
                    //KEEP
                    prev = sorted[i]
                    i++
                }
                sorted[i] - prev < 1 -> {
                    //LIFT
                    prev = sorted[i] + 1
                    lastLift = i
                    i++
                }
            }
        }

        val currentLength = (i-1) - lastCutoff
        return maxOf(maxLength, currentLength)
    }

    fun maxSelectedElements3(nums: IntArray): Int {
        val sorted = nums.sorted()

        /*[1,1,1,2,5]
        [1,1,2,3,5]*/
        return 0
    }
}