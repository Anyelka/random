package com.random.problems.other.dragonByte.twentySix.round1.subtractFirstDigit

import com.random.problems.other.dragonByte.Problem
import java.math.BigInteger

const val PATH = "/other/dragonByte/twentySix/round1/subtractFirstDigit"

class SubtractFirstDigit: Problem<BigInteger>() {
    override fun getPath(): String = PATH
    override fun convertInput(lines: List<String>): List<BigInteger> =
        lines.map { it.toBigInteger() }
    override fun solve(input: BigInteger): List<String> =
        listOf(Solution1().solve(input))
}