package com.random.problems.other.dragonByte.twentySix.round2.betterDataTransfer

import com.random.problems.other.dragonByte.Problem

const val PATH = "/other/dragonByte/twentySix/round2/betterDataTransfer"


class BetterDataTransfer: Problem<Triple<Int,Int, IntArray>>() {
    override fun getPath(): String = PATH

    override fun convertInput(lines: List<String>): List<Triple<Int, Int, IntArray>> {
        return lines.chunked(2).map { (header, values) ->
            val (first, second) = header.split(" ").map(String::toInt)
            val array = values.split(" ").map(String::toInt).toIntArray()
            Triple(first, second, array)
        }
    }

    override fun solve(input: Triple<Int, Int, IntArray>): List<String> {
        val (s, h, aArray) = input
        return Solution1().solve(s,h,aArray)
    }
}