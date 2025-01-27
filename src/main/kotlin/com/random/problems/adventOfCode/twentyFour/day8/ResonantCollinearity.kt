package com.random.problems.adventOfCode.twentyFour.day8

import com.random.problems.adventOfCode.twentyFour.util.Grid
import com.random.problems.adventOfCode.twentyFour.util.writeToFile
import com.random.util.getResourceAsText
import java.nio.file.Files
import java.nio.file.Paths

const val FILE_PATH = "/adventOfCode/2024/day8/TestCase1"

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val map = Map.of(lines)

    Part1().generateMapWithAntinodes("Part1_OutputFile", map)
    Part2().generateMapWithAntinodes("Part2_OutputFile", map)

    Part1().run(map)
    Part2().run(map)
}

private const val ANTINODE = '#'

abstract class Part {
    abstract fun run(map: Map)
    fun calculateSignalImpact(occurrenceMap: kotlin.collections.Map<Char, List<Position>>, map: Map) =
            occurrenceMap.getAllAntinodes(map).count()

    fun kotlin.collections.Map<Char, List<Position>>.getAllAntinodes(map: Map): List<Position> =
            this.entries.flatMap { it.toPair().getAllAntinodes(map) }.distinct()

    private fun Pair<Char, List<Position>>.getAllAntinodes(map: Map): List<Position> =
            second.flatMap { it.getAntinodesWith(second, map) }

    private fun Position.getAntinodesWith(otherSignals: List<Position>, map: Map) =
            otherSignals.flatMap { this.getAntinodesWith(it, map) }

    abstract fun Position.getAntinodesWith(other: Position, map: Map): List<Position>

    fun generateMapWithAntinodes(filename: String, map: Map) {
        writeToFile(filename, Grid.of(createMapWithAntinodes(getOccurrenceMap(map), map)))
    }

    private fun createMapWithAntinodes(occurrenceMap: kotlin.collections.Map<Char, List<Position>>, map: Map): Map {
        val newMap = Map.copy(map)
        occurrenceMap.getAllAntinodes(map).forEach {
            if(newMap.fields[it.y][it.x] == '.') newMap.fields[it.y][it.x] = ANTINODE
        }
        return newMap
    }

}

private fun Grid.Companion.of(map: Map): Grid = Grid(map.fields)

class Part1: Part() {
    override fun run(map: Map) {
        val occurrenceMap: kotlin.collections.Map<Char, List<Position>> = getOccurrenceMap(map)
        println("The impact of the signal: ${calculateSignalImpact(occurrenceMap, map)}")
    }

    override fun Position.getAntinodesWith(other: Position, map: Map): List<Position> {
        val antinode = this.antinodeOf(other)
        return if(other != this && antinode.isInMap(map)) listOf(antinode) else emptyList()
    }

}

class Part2: Part() {
    override fun run(map: Map) {
        val occurrenceMap: kotlin.collections.Map<Char, List<Position>> = getOccurrenceMap(map)
        println("The total impact of the signal: ${calculateSignalImpact(occurrenceMap, map)}")
    }

    override fun Position.getAntinodesWith(other: Position, map: Map): List<Position> {
        if(other == this) {
            return emptyList()
        }
        var current = this
        var next = other
        val antinodes = mutableListOf(current, next)
        var antinode = current.antinodeOf(next)
        while(antinode.isInMap(map)) {
            antinodes.add(antinode)
            current = next
            next = antinode
            antinode = current.antinodeOf(next)
        }
        return antinodes
    }
}

private fun getOccurrenceMap(map: Map): kotlin.collections.Map<Char, List<Position>> {
    return map.fields.withIndex().flatMap { (i, line) ->
        line.toCharArray().withIndex().map { (j, char) -> Position(j, i) to char }
    }.filter { it.second != '.' }.groupBy({ it.second }, { it.first })
}


data class Map(val fields: Array<Array<Char>>) {
    companion object {
        fun of(lines: List<String>) = Map(lines.map { it.toCharArray().toTypedArray() }.toTypedArray())
        fun copy(map: Map) = Map(map.fields.map { it.copyOf() }.toTypedArray())
    }

    fun checkIfInBounds(position: Position)
            = position.y >= 0 && position.y < fields.size && position.x >= 0 && position.x < fields[0].size

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Map

        return fields.contentDeepEquals(other.fields)
    }

    override fun hashCode(): Int {
        return fields.contentDeepHashCode()
    }

}

data class Position(val x: Int, val y: Int) {
    fun isInMap(map: Map): Boolean {
        return map.checkIfInBounds(this)
    }

    fun antinodeOf(position: Position): Position {
        return Position((position.x + (position.x - this.x)), (position.y +(position.y - this.y)))
    }

}