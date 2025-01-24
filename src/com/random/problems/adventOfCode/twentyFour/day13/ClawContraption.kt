package com.random.problems.adventOfCode.twentyFour.day13

import com.random.problems.adventOfCode.twentyFour.util.PositionLong
import com.random.util.getResourceAsText
import java.util.LinkedList
import kotlin.time.measureTime

const val FILE_PATH = "/adventOfCode/2024/day13/TestCase1"

const val MOVE_A_COST = 3
const val MOVE_B_COST = 1

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!
    val games = InputConverter().getGames(lines)

    Part1().run(games)
    Part2().run(games)
}

class InputConverter() {
    private val moveRegex = """X\+(\d+), Y\+(\d+)""".toRegex()
    private val prizeRegex = """X=(\d+), Y=(\d+)""".toRegex()

    fun getGames(lines: String): List<Game> = lines.split("\n\n").map { getGame(it) }

    private fun getGame(game: String): Game = game.split("\n")
        .let { (move1, move2, prize) ->
            Game(
                listOf(getMove(move1, MOVE_A_COST), getMove(move2, MOVE_B_COST)),
                getPrize(prize)
            )
        }

    private fun getMove(line: String, cost: Int): Move =
        moveRegex.find(line)!!.destructured.let { (x, y) -> Move(PositionLong(x.toLong(), y.toLong()), cost) }

    private fun getPrize(line: String): PositionLong =
        prizeRegex.find(line)!!.destructured.let { (x, y) -> PositionLong(x.toLong(), y.toLong()) }

}

class Part1: Part("Part1") {
    override fun totalCost(games: List<Game>): Int = games.map { minCost(it) }.sumOf { it }

    private fun minCost(game: Game): Int = minCost(game, mutableMapOf()) ?: 0

    private fun minCost(game: Game, costMemo: MutableMap<PositionLong, Int?>): Int? {
        val result = minCostToReachTargetWithMoves(PositionLong.NULL, game.prize, game.availableMoves, costMemo)
        return result
    }

    private fun minCostToReachTargetWithMoves(
        start: PositionLong,
        target: PositionLong,
        moves: List<Move>,
        memo: MutableMap<PositionLong, Int?>
    ): Int? {
        if (memo.containsKey(start)) {
            return memo[start]
        }
        if (start == target) {
            return 0
        }
        if (start.isPast(target)) {
            return null
        }
        return moves.mapNotNull { move ->
            minCostToReachTargetWithMoves(start + move, target, moves, memo)?.let { move.cost + it }
        }.minOrNull().also { memo[start] = it }
    }

}

class Part2: Part("Part2") {
    /*private val positionDifference = 10000000000000*/
    private val positionDifference = 0L

    override fun run(games: List<Game>) {
        super.run(extendGames(games))
    }

    private fun extendGames(games: List<Game>): List<Game> =
        games.map { Game(it.availableMoves, PositionLong.of(positionDifference) + it.prize) }

    override fun totalCost(games: List<Game>): Long = games.map { minCost(it) }.sumOf { it }

    private fun minCost(game: Game): Long = minCostToReachTargetWithMoves(game.prize, game.availableMoves) ?: 0

    private fun minCostToReachTargetWithMoves(
        target: PositionLong,
        moves: List<Move>,
    ): Long? {
        val startPosition = PositionLong(0, 0)

        val memo = mutableMapOf<PositionLong, Long?>(startPosition to 0L)

        val queue = LinkedList<PositionLong>()

        queue.add(startPosition)

        while(!queue.isEmpty()) {
            val position = queue.poll()

            val move1 = moves[0]
            val move2 = moves[1]

            val cost1From: Long? = memo[position - move1]?.plus(MOVE_A_COST)
            val cost2From: Long? = memo[position - move2]?.plus(MOVE_B_COST)
            memo[position] = listOfNotNull(memo[position], cost1From, cost2From).minOrNull()

            val nextPosition1 = (position + move1)
                .takeIf { position.x < target.x && position.y < target.y && !queue.contains(it) }
                ?.let { queue.add(it) }
            val nextPosition2 = (position + move2)
                .takeIf { position.x < target.x && position.y < target.y && !queue.contains(it) }
                ?.let { queue.add(it) }
        }

        return memo[target]
    }

    fun PositionLong.higherCoordinate() = if(this.x > this.y) this.x else this.y

}

abstract class Part(val name: String) {
    open fun run(games: List<Game>) {
        val timeTaken = measureTime {
            val totalCost1 = totalCost(games)
            println("$name - The total cost: $totalCost1")
        }
        println("Time taken: $timeTaken")
    }

    abstract fun totalCost(games: List<Game>): Number
}


operator fun PositionLong.minus(move: Move) = this - move.position
operator fun PositionLong.plus(move: Move) = this + move.position

data class Game(val availableMoves: List<Move>, val prize: PositionLong)
data class Move(val position: PositionLong, val cost: Int)