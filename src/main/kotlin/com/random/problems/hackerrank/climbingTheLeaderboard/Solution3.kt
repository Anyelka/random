package com.random.problems.hackerrank.climbingTheLeaderboard

class Solution3 {
    // TC: O(n+m)
    // SC: O(1)
    fun climbingLeaderboard(ranked: Array<Int>, player: Array<Int>): Array<Int> {
        val result = Array(player.size) { 0 }

        var rank = 1
        var rankIndex = 0
        var playerIndex = player.size - 1

        while(playerIndex >= 0) {
            while(rankIndex < ranked.size && player[playerIndex] < ranked[rankIndex]) {
                rankIndex++
                if(rankIndex >= ranked.size || ranked[rankIndex] < ranked[rankIndex - 1]) rank++
            }

            result[playerIndex] = rank
            playerIndex--
        }

        return result
    }
}