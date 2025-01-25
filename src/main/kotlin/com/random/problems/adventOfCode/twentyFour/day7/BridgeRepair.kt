package com.random.problems.adventOfCode.twentyFour.day7

import com.random.util.getResourceAsText

const val FILE_PATH = "/adventOfCode/2024/day7/TestCase1"


fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val equations = lines.map { it.toEquation() }
    Part(listOf(Operation.MULTIPLY, Operation.ADD)).run(equations)
    Part(listOf(Operation.MULTIPLY, Operation.ADD, Operation.CONCAT)).run(equations)

}

private fun String.toEquation(): Pair<Long, List<Long>> {
    val parts = this.split(": ")
    return parts[0].toLong() to parts[1].split(" ").map { it.toLong() }.toList()
}


class Part(private val operations: List<Operation>) {

    fun run(equations: List<Pair<Long, List<Long>>>) {
        println("The sum of equations that can be made true with the operations: $operations " +
                "is: ${equations.getTotalCalibrationResultWith(operations)} ")
    }

    private fun List<Pair<Long, List<Long>>>.getTotalCalibrationResultWith(operations: List<Operation>): Long {
        return this.filter { it.canBeTrueWithOperations(operations) }.sumOf { it.first }
    }

    private fun  Pair<Long, List<Long>>.canBeTrueWithOperations(operations: List<Operation>): Boolean {
        return this.second.canSumUpTo(this.first, 0L, operations)
    }

    private fun List<Long>.canSumUpTo(target: Long, sum: Long, allowedOperations: List<Operation>): Boolean {
        if(isEmpty()) {
            return target == sum
        }

        val number = this[0]
        val nextNumbers = this.drop(1)
        return allowedOperations.any { nextNumbers.canSumUpTo(target, it.sumFunction(sum, number), allowedOperations) }
    }
}

enum class Operation(val sumFunction: (Long, Long) -> Long) {
    MULTIPLY({sum, number -> sum + number}),
    ADD({sum, number -> if(sum == 0L) number else sum * number}),
    CONCAT({sum, number -> "$sum$number".toLong()})
}