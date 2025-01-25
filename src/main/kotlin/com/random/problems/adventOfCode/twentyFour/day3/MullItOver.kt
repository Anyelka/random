package com.random.problems.adventOfCode.twentyFour.day3

import com.random.util.getResourceAsText

const val FILE_PATH = "/adventOfCode/2024/day3/TestCase1"

const val MUL_REGEX = "mul\\((\\d{1,3}),\\s*(\\d{1,3})\\)"
const val DO_REGEX = "^do().*"
const val DONT_REGEX = "^don\'t().*"

const val MUL_PREFIX = "mul("
const val MUL_POSTFIX = ")"
const val MUL_SEPARATOR = ","

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val mulTotal = Part2.countTotalSumOfProducts(lines)

    println("Total product of uncorrupted mul instructions: $mulTotal")
}

fun mulRegex() = MUL_REGEX.toRegex()
fun mulRegexContains() = "$MUL_REGEX.*".toRegex()
fun doRegex() = DO_REGEX.toRegex()
fun dontRegex() = DONT_REGEX.toRegex()

object Part1 {
    fun countTotalSumOfProducts(lines: List<String>): Int =
            findExpressionsMatchingMulRegex(lines.joinToString()).map(::toNumbers).map(::productOf).sum()

    private fun findExpressionsMatchingMulRegex(line: String) = mulRegex().findAll(line).map { it.value }.toList()
}

object Part2 {
    fun countTotalSumOfProducts(lines: List<String>): Int {
        val mulRegex = mulRegex()
        val mulRegexContains = mulRegexContains()
        val doRegex = doRegex()
        val dontRegex = dontRegex()

        var productSum = 0

        var isEnabled = true
        val string = lines.joinToString()
        string.indices.forEach {
            val substring = string.substring(it)
            if(doRegex.matches(substring)) {
                isEnabled = true
            }
            if(dontRegex.matches(substring)) {
                isEnabled = false
            }
            if(isEnabled && mulRegexContains.matches(substring)) {
                val mulExpression = mulRegex.find(substring)!!.value
                productSum += productOf(toNumbers(mulExpression))
            }
        }

        return productSum
    }

}

private fun toNumbers(mulExpression: String): Pair<Int, Int> {
    val numbers = mulExpression.replace(MUL_PREFIX, "")
            .replace(MUL_POSTFIX, "")
            .split(MUL_SEPARATOR)
            .map { it.toInt() }
    return Pair(numbers[0], numbers[1])
}

private fun productOf(numbers: Pair<Int, Int>): Int {
    return numbers.first * numbers.second
}
