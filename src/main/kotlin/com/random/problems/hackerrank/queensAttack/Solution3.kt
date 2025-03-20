package com.random.problems.hackerrank.queensAttack

import kotlin.math.abs

class Solution3 {
    fun queensAttack(n: Int, k: Int, r_q: Int, c_q: Int, obstacles: Array<Array<Int>>): Int {
        return queensAttackBruteForce(n, k, r_q, c_q, obstacles)
    }

    // 1. Brute Force Solution:
    //      go through each tile on the board and count the ones that the queen can attack
    //
    //      Time complexity:    O(n^3)
    //      Space complexity:   O(k)
    //          - where n is the size of the board and k is the number of obstacles
    //
    //
    private fun queensAttackBruteForce(n: Int, k: Int, rQ: Int, cQ: Int, obstacles: Array<Array<Int>>): Int {
        val obstaclePairs = obstacles.map { it[0] to it[1] }
        return (1..n).allCoordinates().count { it.isAttackableBy(rQ to cQ, obstaclePairs) }
    }
}

private fun Pair<Int, Int>.isAttackableBy(queen: Pair<Int, Int>, obstacles: List<Pair<Int, Int>>): Boolean {
    if (!isInLineWith(queen)) return false

    var (i, j) = this
    val (rowIncrement, colIncrement) = queen.first.compareTo(this.first) to queen.second.compareTo(this.second)

    while ((i to j) != queen) {
        if (obstacles.contains(i to j)) return false
        i += rowIncrement
        j += colIncrement
    }

    return true
}

private fun Pair<Int, Int>.isInLineWith(other: Pair<Int, Int>) =
    this != other && (isInCartesianLineWith(other) || isInDiagonalLineWith(other))

private fun Pair<Int, Int>.isInCartesianLineWith(other: Pair<Int, Int>) = first == other.first || second == other.second

private fun Pair<Int, Int>.isInDiagonalLineWith(other: Pair<Int, Int>) =
    abs(other.first - first) == abs(other.second - second)

private fun abs(num: Int) = if (num < 0) -num else num

private fun IntRange.allCoordinates(): List<Pair<Int, Int>> = flatMap { i -> this.map { j -> i to j } }