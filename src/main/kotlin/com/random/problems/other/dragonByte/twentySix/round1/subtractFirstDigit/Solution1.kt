package com.random.problems.other.dragonByte.twentySix.round1.subtractFirstDigit

import com.random.util.pow
import java.math.BigInteger

class Solution1 {
    val modulo = (10.pow(9) + 7).toBigInteger()

    fun solve(n: BigInteger): String {
        return solveS3(n.toBigDecimal().toBigInteger())
    }

    fun solveS1(n: BigInteger): String {
        var number = n
        var steps = 0
        while(number > BigInteger.ZERO) {
            val firstDigit = number.toString()[0].digitToInt().toBigInteger()
            number -= firstDigit
            steps++
        }
        return "" + steps
    }

    fun solveS2(n: BigInteger): String {
        fun findRemainingSteps(number: BigInteger): BigInteger {
            if(number == BigInteger.ZERO) return BigInteger.ZERO
            val numberAsString = number.toString()
            val (firstDigit, rest) = split(number)
            val steps = (rest / firstDigit) + BigInteger.ONE
            return steps + findRemainingSteps(number - steps * firstDigit)
        }
        return "" + findRemainingSteps(n) % modulo
    }


    fun split(number: BigInteger): Pair<BigInteger, BigInteger> {
        val s = number.toString()

        val firstDigit = s[0].digitToInt().toBigInteger()
        val rest = if (s.length > 1) {
            BigInteger(s.substring(1))
        } else {
            BigInteger.ZERO
        }

        return firstDigit to rest
    }

    // Maybe cache the result for each number ?
    val memo = mutableMapOf<BigInteger, BigInteger>()

    fun solveS3(n: BigInteger): String {
        fun findRemainingSteps(number: BigInteger): BigInteger {
            if(number == BigInteger.ZERO) return BigInteger.ZERO
            if(memo.containsKey(number)) return memo[number]!!
            val numberAsString = number.toString()
            val (firstDigit, rest) = split(number)
            val steps = (rest / firstDigit) + BigInteger.ONE
            return (steps + findRemainingSteps(number - steps * firstDigit)).also {
                memo[number] = it
            }
        }
        return "" + findRemainingSteps(n) % modulo
    }

}