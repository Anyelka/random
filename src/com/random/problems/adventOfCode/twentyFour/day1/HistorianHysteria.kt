package com.random.problems.adventOfCode.twentyFour.day1

import com.random.algorithms.sorts.kotlin.QuickSort.quickSortList
import com.random.util.getResourceAsText
import kotlin.math.abs
import kotlin.time.measureTime

const val FILE_PATH = "/adventOfCode/2024/day1/TestCase1"

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val lists = getInput(lines)

   /* val distanceSum = Part1.calculateDistanceSum(lists)

    println("Total distances: $distanceSum")*/

    val timeTaken = measureTime {
        val similarityIndex = Part2.calculateSimilarityIndexV4(lists)
        println("Similarity Index: $similarityIndex")
    }
    println("       -> Time taken: $timeTaken")
}

fun getInput(lines: List<String>): Pair<MutableList<Int>, MutableList<Int>> {
    val firstList = ArrayList<Int>()
    val secondList = ArrayList<Int>()
    lines.forEach { it ->
        val (left, right) = it.split("   ").map { it.toInt() }
        firstList.add(left)
        secondList.add(right)
    }
    return Pair(firstList, secondList)
}

private object Part1 {
    fun calculateDistanceSum(lists: Pair<MutableList<Int>, MutableList<Int>>): Int {
        sort(lists.first)
        sort(lists.second)

        return lists.first.indices.reduce { acc, index ->
            acc + abs(lists.first[index] - lists.second[index])
        }
    }
}

private object Part2 {
    // Performances for TestCase1 - big input:
    //      V1: ~ 12 ms
    //      V2: ~ 16 ms
    //      V3: ~ 19 ms
    //      V4: ~ 15 ms

    // V1:  Brute force solution with always iterating over the whole second list
    //      does not require sorting though
    //
    fun calculateSimilarityIndexV1(lists: Pair<MutableList<Int>, MutableList<Int>>): Int {
        var totalSimIndex = 0
        lists.first.forEach { i ->
            var occurencesInSecondList = 0
            lists.second.forEach { j ->
                if(j == i) {
                    occurencesInSecondList++
                }
            }
            totalSimIndex += i * occurencesInSecondList
        }
        return totalSimIndex
    }

    // Only sorting second list and for each element in the first list, iterating over the second until
    //      that value is found, and count how many times it appears, but after we find the next higher value,
    //      we can break out
    fun calculateSimilarityIndexV2(lists: Pair<MutableList<Int>, MutableList<Int>>): Int {
        sort(lists.second)
        var totalSimIndex = 0
        lists.first.forEach { i ->
            var j = 0
            var runOver = false
            var occurencesInSecondList = 0
            while(j < lists.second.size && !runOver) {
                if(lists.second[j] == i) {
                    occurencesInSecondList++
                } else if(occurencesInSecondList > 0) {
                    runOver = true
                }
                j++
            }
            totalSimIndex += i * occurencesInSecondList
        }
        return totalSimIndex
    }

    // Sorting second list with memoization
    fun calculateSimilarityIndexV3(lists: Pair<MutableList<Int>, MutableList<Int>>): Int {
        sort(lists.second)

        val memo = HashMap<Int, Int>()

        var totalSimIndex = 0
        lists.first.forEach { i ->
            if(memo.containsKey(i)) {
                totalSimIndex += memo[i]!!
            }

            var j = 0
            var runOver = false
            var occurencesInSecondList = 0
            while(j < lists.second.size && !runOver) {
                if(lists.second[j] == i) {
                    occurencesInSecondList++
                } else if(occurencesInSecondList > 0) {
                    runOver = true
                }
                j++
            }
            val value = i * occurencesInSecondList
            totalSimIndex += value
            memo[i] = value
        }
        return totalSimIndex
    }

    // With kotlin built in functions
    fun calculateSimilarityIndexV4(lists: Pair<MutableList<Int>, MutableList<Int>>): Int {
        val first = lists.first.sorted()
        val second = lists.second.sorted()
        return first.sumOf { left -> left * second.count { right -> right == left } }
    }

}


fun sort(list: MutableList<Int>) {
    quickSortList(list)
}
