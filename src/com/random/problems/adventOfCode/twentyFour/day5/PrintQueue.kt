package com.random.problems.adventOfCode.twentyFour.day5

import com.random.util.getResourceAsText

const val FILE_PATH = "/adventOfCode/2024/day5/TestCase1"

const val RULE_DELIMITER = "|"
const val UPDATE_DELIMITER = ","

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val middleSum = Part2.countMiddleSum(lines)

    println("Total sum of middle numbers in correctly ordered: $middleSum")
}
abstract class Part {
    fun countMiddleSum(lines: List<String>): Int {
        val separatorIndex = lines.indexOfFirst { it.isEmpty() }
        val rules = getRules(lines, separatorIndex)
        val updates = getUpdates(lines, separatorIndex)
        return countMiddleSum(rules, updates)
    }

    abstract fun countMiddleSum(rules: List<Pair<Int, Int>>, updates: List<List<Int>>): Int

    private fun getRules(lines: List<String>, separatorIndex: Int) = lines.take(separatorIndex)
            .map { it.split(RULE_DELIMITER) }
            .map { numbers -> Pair(numbers[0].toInt(), numbers[1].toInt()) }

    private fun getUpdates(lines: List<String>, separatorIndex: Int) =
            lines.drop(separatorIndex + 1).map { line -> line.split(UPDATE_DELIMITER).map { it.toInt() } }

    fun List<Int>.isCorrectlyOrdered(rules: List<Pair<Int, Int>>): Boolean {
        return rules.none { isBroken(it, this) }
    }

    private fun isBroken(rule: Pair<Int, Int>, update: List<Int>): Boolean {
        val firstIndex = update.indexOf(rule.first)
        val secondIndex = update.indexOf(rule.second)
        return firstIndex > -1 && secondIndex > -1 && secondIndex < firstIndex
    }

    fun List<Int>.findMiddleNumber(): Int =
            if(this.size % 2 == 0) this[this.size / 2 - 1] else this[this.size / 2]
}

object Part1: Part() {
    override fun countMiddleSum(rules: List<Pair<Int, Int>>, updates: List<List<Int>>): Int =
            updates.filter { update -> update.isCorrectlyOrdered(rules) }.sumOf { it.findMiddleNumber() }

}

object Part2: Part() {
    override fun countMiddleSum(rules: List<Pair<Int, Int>>, updates: List<List<Int>>): Int =
            updates.filter { update -> !update.isCorrectlyOrdered(rules) }.map { it.reorder(rules) }.sumOf { it.findMiddleNumber() }

    private fun List<Int>.reorder(rules: List<Pair<Int, Int>>): List<Int> {
        val pagesCopy = this.toMutableList()
        rules.forEach { rule ->
            val firstIndex = pagesCopy.indexOf(rule.first)
            val secondIndex = pagesCopy.indexOf(rule.second)
            if(firstIndex > -1 && secondIndex > -1 && secondIndex < firstIndex) {
                pagesCopy.swap(firstIndex, secondIndex)
            }
        }
        return if(pagesCopy.isCorrectlyOrdered(rules)) pagesCopy else pagesCopy.reorder(rules)
    }
}

private fun <E> MutableList<E>.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}
