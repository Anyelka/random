import kotlin.math.sign
import kotlin.math.sqrt

data class GameResult(
    val throws: Long,
    val lastPlayer: Long
)

class GameStep(
    val player: Player,
    val direction: Direction,
    val previous: List<GameStep> = listOf()
)

data class GameState(
    val players: List<Player>,
    val startingDirection: Direction,
    val startingPlayer: Player
) {
    fun play(): GameResult {
        val firstStep = GameStep(
            player = startingPlayer,
            direction = startingDirection
        )
        val game = generateSequence(firstStep, ::next)
        val lastStep = game.last()
        return GameResult(
            throws = lastStep.previous.size.toLong(),
            lastPlayer = lastStep.player.ordinal
        )
    }

    fun next(previous: GameStep): GameStep? {
        return (1..8)
            .map { previous.direction.nthClockwise(it) }
            .firstNotNullOfOrNull { nextInDirection(previous, it) }
    }

    private fun nextInDirection(previous: GameStep, direction: Direction): GameStep? = players
        .asSequence()
        .filter { (it.position - previous.player.position).pointsIn(direction) }
        .filter { !previous.previous.map(GameStep::player).contains(it) }
        .sortedBy { (it.position - previous.player.position).length() }
        .map {
            GameStep(
                player = it,
                direction = direction.opposite(),
                previous = previous.previous.plus(previous)
            )
        }
        .firstOrNull()
}

data class Player(
    val position: Vector,
    val ordinal: Long
)

data class Vector(
    val x: Long,
    val y: Long
) {
    operator fun minus(that: Vector): Vector = Vector(x = x - that.x, y = y - that.y)

    fun length(): Double = sqrt(((x * x) + (y * y)).toDouble())

    fun pointsIn(direction: Direction): Boolean =
        parallel(direction.identity) && x.sign == direction.identity.x.sign && y.sign == direction.identity.y.sign

    private fun parallel(that: Vector): Boolean = x * that.y == y * that.x
}

infix fun Long.to(that: Long): Vector = Vector(this, that)

enum class Direction(val identity: Vector) {
    N(0L to 1L),
    NE(1L to 1L),
    E(1L to 0L),
    SE(1L to -1L),
    S(0L to -1L),
    SW(-1L to -1L),
    W(-1L to 0L),
    NW(-1L to 1L);

    fun nthClockwise(steps: Int) = entries[(this.ordinal + steps) % entries.size]

    fun opposite(): Direction = nthClockwise(entries.size / 2)
}
