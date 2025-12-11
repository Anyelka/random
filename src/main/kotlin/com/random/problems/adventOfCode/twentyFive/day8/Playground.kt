package com.random.problems.adventOfCode.twentyFive.day8

import com.random.util.getResourceAsText
import com.random.util.pow
import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.time.measureTime

fun main() {
    val testCases = mapOf(
        0 to Pair("/adventOfCode/2025/day8/TestCase0",10),
        1 to Pair("/adventOfCode/2025/day8/TestCase1",1000)
    )

    testCases[1]!!.let { testCase ->
        val lines = getResourceAsText(testCase.first)!!.lines()

        val timeTaken = measureTime {
            println("AOC day 8 - Part 1 solution: ${Part1(testCase.second).solve(parse(lines.filter { it.isNotEmpty() }))}")
        }
        println("       -> Time taken: $timeTaken")
    }
}

private fun parse(lines: List<String>): List<Point> = lines.map { Point.of(it) }

class Point(val x: Int, val y: Int, val z: Int) {
    companion object {
        fun of(line: String): Point {
            return line.split(",")
                .map { it.toInt() }
                .let { Point(it[0], it[1], it[2]) }
        }
    }

}

class Part1(val repeat: Int) {
    // wrong results:
    //      72072: too low
    ///     33696: too low
    //      352584: good solution

    fun solve(points: List<Point>): Long {
        val minDistances = PriorityQueue<Pair<Long, Pair<Int, Int>>>(Comparator.comparing { it.first })

        for(i in points.indices) {
            for(j in i..points.lastIndex) {
                if(i == j) continue
                val distance = distance(points[i], points[j])
                minDistances.offer(distance to (i to j))
            }
        }

        val circuits = Circuits(points.size)

        repeat(repeat) {
            minDistances.poll().second.let { circuits.union(it.first, it.second) }
        }

        val circuitSizes = Array(points.size) { 0L }
        points.indices.forEach { circuitSizes[circuits.find(it)]++ }

        return circuitSizes
            .asSequence()
            .filter { it != 0L }
            .sortedDescending()
            .take(3)
            .fold(1L) { acc, x -> acc * x }
    }


    fun distance(p1: Point, p2: Point): Long {
        val dx = (p1.x - p2.x).toLong()
        val dy = (p1.y - p2.y).toLong()
        val dz = (p1.z - p2.z).toLong()
        return dx*dx + dy*dy + dz*dz
    }

}

class Circuits(n: Int) {
    val parents = Array(n) { it }

    fun find(x: Int): Int {
        if(parents[x] == x) return x
        return find(parents[x])
    }

    fun union(x: Int, y: Int): Boolean {
        val parent1 = find(x)
        val parent2 = find(y)
        if(parent1 == parent2) return false
        if(parent1 > parent2) parents[parent1] = parent2
        if(parent1 < parent2) parents[parent2] = parent1
        return true
    }
}


/*
object Part2 {
    // TODO: CHECK WITH LONG!!!
    fun solve(points: List<Point>): Int {
        return 0
    }
}
*/
