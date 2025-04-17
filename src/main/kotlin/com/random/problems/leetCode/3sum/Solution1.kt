package com.random.problems.leetCode.`3sum`

class Solution1 {
    fun threeSum(nums: IntArray): List<List<Int>> {
        return threeSumOpt(nums)
    }

    // 1. Brute force solution:
    //  go through all triplets
    //  count the ones that sum up to 0
    //  only count the distinct ones
    //
    //      Time complexity:        O(n ^ 3)
    //      Space complexity:       O(n ^ 3)
    //
    fun threeSumBruteForce(nums: IntArray): List<List<Int>> {
        val triplets = mutableSetOf<List<Int>>()
        for(i in nums.indices) {
            for(j in i..<nums.size) {
                for(k in j..<nums.size) {
                    if(i != j && j != k && nums[i] + nums[j] + nums[k] == 0) {
                        triplets.addIfDistinct(listOf(nums[i], nums[j], nums[k]))
                    }
                }
            }
        }
        return triplets.toList()
    }

    // 2. Solution:
    //      Optimized solution with sorting the array,
    //          then one loop and a 2Sum for the other two numbers with left and right pointers
    //
    //      Time complexity:    O(n * log(n)) + O(n ^ 2) = O(n ^ 2)
    //      Space complexity:   O(1) - depends on the sorting
    //
    fun threeSumOpt(nums: IntArray): List<List<Int>> {
        val sortedNums = nums.sorted()
        return threeSumOnSorted(sortedNums)
    }

    private fun threeSumOnSorted(nums: List<Int>): MutableList<List<Int>> {
        val triplets = mutableListOf<List<Int>>()
        for (i in nums.indices) {
            val target = nums[i]

            if (i > 0 && target == nums[i - 1]) continue

            var l = i + 1
            var r = nums.size - 1

            while (l < r) {
                val threeSum = target + nums[l] + nums[r]
                if (threeSum == 0) {
                    triplets.add(listOf(target, nums[l], nums[r]))
                    l++
                    while(nums[l] == nums[l - 1] && l < r) l++
                } else if (threeSum < 0) {
                    l++
                } else {
                    r--
                }
            }
        }
        return triplets
    }


}

private fun MutableSet<List<Int>>.addIfDistinct(triplet: List<Int>) {
    if (this.none { t -> t.matchesTriplet(triplet) }) this.add(triplet)
}

private fun List<Int>.matchesTriplet(other: List<Int>) =
    this.sorted() == other.sorted()
