package com.random.problems.leetCode.twoSum2

class Solution1 {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        return twoSumTwoPointers(numbers, target)
    }

    // 1. Solution: Brute force
    //      TC:     O(n ^ 2)
    //      SC:     O(1)
    fun twoSumBruteForce(numbers: IntArray, target: Int): IntArray {
        for(i in numbers.indices) {
            for(j in i+1..<numbers.size) {
                if(numbers[i] + numbers[j] == target) return intArrayOf(i + 1, j + 1)
            }
        }
        return intArrayOf(0,0)
    }

    // 2. Solution: Map with one loop
    //      TC:     O(n)
    //      SC:     O(n)
    fun twoSumMap(numbers: IntArray, target: Int): IntArray {
        val differenceMap = mutableMapOf<Int,Int>()
        for((i, number) in numbers.withIndex()) {
            if(differenceMap.containsKey(number)) return intArrayOf(differenceMap[number]!! + 1, i + 1)
            differenceMap[target - number] = i
        }
        return intArrayOf(0,0)
    }

    // 3. Solution: Two pointers
    //      TC:     O(n)
    //      SC:     O(1)
    fun twoSumTwoPointers(numbers: IntArray, target: Int): IntArray {
        var i1 = 0
        var i2 = numbers.size - 1
        while(i1 < i2) {
            if(numbers[i1] + numbers[i2] == target) return intArrayOf(i1 + 1, i2 + 1)
            else if (numbers[i1] + numbers[i2] < target) i1++
            else i2--
        }
        return intArrayOf(0,0)
    }
}