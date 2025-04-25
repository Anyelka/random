package com.random.problems.hackerrank.magicSquare

import kotlin.math.abs

val magicSquare = arrayOf(
    arrayOf(8, 3, 4),
    arrayOf(1, 5, 9),
    arrayOf(6, 7, 2)
)

class Solution1 {
    fun formingMagicSquare(s: Array<Array<Int>>): Int {
        return generateAllMagicSquares().minOf { s.costOfConversionTo(it) }
    }

    private fun generateAllMagicSquares(): List<Array<Array<Int>>> {
        val result = mutableListOf<Array<Array<Int>>>()
        magicSquare
            .also { result.add(it) }.also { result.add(it.mirror()) }.rotate()
            .also { result.add(it) }.also { result.add(it.mirror()) }.rotate()
            .also { result.add(it) }.also { result.add(it.mirror()) }.rotate()
            .also { result.add(it) }.also { result.add(it.mirror()) }
        return result
    }

    fun Array<Array<Int>>.rotate(): Array<Array<Int>> {
        val result = Array(this.size) {Array(this.size) { 0 } }
        val n = this.size - 1
        this.withIndex().forEach { (i, row) ->
            row.withIndex().forEach { (j, value) ->
                result[j][n - i] = value
            }
        }
        return result
    }

    fun Array<Array<Int>>.mirror(): Array<Array<Int>> {
        val result = Array(this.size) {Array(this.size) { 0 } }
        this.withIndex().forEach { (i, row) ->
            row.withIndex().forEach { (j, value) ->
                result[j][i] = value
            }
        }
        return result
    }

    private fun Array<Array<Int>>.costOfConversionTo(other: Array<Array<Int>>): Int {
        return this.withIndex().sumOf { (i, row) ->
            row.withIndex().sumOf { (j, value) ->
                abs(value - other[i][j])
            }
        }
    }
}
