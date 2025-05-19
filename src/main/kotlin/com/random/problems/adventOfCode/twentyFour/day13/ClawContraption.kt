package com.random.problems.adventOfCode.twentyFour.day13

import com.random.problems.adventOfCode.twentyFour.util.PositionLong
import com.random.util.getResourceAsText
import java.util.LinkedList
import kotlin.time.measureTime
import java.math.BigInteger

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

class Part1 : Part("Part1") {
    override fun minCost(game: Game) = minCostMemo(game) ?: BigInteger.ZERO

    // 1. Solution: memoization with recursion
    private fun minCostMemo(game: Game): BigInteger? = minCostMemo(game, mutableMapOf())

    private fun minCostMemo(game: Game, costMemo: MutableMap<PositionLong, BigInteger?>): BigInteger? =
        minCostToReachTargetWithMovesMemo(PositionLong.NULL, game.prize, game.availableMoves, costMemo)

    private fun minCostToReachTargetWithMovesMemo(
        start: PositionLong,
        target: PositionLong,
        moves: List<Move>,
        memo: MutableMap<PositionLong, BigInteger?>
    ): BigInteger? {
        if (memo.containsKey(start)) {
            return memo[start]
        }
        if (start == target) {
            return BigInteger.ZERO
        }
        if (start.isPast(target)) {
            return null
        }
        return moves.mapNotNull { move ->
            minCostToReachTargetWithMovesMemo(start + move, target, moves, memo)
                ?.let { move.cost.toBigInteger() + it }
        }.minOrNull().also { memo[start] = it }
    }

    // 2. Solution: tabulation with queueing the next elements
    private fun minCostTab(game: Game): Int? = minCostTab(game, mutableMapOf())

    private fun minCostTab(game: Game, costMemo: MutableMap<PositionLong, Int?>): Int? =
        minCostToReachTargetWithMovesTab(PositionLong.NULL, game.prize, game.availableMoves, costMemo)

    private fun minCostToReachTargetWithMovesTab(
        start: PositionLong,
        target: PositionLong,
        moves: List<Move>,
        memo: MutableMap<PositionLong, Int?>
    ): Int? {
        memo[start] = 0

        val queue = LinkedList<PositionLong>().apply { add(start) }

        while (!queue.isEmpty()) {
            val position = queue.poll()

            val moveCosts = moves.map { memo[position - it]?.plus(it.cost) }

            memo[position] = (moveCosts + memo[position]).filterNotNull().minOrNull()

            moves.map { position + it }
                .filter { nextPosition -> position.x < target.x && position.y < target.y && !queue.contains(nextPosition) }
                .forEach { queue.add(it) }
        }

        return memo[target]
    }

}

class Part2 : Part("Part2") {
    private val positionDifference = 10000000000000
    override fun run(games: List<Game>) {
        super.run(extendGames(games))
    }

    private fun extendGames(games: List<Game>): List<Game> =
        games.map { Game(it.availableMoves, PositionLong.of(positionDifference) + it.prize) }

    override fun minCost(game: Game): BigInteger {
        // We assume we only have two moves - we can write two equations with 2 variables to reach the
        //      prize from (0;0) starting coordinates
        //  We push the A button n times and the B button m times
        //      n * XA + m * XB = XT
        //      n * YA + m * YB = YT
        // after solving the equation:
        //      m = (XA * YT - YA * XT) / ( XA * YB - YA * XB)
        //      n = (XT - m * XB) / XA

        val (move1, move2) = game.availableMoves

        val (xt, yt) = game.prize
        val (xa, ya) = move1.position
        val (xb, yb) = move2.position
        val ca = move1.cost
        val cb = move2.cost


        val m = (xa * yt - ya * xt) / (xa * yb - ya * xb)
        val n = (xt - m * xb) / xa

        if((xa * n + xb * m to ya * n + yb * m) != xt to yt) {
            return BigInteger.ZERO
        }
        return ca.toBigInteger() * n.toBigInteger() + cb.toBigInteger() * m.toBigInteger()
    }

}

abstract class Part(val name: String) {
    open fun run(games: List<Game>) {
        val timeTaken = measureTime {
            val totalCost1 = totalCost(games)
            println("$name - The total cost: $totalCost1")
        }
        println("Time taken: $timeTaken")
    }

    open fun totalCost(games: List<Game>): BigInteger {
        val sum = games.map { minCost(it) }.sumOf { it }
        return sum;
    }

    abstract fun minCost(game: Game): BigInteger
}


operator fun PositionLong.minus(move: Move) = this - move.position
operator fun PositionLong.plus(move: Move) = this + move.position

data class Game(val availableMoves: List<Move>, val prize: PositionLong)
data class Move(val position: PositionLong, val cost: Int) {
    operator fun component3() = cost
}