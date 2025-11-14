package com.random.problems.leetCode.maximumNumberOfOperationsToMoveOnesToTheEnd

import kotlin.math.max

class Solution1 {
    fun maxOperations(s: String): Int {
        return maxOperations3(s)
    }

    // 1. Solution:
    //  Iterate through chars and shift all shiftable 1s from left to right
    //      -> count operations
    //      -> if we had any operations, do shifting once more
    //      -> if we had no operations, return 0
    //
    //      TC: O(n ^ 2)
    //      SC: O(n)
    //
    //      TOO SLOW
    //
    fun maxOperations1(s: String): Int {
        return maxOperations2(s.toCharArray())
    }

    fun maxOperations1(string: CharArray): Int {
        var operations = 0

        var i = 0
        while(i < string.lastIndex) {
            if(string[i] == '1') {
                var j = i + 1
                while(j < string.size && string[j] == '0') {
                    j++
                }
                if(j > i + 1) {
                    string[i] = '0'
                    string[j - 1] = '1'
                    operations++
                    continue
                }
            }
            i++
        }

        return if(operations == 0) 0 else operations + maxOperations1(string)
    }

    // same as solution 1 but Loop instead of recursion
    //      TOO SLOW
    //
    fun maxOperations2(string: CharArray): Int {
        var operations = 0
        var finished = false

        while(!finished) {
            var i = 0
            var localOperations = 0
            while(i < string.lastIndex) {
                if(string[i] == '1') {
                    var j = i + 1
                    while(j < string.size && string[j] == '0') {
                        j++
                    }
                    if(j > i + 1) {
                        string[i] = '0'
                        string[j - 1] = '1'
                        localOperations++
                        continue
                    }
                }
                i++
            }
            if(localOperations == 0) finished = true
            operations += localOperations
        }

        return operations
    }

    fun maxOperations3(s: String): Int {
        var operations = 0
        var zeroSections = 0
        var i = s.lastIndex
        while(i >= 0) {
            var oneCount = 0
            while(i >= 0 && s[i] == '1') {
                oneCount++
                i--
            }
            operations += oneCount * zeroSections
            while(i >= 0 && s[i] == '0') {
                i--
            }
            zeroSections++
        }

        return operations
    }


}