package com.random.problems.leetCode.validSudoku

class Solution1 {
    // 1. Solution:
    //  go through each row, col and box and check for duplicates
    //
    //      TC:     O(1) = O(9) * O(9) + O(9) * O(9) + O(9) * O(9) = O(n ^ 2) where n = 9
    //      TC:     O(1) = O(n)
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        fun invalidRow(index: Int): Boolean = containsDuplicate(board[index].toTypedArray())
        fun invalidColumn(index: Int): Boolean = containsDuplicate(board.map { it[index] }.toTypedArray())

        fun hasInvalidBox() = (0..2).flatMap { boxRow ->
            (0..2).map { boxCol ->
                (boxRow * 3 until boxRow * 3 + 3).flatMap { i ->
                    (boxCol * 3 until boxCol * 3 + 3).map { j -> board[i][j] }
                }.toTypedArray()
            }
        }.any { containsDuplicate(it) }

        fun hasInvalidRowOrCol() = (0..8).any { invalidRow(it) || invalidColumn(it) }

        return !(hasInvalidRowOrCol() || hasInvalidBox())
    }

    private fun containsDuplicate(array: Array<Char>) = containsDuplicate(array, mutableSetOf())
    private fun containsDuplicate(array: Array<Char>, set: MutableSet<Char>) = array.any { it != '.' && !set.add(it) }

}
