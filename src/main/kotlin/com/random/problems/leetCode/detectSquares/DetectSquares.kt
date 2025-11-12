package com.random.problems.leetCode.detectSquares

import com.random.util.format
import com.random.util.isCorrectStringWithExpected

fun main() {
    val input = listOf(
        (arrayOf("DetectSquares", "add", "add", "add", "count", "count", "add", "count") to arrayOf(
            intArrayOf(),
            intArrayOf(3, 10),
            intArrayOf(11, 2),
            intArrayOf(3, 2),
            intArrayOf(11, 10),
            intArrayOf(14, 8),
            intArrayOf(11, 2),
            intArrayOf(11, 10))
        ) to arrayOf(null, null, null, null, 1, 0, null, 2),
        (arrayOf("DetectSquares","add","add","add","count","count","add","count","add","add","count","add","count") to arrayOf(
            intArrayOf(),intArrayOf(3,10),intArrayOf(11,2),intArrayOf(3,2),intArrayOf(11,10),intArrayOf(14,8),intArrayOf(11,2),intArrayOf(11,10),intArrayOf(11,9),intArrayOf(10,9),intArrayOf(11,10),intArrayOf(10,10),intArrayOf(11,10)
        )) to arrayOf(null,null,null,null,1,0,null,2,null,null,2,null,3),
        getInput27()
    )

    input.forEach {
        val (input, expectedOutput) = it
        val (functions, parameters) = input

        val detectSquares = Solution2()

        val result = functions.withIndex().map { (index, function) ->
            when(function) {
                "add" -> {
                    detectSquares.add(parameters[index])
                    null
                }
                "count" -> detectSquares.count(parameters[index])
                else -> null
            }
        }


        println("Result for ${format(parameters)} is: ${format(result)} - ${isCorrectStringWithExpected(result, expectedOutput)}")
    }
}