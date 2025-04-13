package com.random.problems.leetCode.zigzagconversion

class Solution2 {
    fun convert(s: String, numRows: Int): String {
        // P.    H
        // A.  S I
        // Y. I. R
        // P L.  I G
        // A.    N
        // numRows = 5
        if(numRows == 1) return s

        val rows = Array<MutableList<Char>>(numRows) { mutableListOf() }
        var row = 0
        var isAscending = true
        for(element in s) {
            rows[row].add(element)
            if(isAscending) {
                row++
            } else {
                row--
            }
            if(row == numRows - 1 || row == 0) isAscending = !isAscending
        }
        return rows.joinToString("") { it.joinToString("") }
    }
}