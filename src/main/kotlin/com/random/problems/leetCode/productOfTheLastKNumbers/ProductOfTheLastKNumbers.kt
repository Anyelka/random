package com.random.problems.leetCode.productOfTheLastKNumbers

import com.random.util.format
import com.random.util.isCorrectStringWithExpected
import com.random.util.test

fun main() {
    val input = listOf(
        (listOf("ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct")
            to
                listOf("","3","0","2","5","4","2","3","4","8","2")
        ) to listOf(null,null,null,null,null,null,20,40,0,null,32),
        (listOf("ProductOfNumbers","add","getProduct","getProduct","getProduct","add","add","add")
                to
                listOf("","1","1","1","1","7","6","7")
        ) to listOf(null,null,1,1,1,null,null,null)
    )

    val solution = Solution1()
    input.forEach {
        val input = it.first
        val (operations, parameters) = input
        val results = mutableListOf<Int?>()

        for(i in operations.indices) {
            val operation = operations[i]
            val parameter = parameters[i].toIntOrNull()
            val result = when(operation) {
                "add" -> {
                    solution.add(parameter!!)
                    null
                }
                "getProduct" -> solution.getProduct(parameter!!)
                else -> null
            }
            results.add(result)
        }

        println("Result for $operations and $parameters is: $results - ${isCorrectStringWithExpected(results, it.second)}")

    }
}