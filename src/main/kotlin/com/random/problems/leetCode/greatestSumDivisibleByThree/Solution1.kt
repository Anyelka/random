package com.random.problems.leetCode.greatestSumDivisibleByThree

class Solution1 {
    fun maxSumDivThree(nums: IntArray): Int {
        return maxSumDivThree2(nums)
    }

    /*
        1,4,7 -> x % 3 = 1
        2,5,8 -> x % 3 = 2

        [3,6,5,1,8]
         0,0,2,1,2
        maxSum = 3 + 6 + 5 + 1 + 8 = 23
        remainder = 23 % 3 = 2
        we need to remove either:
            - 1 number with 2 remainder
            - 2 numers with 1 remainder (2 in total)
                -> whichever of these 2 results in the min sum removal


        [9,11,3]
         0, 2,0
        maxSum = 23
        remainder =  23 % 3 = 2
        -> we remove 1 number with 2 remainder:
            maxSum = 23 - 11 = 12
        ...

        [1,2,3,4,4]
         1,2,0,1,1
         maxSum = 1 + 2 + 3 + 4 + 4 = 14
         remainder = 14 % 3 = 2
         we need to remove either:
            - 1 number with 2 remainder:
                remove 2                    TOTAL -=2
            - 2 numbes with 1 remainder:
                remove 1,4                  TOTAL -=5
                remove 1, (other) 4
                remove 4,4                  TOTAL -=5

        [10,8,1,4]
          1,2,1,1
        maxSum = 10 + 8 + 1 + 4 = 23
        remainder = 23 % 3 = 2
        remove either:
            - 1 number with 2 remainder:
                remove 8                    TOTAL -= 8
            - 2 smallest numbers with 1 remainder:
                remove 1, 4                 TOTAL -= 5
        sum = 23 - 5 = 18

        [1,2,2,3,4,4]
         1,2,2,0,1,1
        maxSum = 16
        remainder = 1
        remove either:
            - 1 number with 1 remainder:
                - 1                         TOTAL -= 1
            - 2 numbers with 2 remainder:
                - 2,2                       TOTAL -= 4

        we need to keep track of:
            - smallest with 1 remainder
            - second smallest with 1 remainder
            - smallest with 2 remainder
            - second smallest with 2 remainder
        */
    // 1. Solution:
    //   single pass with removing the smallest unnecessary numbers that make the sum non-divisible by 3
    //      TC: O(n)
    //      SC: O(1)
    fun maxSumDivThree1(nums: IntArray): Int {
        val maxValue = 10 * 10 * 10 * 10
        var smallestWith1Rem = maxValue
        var secondSmallestWIth1Rem = maxValue
        var smallestWith2Rem = maxValue
        var secondSmallestWith2Rem = maxValue

        var sum = 0
        for(num in nums) {
            sum += num
            if(num % 3 == 1) {
                if(num < smallestWith1Rem) {
                    secondSmallestWIth1Rem = smallestWith1Rem
                    smallestWith1Rem = num
                } else if(num < secondSmallestWIth1Rem) {
                    secondSmallestWIth1Rem = num
                }
            }
            if(num % 3 == 2) {
                if(num < smallestWith2Rem) {
                    secondSmallestWith2Rem = smallestWith2Rem
                    smallestWith2Rem = num
                } else if(num < secondSmallestWith2Rem) {
                    secondSmallestWith2Rem = num
                }
            }
        }

        sum -=  when(sum % 3) {
            1 -> minOf(smallestWith1Rem, smallestWith2Rem + secondSmallestWith2Rem)
            2 -> minOf(smallestWith2Rem, smallestWith1Rem + secondSmallestWIth1Rem)
            else -> 0
        }
        return sum
    }

    // 1. Solution with functional style
    fun maxSumDivThree2(nums: IntArray): Int {
        val maxValue = 3 * 10 * 10 * 10 * 10
        val oneMins = maxValue + 1 to maxValue + 1
        val twoMins = maxValue + 2 to maxValue + 2

        fun Pair<Int,Int>.nextPair(num: Int): Pair<Int,Int> {
            if(num % 3 == first % 3) {
                return if(num < second) minOf(first, num) to maxOf(first, num) else this
            }
            return this
        }

        return nums.fold(Triple(0, oneMins, twoMins)) {
            (sum, oneMins, twoMins), value ->
            Triple(sum + value, oneMins.nextPair(value), twoMins.nextPair(value))
        }.let { (sum, oneMins, twoMins) -> when(sum % 3) {
            1 -> sum - minOf(oneMins.first, twoMins.first + twoMins.second)
            2 -> sum - minOf(twoMins.first, oneMins.first + oneMins.second)
            else -> sum
        }}
    }
}