package com.random.problems.leetCode.onesAndZeroes

class Solution1 {
    fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
        return findMaxForm2(strs, m, n)
    }

    // 1. Backtracking with memo
    //      where s = length of strs
    //      TC: O(m * n * s)
    //      SC: O(m * n * s)
    fun findMaxForm1(strs: Array<String>, m: Int, n: Int): Int {
        val memo = mutableMapOf<Triple<Int,Int,Int>, Int>()
        return findMaxForm(strs, m, n, 0, memo)
    }

    fun findMaxForm(strs: Array<String>, m: Int, n: Int, i: Int, memo: MutableMap<Triple<Int,Int,Int>, Int>): Int {
        val key = Triple(m,n,i)
        if(memo.containsKey(key)) return memo[key]!!
        if(i == strs.size) return 0
        var mLeft = m
        var nLeft = n

        val currentStr = strs[i]
        var canBeSelected = true
        for(c in currentStr.toCharArray()) {
            if(c == '0') mLeft--
            else if(c == '1') nLeft--
            if(mLeft < 0 || nLeft < 0) {
                canBeSelected = false
                break
            }
        }

        val selected = if(canBeSelected) (1 + findMaxForm(strs, mLeft, nLeft, i + 1, memo)) else 0
        val notSelected = findMaxForm(strs, m, n, i + 1, memo)

        return maxOf(selected, notSelected).also { memo[key] = it }
    }


    fun findMaxForm2(strs: Array<String>, m: Int, n: Int): Int {
        val dp = Array(m + 1) { IntArray(n + 1) }

        for (str in strs) {
            val zeros = str.count { it == '0' }
            val ones = str.length - zeros

            for (i in m downTo zeros) {
                for (j in n downTo ones) {
                    dp[i][j] = maxOf(dp[i][j], 1 + dp[i - zeros][j - ones])
                }
            }
        }

        return dp[m][n]
    }
}