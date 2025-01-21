package com.random.problems.adventOfCode.twentyFour.day12

import com.random.problems.adventOfCode.twentyFour.util.Direction
import com.random.problems.adventOfCode.twentyFour.util.Grid
import com.random.problems.adventOfCode.twentyFour.util.Position
import com.random.util.getResourceAsText

const val FILE_PATH = "/adventOfCode/2024/day12/TestCase1"

fun measureTime (run: (Grid) -> Unit, grid: Grid) {
    val timeTaken = kotlin.time.measureTime {
        run(grid)
    }
    println("Time taken: $timeTaken")
}
fun main() {
    val grid = Grid.of(getResourceAsText(FILE_PATH)!!.lines())
    measureTime(Part1()::calculateTotalPrice, grid)
    measureTime(Part2()::calculateTotalPrice, grid)
}

class Part1: Part("Part1") {
    override fun Pair<Char, List<Position>>.getOuterPrice(grid: Grid) = perimeterPrice(grid)

    private fun Pair<Char, List<Position>>.perimeterPrice(grid: Grid) =
            this.second.sumOf { (it to this.first).differentNeighbors(grid) }

    private fun Pair<Position, Char>.differentNeighbors(grid: Grid) = Direction.entries.count { isDifferentNeighbor(grid, it) }

    private fun Pair<Position, Char>.isDifferentNeighbor(grid: Grid, it: Direction): Boolean {
        val nextPosition = this.first.move(it)
        return !grid.checkIfInBounds(nextPosition) || grid[nextPosition] != this.second
    }

}

class Part2: Part("Part2") {
    override fun Pair<Char, List<Position>>.getOuterPrice(grid: Grid): Int = getNumberOfSides(grid)

    private fun Pair<Char, List<Position>>.getNumberOfSides(grid: Grid) = this.second.sumOf{ it.countCorners(grid) }

    private fun Position.countCorners(grid: Grid): Int {
        return listOf(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT, Direction.UP)
                .zipWithNext()
                .map{ (direction1, direction2) ->
                    listOf(
                            grid[this],
                            grid[this + direction1],
                            grid[this + direction2],
                            grid[this + direction1 + direction2]
                    )
                }
                .count{ (current, firstNeighbor, secondNeighbor, corner) ->
                    (current != firstNeighbor && current != secondNeighbor)
                            || (current == firstNeighbor && current == secondNeighbor && current != corner)
                }
    }

}

abstract class Part(private val name: String) {
    fun calculateTotalPrice(grid: Grid) {
        val totalPrice = getTotalPrice(exploreAllRegions(grid), grid)
        println("${this.name} - The total price: $totalPrice")
    }

    private fun getTotalPrice(regions: Map<Pair<Int, Char>, List<Position>>, grid: Grid)
            = regions.entries.sumOf { it.leaveIndex().getFencePrice(grid) }

    private fun Map.Entry<Pair<Int, Char>, List<Position>>.leaveIndex(): Pair<Char, List<Position>>
            = this.toPair().first.second to this.toPair().second

    private fun Pair<Char, List<Position>>.getFencePrice(grid: Grid) = this.getInnerPrice() * this.getOuterPrice(grid)

    private fun Pair<Char, List<Position>>.getInnerPrice(): Int = this.second.size

    abstract fun Pair<Char, List<Position>>.getOuterPrice(grid: Grid): Int

    private fun exploreAllRegions(grid: Grid): Map<Pair<Int, Char>, List<Position>> {
        val regions = mutableMapOf<Pair<Int, Char>, List<Position>>()
        grid.all().forEach { it.toPair().exploreNewRegion(grid, regions) }
        return regions
    }

    private fun Pair<Position, Char>.exploreNewRegion(grid: Grid, exploredRegions: MutableMap<Pair<Int, Char>, List<Position>>) {
        if(this.first.isIn(exploredRegions)) {
            return
        }

        val regionIndex = exploredRegions.keys.filter { it.second == this.second }.maxByOrNull { it.first }?.first ?: 0
        val fieldsInRegion = mutableListOf(this.first)
        exploredRegions[regionIndex+1 to this.second] = fieldsInRegion
        Direction.entries.forEach { this.exploreTowards(it, fieldsInRegion, grid, exploredRegions)}
    }

    private fun Pair<Position, Char>.exploreTowards(direction: Direction, fieldsInRegion: MutableList<Position>, grid: Grid,
                                                    exploredRegions: MutableMap<Pair<Int, Char>, List<Position>>) {
        val nextPosition = this.first.move(direction)
        if(!nextPosition.isIn(exploredRegions) && grid.checkIfInBounds(nextPosition) && grid.charAt(nextPosition) == this.second) {
            fieldsInRegion.add(nextPosition)
            Direction.entries.forEach { (nextPosition to this.second).exploreTowards(it, fieldsInRegion, grid, exploredRegions)}
        }
    }
}
