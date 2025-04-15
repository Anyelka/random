package com.random.problems.leetCode.validSudoku

import com.random.util.test

fun main() {
    val input = getInput()
    
    input.forEach {
        val board = it.first
        it.test { Solution1().isValidSudoku(board) }
    }
}

fun getInput(): List<Pair<Array<CharArray>, Boolean>> {
    return listOf(
        arrayOf(charArrayOf('5','3','.','.','7','.','.','.','.')
            ,charArrayOf('6','.','.','1','9','5','.','.','.')
            ,charArrayOf('.','9','8','.','.','.','.','6','.')
            ,charArrayOf('8','.','.','.','6','.','.','.','3')
            ,charArrayOf('4','.','.','8','.','3','.','.','1')
            ,charArrayOf('7','.','.','.','2','.','.','.','6')
            ,charArrayOf('.','6','.','.','.','.','2','8','.')
            ,charArrayOf('.','.','.','4','1','9','.','.','5')
            ,charArrayOf('.','.','.','.','8','.','.','7','9')) to true,

        arrayOf(charArrayOf('8','3','.','.','7','.','.','.','.')
            ,charArrayOf('6','.','.','1','9','5','.','.','.')
            ,charArrayOf('.','9','8','.','.','.','.','6','.')
            ,charArrayOf('8','.','.','.','6','.','.','.','3')
            ,charArrayOf('4','.','.','8','.','3','.','.','1')
            ,charArrayOf('7','.','.','.','2','.','.','.','6')
            ,charArrayOf('.','6','.','.','.','.','2','8','.')
            ,charArrayOf('.','.','.','4','1','9','.','.','5')
            ,charArrayOf('.','.','.','.','8','.','.','7','9')) to false
    )
}
