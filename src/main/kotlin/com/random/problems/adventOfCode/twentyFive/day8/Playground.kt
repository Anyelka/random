package com.random.problems.adventOfCode.twentyFive.day8

import com.random.util.getResourceAsText
import java.util.PriorityQueue
import kotlin.time.measureTime

fun main() {
    val testCases = listOf(
        Pair("/adventOfCode/2025/day8/TestCase0",10),
        Pair("/adventOfCode/2025/day8/TestCase1",1000)
    )

    testCases.forEach { testCase ->
        val lines = getResourceAsText(testCase.first)!!.lines()

        val timeTaken = measureTime {
            println("AOC day 8 - Part 1 solution: ${Part1(testCase.second).solve(parse(lines.filter { it.isNotEmpty() }))}")
            println("AOC day 8 - Part 2 solution: ${Part2.solve(parse(lines.filter { it.isNotEmpty() }))}")
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

    fun distanceTo(p2: Point): Long {
        val dx = (x - p2.x).toLong()
        val dy = (y - p2.y).toLong()
        val dz = (z - p2.z).toLong()
        return dx*dx + dy*dy + dz*dz
    }
}

class Circuits(public val size: Int) {
    val parents = Array(size) { it }

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

fun  PriorityQueue<Pair<Long, Pair<Int, Int>>>.fill(points: List<Point>) {
    for(i in points.indices) {
        for(j in i..points.lastIndex) {
            if(i == j) continue
            this.offer(points[i].distanceTo(points[j]) to (i to j))
        }
    }
}

class Part1(val repeat: Int) {
    fun solve(points: List<Point>): Long {
        val minDistances = PriorityQueue<Pair<Long, Pair<Int, Int>>>(Comparator.comparing { it.first })

        minDistances.fill(points)

        val circuits = Circuits(points.size)

        repeat(repeat) {
            minDistances.poll().second.let { circuits.union(it.first, it.second)}
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

}

object Part2 {
    fun solve(points: List<Point>): Long {
        val minDistances = PriorityQueue<Pair<Long, Pair<Int, Int>>>(Comparator.comparing { it.first })

        minDistances.fill(points)
        val circuits = Circuits(points.size)

        var firstIndex = -1
        var secondIndex = -1
        var circuitCount = circuits.size

        while(circuitCount > 1) {
            val pair = minDistances.poll().second
            firstIndex = pair.first
            secondIndex = pair.second
            val merged = circuits.union(firstIndex, secondIndex)
            if(merged) circuitCount--

        }
        return points[firstIndex].x.toLong() * points[secondIndex].x
    }
}
