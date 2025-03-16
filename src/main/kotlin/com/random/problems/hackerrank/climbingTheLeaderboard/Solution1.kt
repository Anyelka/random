package com.random.problems.hackerrank.climbingTheLeaderboard

class Solution1 {
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

}