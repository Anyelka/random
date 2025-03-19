package com.random.problems.leetCode.zigzagconversion

class Solution1 {
    fun convert(s: String, numRows: Int): String {
        return convert1(s, numRows)
    }

    // 1. Solution:
    //          go through the letters one-by-one
    //              we keep track of the row number and increment it based on row direction
    //              add them into a string by row
    //              if we get to the edge (row number = numRows or row number = 0) we change the row direction
    //          concat the strings by row
    //
    //          Time complexity:    O(n)
    //          Space complexity:   O(n)
    //
    private fun convert1(s: String, numRows: Int): String {
        if(numRows == 1) return s

        val rowStrings = MutableList(numRows) { mutableListOf<Char>() }
        var row = 0
        var isAscRow = false

        for(c in s) {
            rowStrings[row].add(c)
            if(row == numRows - 1 || row == 0) isAscRow = !isAscRow
            row += if(isAscRow) 1 else -1
        }

        return rowStrings.joinToString("") { it.joinToString("") }
    }

}