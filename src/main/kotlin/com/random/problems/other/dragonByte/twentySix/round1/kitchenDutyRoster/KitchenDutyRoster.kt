package com.random.problems.other.dragonByte.twentySix.round1.kitchenDutyRoster

import com.random.problems.other.dragonByte.Problem

const val PATH = "/other/dragonByte/twentySix/round1/kitchenDutyRoster"

class KitchenDutyRoster: Problem<Triple<Int,Int, Boolean>>() {
    override fun getPath(): String = PATH

    override fun convertInput(lines: List<String>): List<Triple<Int, Int, Boolean>> =
        lines
            .map { it.split(" ") }
            .map { Triple(it[0].toInt(), it[1].toInt(), parseBoolean(it[2])) }

    private fun parseBoolean(str: String) =
        when (str) {
            "1" -> true
            "0" -> false
            else -> str.toBoolean()
        }

    override fun solve(input: Triple<Int, Int, Boolean>): List<String> {
        val (n,k,p) = input
        return Solution1().solve(n,k,p)
    }
}