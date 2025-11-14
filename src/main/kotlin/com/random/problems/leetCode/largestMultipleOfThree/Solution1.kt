package com.random.problems.leetCode.largestMultipleOfThree

import java.util.PriorityQueue

class Solution1 {
    fun largestMultipleOfThree(digits: IntArray): String {
        return largestMultipleOfThree2(digits)
    }

    // 1. Solution:
    //  backtracking with decision tree
    fun largestMultipleOfThree1(digits: IntArray): String {
        /*
            algorithm:
            - go through the numbers one-by-one and set the decision tree for taking/leaving each element out
            - keep track of the total remainder and the selected numbers
            - if the total remainder is 0, we found a potential solution:
                -> compare it with the max number and reset if its bigger
            - what data type to store the numbers in?
                -> max priority queue?

         */

        var maxValue = -1L


        fun getNextR(r: Int, digit: Int) = (r + (digit % 3)) % 3

        fun getCurrentMax(selectedDigits: List<Int>): Long {
            val copy = selectedDigits.maxPriorityQueue()
            var result = 0L
            while (copy.isNotEmpty()) {
                result *= 10
                result += copy.poll()
            }
            return result
        }

        val selectedDigits =  mutableListOf<Int>()

        fun dfs(i: Int, r: Int) {
            if(r == 0 && selectedDigits.isNotEmpty()) {
                val currentMax = getCurrentMax(selectedDigits)
                maxValue = maxOf(currentMax, maxValue)
            }
            if(i > digits.lastIndex) return
            val current = digits[i]
            // 1. take
            selectedDigits.add(current)
            val nextR = getNextR(r, current)
            dfs(i + 1, nextR)
            selectedDigits.remove(current)
            // 2. leave
            dfs(i + 1, r)
        }

        dfs(0, 0)

        return if(maxValue == -1L) "" else maxValue.toString()
    }

    fun List<Int>.maxPriorityQueue(): PriorityQueue<Int> {
        val copy = PriorityQueue<Int>(Comparator.comparing { -it })
        copy.addAll(this)
        return copy
    }


    fun largestMultipleOfThree2(digits: IntArray): String {
        val sortedDigits = digits.sortedBy { -it }.toMutableList()
        val remainder = sortedDigits.sum() % 3

        fun getResult() = if(sortedDigits.isNotEmpty() && sortedDigits.all { it == 0} ) "0" else sortedDigits.joinToString("") { it.toString() }

        if(remainder == 0) return getResult()
        for(i in sortedDigits.lastIndex downTo 0) {
            if(sortedDigits[i] % 3 == remainder) {
                sortedDigits.removeAt(i)
                return getResult()
            }
        }

        var removed = 0
        for(i in sortedDigits.lastIndex downTo 0) {
            if(sortedDigits[i] % 3 ==  3 - remainder) {
                sortedDigits.removeAt(i)
                removed++
                if(removed == 2) return getResult()
            }
        }

        return ""
    }
}