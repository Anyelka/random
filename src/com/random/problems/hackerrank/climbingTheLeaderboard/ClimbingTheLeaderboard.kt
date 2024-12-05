package com.random.problems.hackerrank.climbingTheLeaderboard

import com.random.util.getResourceAsText
import kotlin.math.floor
import kotlin.time.measureTime

const val FILE_PATH = "/hackerrank/climbingTheLeaderboard/TestCase1"

fun main() {
    val file = getResourceAsText(FILE_PATH)

    val ranked = file!!.lines()[1].split(" ").map { it.toInt() }.toTypedArray()
    val player = file.lines()[3].split(" ").map { it.toInt() }.toTypedArray()

    val time = measureTime {
        val result = climbingLeaderboard(ranked, player)
        result.forEach {  println(it) }
    }
    println("Time taken: $time")
}

fun climbingLeaderboard(ranked: Array<Int>, player: Array<Int>): Array<Int> {
    val ranks = HashMap<Int, Int>()
    val result = ArrayList<Int>()
    for(playerScore in player) {
        var playerRank = ranks[playerScore]
        if(playerRank == null) {
            playerRank = getRank(ranked, playerScore)
            ranks.put(playerScore, playerRank)
        }
        result.add(playerRank)
    }
    return result.toTypedArray()
}

//default solution: too slow
fun getRank(ranked: Array<Int>, playerScore: Int): Int {
    return 0
}

