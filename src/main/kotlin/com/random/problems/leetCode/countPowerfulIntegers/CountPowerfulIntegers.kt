package com.random.problems.leetCode.countPowerfulIntegers

import com.random.util.test

fun main() {
    val input = listOf(
        (((1 to 6000) to 4) to "124") to 5L,
        (((15 to 215) to 6) to "10") to 2L,
        (((1000 to 2000) to 4) to "3000") to 0L,
        (((20 to 1159) to 5) to "20") to 8L,
        (((1829505 to 1255574165) to 7) to "11223") to 5470L,
        (((1 to 971) to 9) to "72") to 9L,
        (((15398 to 1424153842) to 8) to "101") to 783790L,

        (((1114 to 1864854501) to 7) to "26") to 4194295L
        //  20  120 220 320 420 520 1020 1120

        //  l = 5
        //  if we remove l=5 places from the end:
        // start = 18 end = 12555

    )

    input.forEach {
        val (start, finish) = (it.first.first.first.first.toLong() to it.first.first.first.second.toLong())
        val limit = it.first.first.second
        val s = it.first.second
        it.test { Solution1().numberOfPowerfulInt(start, finish, limit, s) }
    }
}