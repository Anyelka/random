import java.io.File

fun readStates(path: String): List<GameState> {
    val lines = object {}.javaClass.getResource(path)!!.readText().lines()
    val gameLines = lines.drop(1)
    val endIndices = gameLines
        .withIndex()
        .filter { Direction.entries.map(Direction::toString).contains(it.value) }
        .map { it.index + 2 }
    val gamesLines = endIndices.foldIndexed(mutableListOf<List<String>>()) { listIndex, acc, endIndex ->
        val startIndex = if (listIndex > 0) endIndices[listIndex - 1] else 0
        acc.add(gameLines.drop(startIndex).take(endIndex - startIndex))
        acc
    }
    return gamesLines.map(::parseGameState)
}

private fun parseGameState(lines: List<String>): GameState {
    val startingDirection = lines.dropLast(1).last().let(Direction::valueOf)
    val startingPlayerIndex = lines.last().toInt()
    val players = lines
        .drop(1)
        .dropLast(2)
        .map { it.split(' ').map(String::toLong) }
        .withIndex()
        .map {
            Player(
                position = it.value[0] to it.value[1],
                ordinal = it.index + 1L
            )
        }
    return GameState(
        players = players,
        startingDirection = startingDirection,
        startingPlayer = players[startingPlayerIndex - 1]
    )
}

fun writeResults(path: String, results: Collection<GameResult>) {
    File(path).delete()
    File(path).printWriter().use { out ->
        results.forEach { result -> out.println("${result.throws} ${result.lastPlayer}") }
    }
}