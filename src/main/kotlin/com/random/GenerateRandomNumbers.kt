package com.random

import com.random.algorithms.sorts.testdata.generate.generateRandomIntArray
import kotlin.random.Random

fun main() {
    /*val numbers = (0..1).map { Random.nextInt(12) } +
    (2..3).map { Random.nextInt(13, 24) } +
    (4..5).map { Random.nextInt(25, 37) } +
    (5..6).map { Random.nextInt(38, 50) } +
    (6..7).map { Random.nextInt(51, 63) }
*/
    val numbers = (1..10).map { Random.nextInt(1, 63) }
    numbers.shuffled().forEach{println(it)}
}