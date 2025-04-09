package com.random.problems.hackerrank.theGridSearch

class Solution1 {
    fun gridSearch(G: Array<String>, P: Array<String>): String {
        return gridSearch1(G, P)
    }

    // 1. Solution:
    //      Time complexity:    O(m * n * p * q)
    //      Space complexity:   O(1)
    private fun gridSearch1(G: Array<String>, P: Array<String>): String {
        for (i in G.indices) {
            for (j in G[i].indices) {
                if (G[i][j] == P[0][0]) {
                    if (G.size - i >= P.size && G[i].length - j >= P[0].length) {
                        if (findMatchStartingFrom(G, P, i, j)) return "YES"
                    }
                }
            }
        }
        return "NO"
    }

    fun findMatchStartingFrom(G: Array<String>, P: Array<String>, i: Int, j: Int): Boolean {
        for(k in P.indices) {
            for(l in P[k].indices) {
                if(G[i+k][j+l] != P[k][l]) {
                    return false
                }
            }
        }
        return true
    }
}