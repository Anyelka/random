package com.random.problems.hackerrank.powerSum

import com.random.util.getResourceAsText
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.time.measureTime

const val FILE_PATH = "/hackerrank/powerSum/TestCase0"

fun main() {
    val file = getResourceAsText(FILE_PATH)

    val x = file!!.lines()[0].toInt()
    val n = file.lines()[1].toInt()

    val time = measureTime {
        val result = powerSum(x, n)
        println(result)
    }
    println("Time taken: $time")
}

fun powerSum(X: Int, N: Int): Int {
    val collectedPows = ArrayList<MutableList<Int>>()
    collectPows(1, X, N, ArrayList(), collectedPows)
    return collectedPows.size
}

fun collectPows(number: Int, remainingSum: Int, power: Int, previousPows: MutableList<Int>, collectedPows: MutableList<MutableList<Int>>) {
    val numberPower = number.pow(power)
    val nextRemainingSum = remainingSum - numberPower
    if(numberPower > remainingSum) {
        return
    }
    if(numberPower == remainingSum) {
        previousPows.add(numberPower)
        if(!collectedPows.contains(previousPows)) {
            collectedPows.add(previousPows)
        }
    }
    val nextPreviousPows = previousPows.toMutableList()
    nextPreviousPows.add(numberPower)
    collectPows(number + 1, nextRemainingSum, power, nextPreviousPows, collectedPows)
    collectPows(number + 1, remainingSum, power, previousPows, collectedPows)
}

fun Int.pow(power: Int): Int {
    return this.toDouble().pow(power).toInt()
}

fun Double.pow(power: Double): Double {
    if(power == 0.0) {
        return 1.0
    }
    return this * this.pow(power - 1)
}
