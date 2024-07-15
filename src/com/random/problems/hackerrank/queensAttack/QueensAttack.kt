package com.random.problems.hackerrank.queensAttack

import com.random.problems.getResourceAsText
import kotlin.time.measureTime

val FILE_PATH = "/hackerrank/queensAttack/TestCase19"

fun main() {
    val inputFile = getResourceAsText(FILE_PATH)

    val lines: List<String> = inputFile!!.lines()
    val n = lines.first().split(" ")[0].toInt()
    val queenPosition: Pair<Int, Int> = Pair(lines[1].split(" ")[0].toInt(), lines[1].split(" ")[1].toInt())
    val obstacles: Array<Array<Int>> = lines.takeLast(lines.size - 2).map { line ->
        line.split(" ").map { it.toInt() }.toTypedArray()
    }.toTypedArray()

    val timeTaken = measureTime {
        val result = queensAttack(n, obstacles.size, queenPosition.first, queenPosition.second, obstacles)
        println("The queen can attack $result spaces")
    }
    println("Time taken: $timeTaken")
}

fun queensAttack(n: Int, k: Int, r_q: Int, c_q: Int, obstacles: Array<Array<Int>>): Int {
    var attackableSquares: Int = 0
    for (i in -1..1) {
        for (j in -1..1) {
            attackableSquares += attackableSquares(Pair(i,j),n,Pair(r_q,c_q),obstacles)
        }
    }
    return attackableSquares
}

fun attackableSquares(direction: Pair<Int, Int>, n: Int, queenPosition: Pair<Int, Int>, obstacles: Array<Array<Int>>): Int {
    if((direction.first == 0 && direction.second == 0)) {
        return 0
    }
    val nextPosition = queenPosition.move(direction)
    if(nextPosition.first > n || nextPosition.first < 1 || nextPosition.second > n || nextPosition.second < 1) {
        return 0
    }
    val isObstacle = obstacles.any { it[0] == nextPosition.first && it[1] == nextPosition.second }
    if(isObstacle) {
        return 0
    }
    return 1 + attackableSquares(direction, n, nextPosition, obstacles)
}

fun Pair<Int,Int>.move(moveCoordinates: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first + moveCoordinates.first, this.second + moveCoordinates.second)
}
