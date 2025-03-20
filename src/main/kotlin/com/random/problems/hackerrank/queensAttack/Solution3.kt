package com.random.problems.hackerrank.queensAttack

import kotlin.math.abs

class Solution3 {
    fun queensAttack(n: Int, k: Int, r_q: Int, c_q: Int, obstacles: Array<Array<Int>>): Int {
        return queensAttack3(n, k, r_q, c_q, obstacles)
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
        return (1..n).generateAllPairs().count { it.isAttackableBy(rQ to cQ, obstaclePairs) }
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

    // 2. Solution
    //      start from the queen's position and check each of the 8 directions:
    //          iterate over the tiles one-by-one until we reach an obstacle
    //
    //      Time complexity:    O(n)
    //      Space complexity:   O(k)
    //
    private fun queensAttack2(n: Int, k: Int, rQ: Int, cQ: Int, obstacles: Array<Array<Int>>): Int {
        val obstaclePairs = obstacles.map { it[0] to it[1] }
        return (-1..1)
            .generateAllPairs()
            .filter { it != (0 to 0) }
            .sumOf { (rQ to cQ).maxStepsInDirection(n, it, obstaclePairs) }
    }

    private fun Pair<Int, Int>.maxStepsInDirection(
        n: Int,
        direction: Pair<Int, Int>,
        obstacles: List<Pair<Int, Int>>
    ): Int {
        var current = this + direction
        var steps = 0
        while (!obstacles.contains(current) && current.isOnBoard(n)) {
            steps++
            current += direction
        }
        return steps
    }

    //  3. Solution
    //      start from the queen's position and check each of the 8 directions:
    //          filter the obstacles in the direction and calculate the distance between the closest obstacle and the queen
    //
    //      Time complexity:    O(k + n)
    //      Space complexity:   O(k + n)
    //
    private fun queensAttack3(n: Int, k: Int, rQ: Int, cQ: Int, obstacles: Array<Array<Int>>): Int {
        val allObstaclePairs = obstacles.getAllInQueensWay(rQ to cQ) + generateEdgesAroundBoard(n)
        return (-1..1)
            .generateAllPairs()
            .filter { it != (0 to 0) }
            .sumOf { (rQ to cQ).maxStepsInDirection2(it, allObstaclePairs) }
    }

    private fun Array<Array<Int>>.getAllInQueensWay(queen: Pair<Int, Int>) =
        this.map { it[0] to it[1] }.filter { it.isInLineWith(queen) }

    private fun Pair<Int, Int>.maxStepsInDirection2(direction: Pair<Int, Int>, obstacles: List<Pair<Int, Int>>): Int {
        return obstacles
            .filter { it.isInDirectionFrom(this, direction) }
            .minOf { it.distanceFrom(this) - 1 }
    }
}

private fun Pair<Int, Int>.isInDirectionFrom(from: Pair<Int, Int>, direction: Pair<Int, Int>) =
    (this.first.compareTo(from.first) to this.second.compareTo(from.second)) == direction
            && (direction.first == 0 || direction.second == 0 || this.isInDiagonalLineWith(from))

private fun Pair<Int, Int>.distanceFrom(other: Pair<Int, Int>) =
    if (first == other.first) abs(other.second - second) else abs(other.first - first)


private operator fun Pair<Int, Int>.plus(direction: Pair<Int, Int>): Pair<Int, Int> =
    first + direction.first to second + direction.second

private fun Pair<Int, Int>.isOnBoard(n: Int): Boolean = (first > 0 && second > 0) && (first <= n && second <= n)

private fun Pair<Int, Int>.isInLineWith(other: Pair<Int, Int>) =
    this != other && (isInCartesianLineWith(other) || isInDiagonalLineWith(other))

private fun Pair<Int, Int>.isInCartesianLineWith(other: Pair<Int, Int>) = first == other.first || second == other.second

private fun Pair<Int, Int>.isInDiagonalLineWith(other: Pair<Int, Int>) =
    abs(other.first - first) == abs(other.second - second)

private fun abs(num: Int) = if (num < 0) -num else num

private fun IntRange.generateAllPairs(): List<Pair<Int, Int>> = flatMap { i -> this.map { j -> i to j } }

private fun generateEdgesAroundBoard(boardSize: Int) = generateEdges(0, boardSize + 1)

private fun generateEdges(min: Int, max: Int) =
    (min..max).flatMap { listOf(min to it, max to it, it to min, it to max) }.toSet()