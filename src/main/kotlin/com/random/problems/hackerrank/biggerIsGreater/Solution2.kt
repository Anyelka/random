package com.random.problems.hackerrank.biggerIsGreater

import com.random.util.swap

class Solution2 {
    //  Time complexity:    O(2*n)
    //  Space complexity:   O(n)
    fun biggerIsGreater(w: String): String {
        val result = w.toCharArray()

        var i = w.length - 2
        while (i >= 0 && w[i].code >= w[i+1].code) i--

        if(i < 0) return "no answer"

        var j = w.length - 1
        while(w[i].code >= w[j].code) j--

        result.swap(i, j)

        return result.concatToString().substring(0, i + 1) + result.concatToString().substring(i + 1).reversed()
    }
}