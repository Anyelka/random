package com.random.problems.leetCode.twoSum2

class Solution2 {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        return twoSum2(numbers, target)
    }

    //  TC: O(n)
    //  SC: O(1)
    //  Runtime: 12ms
    private fun twoSum1(numbers: IntArray, target: Int): IntArray {
        var i = 0
        var j = numbers.lastIndex
        while (i <= j) {
            val sum = numbers[i] + numbers[j]
            if (sum == target) return intArrayOf(i + 1, j + 1)
            else if (sum > target) j--
            else i++
        }
        return intArrayOf()
    }

    //  TC: O(n)
    //  SC: O(1)
    //  Runtime: 8ms
    private fun twoSum2(numbers: IntArray, target: Int): IntArray {
        val differences = mutableMapOf<Int, Int>()
        for(i in numbers.indices) {
            differences[numbers[i]]?.let { return intArrayOf(it, i + 1) }
            differences[target - numbers[i]] = i + 1
        }
        return intArrayOf()
    }

}