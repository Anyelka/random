package com.random.problems.adventOfCode.twentyFour.day13

import com.random.problems.adventOfCode.twentyFour.util.Position
import com.random.util.getResourceAsText

const val FILE_PATH = "/adventOfCode/2024/day13/TestCase1"

const val MOVE_A_COST = 3
const val MOVE_B_COST = 1
fun main() {
    val lines = getResourceAsText(FILE_PATH)!!
    val games = InputConverter().getGames(lines)
    val totalCost = Part1().totalCost(games)
    println("The total cost: $totalCost")
}

class InputConverter () {
    private val moveRegex = """X\+(\d+), Y\+(\d+)""".toRegex()
    private val prizeRegex = """X=(\d+), Y=(\d+)""".toRegex()

    fun getGames(lines: String): List<Game> = lines.split("\n\n").map { getGame(it) }

    private fun getGame(game: String): Game = game.split("\n")
        .let { (move1, move2, prize) -> Game(listOf(getMove(move1, MOVE_A_COST), getMove(move2, MOVE_B_COST)), getPrize(prize)) }

    private fun getMove(line: String, cost: Int): Move =
        moveRegex.find(line)!!.destructured.let { (x, y) -> Move(Position(x.toInt(), y.toInt()), cost) }

    private fun getPrize(line: String): Position =
        prizeRegex.find(line)!!.destructured.let { (x, y) -> Position(x.toInt(), y.toInt()) }

}

class Part1 {
    fun totalCost(games: List<Game>): Int = games.map { minCost(it) }.sumOf { it }

    private fun minCost(game: Game): Int = minCost(game, mutableMapOf()) ?: 0

    private fun minCost(game: Game, costMemo: MutableMap<Position, Int?>) =
        minCostToReachTargetWithMoves(Position.NULL, game.prize, game.availableMoves, costMemo)

    private fun minCostToReachTargetWithMoves(
        start: Position,
        target: Position,
        moves: List<Move>,
        memo: MutableMap<Position, Int?>
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
            minCostToReachTargetWithMoves(start + move, target, moves, memo)?.let{ move.cost + it}
        }.minOrNull().also { memo[start] = it }
    }

}

operator fun Position.plus(move: Move) = this + move.position

data class Game(val availableMoves: List<Move>, val prize: Position)
data class Move(val position: Position, val cost: Int)