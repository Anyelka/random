package com.random.problems.leetCode.satisfiabilityOfEqualityOfEquations

import com.random.util.test

fun main() {
    val input = listOf(
        arrayOf("a==b","b!=a") to false,
        arrayOf("b==a","a==b") to true,
        arrayOf("a==b","b==c") to true,
        arrayOf("c==c","b==d","x!=z") to true
    )

    input.forEach {
        val equations = it.first
        it.test { Solution1().equationsPossible(equations) }
    }

}