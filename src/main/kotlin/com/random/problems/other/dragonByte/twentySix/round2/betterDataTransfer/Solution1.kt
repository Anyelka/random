package com.random.problems.other.dragonByte.twentySix.round2.betterDataTransfer

import java.math.BigInteger

class Solution1 {
    fun solve(s: Int, h: Int, aArray: IntArray): List<String> {
        /*val optimalCost = ""
        val optimalSolutionExample = ""
        return listOf(
            optimalCost,
            optimalSolutionExample
        )*/
        return solve1(s,h,aArray)
    }

    fun solve1(s: Int, h: Int, aArray: IntArray): List<String> {
        // we can assume s <= 10, h = 1
        return solveWithH1(s, h, aArray)
    }

    fun solveWithH1(s: Int, h: Int, aArray: IntArray): List<String> {
        val aSorted = aArray.sortedDescending()
        val encodings = Array<String>(s) { "" }
        var char = '0'
        var prefix = ""
        var totalCost = BigInteger.ZERO
        for(i in 0..<s) {
            var nextChar = char
            when(char) {
                '0' -> {
                    encodings[i] = prefix + nextChar
                    nextChar = '*'
                    totalCost += (prefix.length + 1).toBigInteger() * aSorted[i].toBigInteger()
                }
                '*' -> {
                    encodings[i] = prefix + nextChar
                    nextChar = '1'
                    totalCost += (prefix.length + 1).toBigInteger() * aSorted[i].toBigInteger()
                }
                else -> {
                    nextChar = '0'
                    if(i == s - 1) {
                        encodings[i] = prefix + "1"
                        totalCost += (prefix.length + 1).toBigInteger() * aSorted[i].toBigInteger()
                    } else {
                        encodings[i] = prefix + "1" + nextChar
                        prefix += "1"
                        totalCost += (prefix.length + 1).toBigInteger() * aSorted[i].toBigInteger()
                        nextChar = '*'
                    }
                }
            }
            char = nextChar
        }

        return listOf(
            "" + totalCost,
            encodings.joinToString(" ")
        )

    }
}