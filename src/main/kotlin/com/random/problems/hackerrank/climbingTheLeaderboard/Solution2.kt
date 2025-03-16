package com.random.problems.hackerrank.climbingTheLeaderboard

class Solution2 {

    fun climbingLeaderboard(ranked: Array<Int>, player: Array<Int>): Array<Int> {
        return climbingLeaderboardOrdered(player, ranked)
    }

    // Naive scoring solution:
    //          for each player, go through the rankings from the first player and place them in their place
    //
    //
    //      Time complexity: O(n * m)
    //      Space complexity: O(1)
    //          where:
    //              - n = the number of players, and
    //              - m = the size of the leaderboard
    private fun climbingLeaderboardNaive(player: Array<Int>, ranked: Array<Int>) =
        player.map { it.getRanking(ranked) }.toTypedArray()

    private fun Int.getRanking(ranked: Array<Int>): Int {
        var prevScore = Int.MAX_VALUE
        var prevRank = 0
        for(score in ranked) {
            if(score < prevScore) prevRank++
            if(this >= score) return prevRank
            prevScore = score
        }
        return prevRank + 1
    }

    // Ordered approach:
    //      in this approach we utilize the fact that the player scores are in ascending order as well as the fact that
    //      the leaderboard is in descending order
    //
    //
    //      Time complexity: O(n + m)
    //      Space complexity: O(1)
    //          where:
    //              - n = the number of players, and
    //              - m = the size of the leaderboard
    private fun climbingLeaderboardOrdered(player: Array<Int>, ranked: Array<Int>): Array<Int> {
        var prevScore = Int.MAX_VALUE
        var prevRank = 0
        var currentPlayerIndex = player.size - 1
        val ranks = Array(player.size) { 0 }

        for(score in ranked) {
            if(currentPlayerIndex < 0) break
            if(score < prevScore) prevRank++

            while(currentPlayerIndex >= 0 && player[currentPlayerIndex] >= score) {
                ranks[currentPlayerIndex] = prevRank
                currentPlayerIndex--
            }
            prevScore = score
        }

        while(currentPlayerIndex >= 0) {
            ranks[currentPlayerIndex] = prevRank + 1
            currentPlayerIndex--
        }

        return ranks
    }
}
