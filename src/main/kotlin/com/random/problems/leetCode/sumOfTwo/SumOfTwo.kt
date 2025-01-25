package com.random.problems.leetCode.sumOfTwo

fun main() {
    val result = SumOfTwo().twoSum(intArrayOf(3,2,4), 6)
    val i = result[0]
    val j = result[1]
    println("$i;$j")
}

class SumOfTwo {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val seen = hashMapOf<Int, Int>()
        for((i, num) in nums.withIndex()) {
            val diff = target - num
            val otherNumIndex = seen[diff]
            if(otherNumIndex != null) {
                return intArrayOf(otherNumIndex, i)
            } else {
                seen[num] = i
            }
        }
        return intArrayOf()
    }
}