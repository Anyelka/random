package com.random.problems.adventOfCode.twentyFour.day11

import com.random.util.getResourceAsText
import java.math.BigInteger
import kotlin.time.measureTime

const val FILE_PATH = "/adventOfCode/2024/day11/TestCase1"

fun main() {
    val stones = getResourceAsText(FILE_PATH)!!.split(" ").map { it.toBigInteger() }.toMutableList()
    val timeTaken1 = measureTime {
        val stonesAfterBlink = Part1().getStonesAfter(stones, 25)
        println("Number of Stones after 25 blinks: ${stonesAfterBlink.size}")
    }
    println("Time taken: $timeTaken1")

    val timeTaken2 = measureTime {
        val stoneCountAfterBlink = Part2().stoneCountAfter(stones, 75)
        println("Number of Stones after 25 blinks: ${stoneCountAfterBlink}")
    }
    println("Time taken: $timeTaken2")
}

abstract class Part {
    fun getNextStones(stone: BigInteger): MutableList<BigInteger> {
        return getNextStones(mutableListOf(stone))
    }

    fun getNextStones(stones: MutableList<BigInteger>): MutableList<BigInteger> {
        val nextStones = stones.toMutableList()
        var nextStonePivot = 0
        for ((i, stone) in stones.withIndex()) {
            val stoneAsString = stone.toString()
            if(stone == BigInteger.ZERO) {
                nextStones[i + nextStonePivot] = BigInteger.valueOf(1)
            } else if(stoneAsString.length %  2 == 0) {
                val firstNewStone = stoneAsString.substring(0..<stoneAsString.length/2).toBigInteger()
                val secondNewStone = stoneAsString.substring(stoneAsString.length/2).toBigInteger()
                nextStones[i + nextStonePivot] = firstNewStone
                nextStones.add(i + 1 + nextStonePivot, secondNewStone)
                nextStonePivot++
            } else {
                nextStones[i + nextStonePivot] = stone * BigInteger.valueOf(2024)
            }
        }
        return nextStones
    }
}
class Part1: Part() {
    fun getStonesAfter(stones: MutableList<BigInteger>, blinkCount: Int): MutableList<BigInteger> {
        if(blinkCount==0) {
            return stones
        }
        val nextStones = getNextStones(stones)
        return getStonesAfter(nextStones, blinkCount-1)
    }
}

class Part2: Part() {
    private val numberOfChildrenAfterNBlinks = hashMapOf<Pair<BigInteger, Int>, BigInteger>()
    fun stoneCountAfter(stones: MutableList<BigInteger>, blinkCount: Int) =
            stones.sumOf { it.stoneCountAfter(blinkCount) }

    private fun BigInteger.stoneCountAfter(blinkCount: Int): BigInteger {
        if (blinkCount == 0) return BigInteger.ONE
        return numberOfChildrenAfterNBlinks[this to blinkCount] ?: getStoneCountForChildrenAfter(blinkCount)
    }

    private fun BigInteger.getStoneCountForChildrenAfter(blinkCount: Int): BigInteger {
        val stoneCountAfterBlinks = getNextStones(this).sumOf { it.stoneCountAfter(blinkCount - 1) }
        numberOfChildrenAfterNBlinks[this to blinkCount] = stoneCountAfterBlinks
        return stoneCountAfterBlinks
    }
}
