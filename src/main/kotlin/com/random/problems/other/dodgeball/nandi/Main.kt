fun main() {
    val games = readStates("input.txt")
    val results = games.map(GameState::play)
    writeResults("output.txt", results)
}
