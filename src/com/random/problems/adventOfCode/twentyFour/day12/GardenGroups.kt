package com.random.problems.adventOfCode.twentyFour.day12

import com.random.util.getResourceAsText

const val FILE_PATH = "/adventOfCode/2024/day12/TestCase1"

fun measureTime (run: (Map) -> Unit, map: Map) {
    val timeTaken = kotlin.time.measureTime {
        run(map)
    }
    println("Time taken: $timeTaken")
}
fun main() {
    val map = Map.of(getResourceAsText(FILE_PATH)!!.lines())
    measureTime(Part1()::calculateTotalPrice, map)
    measureTime(Part2()::calculateTotalPrice, map)
}

class Part1: Part("Part1") {
    override fun Pair<Char, List<Position>>.getOuterPrice(map: Map) = perimeterPrice(map)

    private fun Pair<Char, List<Position>>.perimeterPrice(map: Map) =
            this.second.sumOf { (it to this.first).differentNeighbors(map) }

    private fun Pair<Position, Char>.differentNeighbors(map: Map) = Direction.entries.count { isDifferentNeighbor(map, it) }

    private fun Pair<Position, Char>.isDifferentNeighbor(map: Map, it: Direction): Boolean {
        val nextPosition = this.first.move(it)
        return !nextPosition.isIn(map) || map[nextPosition] != this.second
    }

}

class Part2: Part("Part2") {
    override fun Pair<Char, List<Position>>.getOuterPrice(map: Map): Int = getNumberOfSides(map)

    private fun Pair<Char, List<Position>>.getNumberOfSides(map: Map) = this.second.sumOf{ it.countCorners(map) }

    private fun Position.countCorners(map: Map): Int {
        return listOf(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT, Direction.UP)
                .zipWithNext()
                .map{ (direction1, direction2) ->
                    listOf(
                            map[this],
                            map[this + direction1],
                            map[this + direction2],
                            map[this + direction1 + direction2]
                    )
                }
                .count{ (current, firstNeighbor, secondNeighbor, corner) ->
                    (current != firstNeighbor && current != secondNeighbor)
                            || (current == firstNeighbor && current == secondNeighbor && current != corner)
                }
    }

}

abstract class Part(name: String) {
    fun calculateTotalPrice(map: Map) {
        val totalPrice = getTotalPrice(exploreAllRegions(map), map)
        println("$this.name - The total price: $totalPrice")
    }

    fun getTotalPrice(regions: kotlin.collections.Map<Pair<Int, Char>, List<Position>>, map: Map)
            = regions.entries.sumOf { it.leaveIndex().getFencePrice(map) }

    fun kotlin.collections.Map.Entry<Pair<Int, Char>, List<Position>>.leaveIndex(): Pair<Char, List<Position>>
            = this.toPair().first.second to this.toPair().second

    fun Pair<Char, List<Position>>.getFencePrice(map: Map) = this.getInnerPrice() * this.getOuterPrice(map)

    fun Pair<Char, List<Position>>.getInnerPrice(): Int = this.second.size

    abstract fun Pair<Char, List<Position>>.getOuterPrice(map: Map): Int

    fun exploreAllRegions(map: Map): kotlin.collections.Map<Pair<Int, Char>, List<Position>> {
        val regions = mutableMapOf<Pair<Int, Char>, List<Position>>()
        map.all().forEach { it.toPair().exploreNewRegion(map, regions) }
        return regions
    }

    private fun Pair<Position, Char>.exploreNewRegion(map: Map, exploredRegions: MutableMap<Pair<Int, Char>, List<Position>>) {
        if(this.first.isIn(exploredRegions)) {
            return
        }

        val regionIndex = exploredRegions.keys.filter { it.second == this.second }.maxByOrNull { it.first }?.first ?: 0
        val fieldsInRegion = mutableListOf(this.first)
        exploredRegions[regionIndex+1 to this.second] = fieldsInRegion
        Direction.entries.forEach { this.exploreTowards(it, fieldsInRegion, map, exploredRegions)}
    }

    private fun Pair<Position, Char>.exploreTowards(direction: Direction, fieldsInRegion: MutableList<Position>, map: Map,
                                                    exploredRegions: MutableMap<Pair<Int, Char>, List<Position>>) {
        val nextPosition = this.first.move(direction)
        if(!nextPosition.isIn(exploredRegions) && nextPosition.isIn(map) && map.charAt(nextPosition) == this.second) {
            fieldsInRegion.add(nextPosition)
            Direction.entries.forEach { (nextPosition to this.second).exploreTowards(it, fieldsInRegion, map, exploredRegions)}
        }
    }
}

data class Map(val fields: Array<Array<Char>>) {
    companion object {
        fun of(lines: List<String>) = Map(lines.map { it.toCharArray().toTypedArray() }.toTypedArray())
    }

    fun all(): kotlin.collections.Map<Position, Char> {
        return fields.withIndex().map { (i, line) ->
            line.withIndex().map { (j, char) -> Position(j, i) to char }
        }.flatten().toMap()
    }
    fun checkIfInBounds(position: Position)
            = position.y >= 0 && position.y < fields.size && position.x >= 0 && position.x < fields[0].size

    operator fun get(position: Position) = charAt(position)
    fun charAt(position: Position)
            = if(position.y in fields.indices && position.x in fields[position.y].indices)
                fields[position.y][position.x]
            else null

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
    operator fun plus(direction: Direction) = this.move(direction)

    companion object {
        val OUT_OF_MAP = Position(-1, -1)
    }

    fun move(direction: Direction): Position = Position(x + direction.x, y + direction.y)

    fun isIn(map: Map) = map.checkIfInBounds(this)

     fun isIn(regions: MutableMap<Pair<Int, Char>, List<Position>>) = regions.values.flatten().contains(this)

}

enum class Direction(val x: Int, val y: Int) {
    UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0), NULL(0, 0);

    fun reverse() = this.next().next()
    fun previous() = when(this) {
            UP -> LEFT
            RIGHT -> UP
            DOWN -> RIGHT
            LEFT -> DOWN
            else -> NULL
    }

    fun next() = when(this) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
            else -> NULL
    }
}