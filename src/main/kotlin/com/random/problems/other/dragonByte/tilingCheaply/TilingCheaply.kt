package com.random.problems.other.dragonByte.tilingCheaply

import java.nio.file.Files
import java.nio.file.Paths

const val directory = "/other/dragonByte/tilingCheaply"

fun getResourceAsText(path: String): String? =
    object {}.javaClass.getResource(path)?.readText()

fun main() {
    solve("T1")
}

private fun solve(filename: String) {
    val result = getInputTestCases(filename).map { solve(it) }.map {
        if(it == null) "NO"
        else "YES\n" + it.joinToString("\n")}
    Files.writeString(Paths.get("${filename}.out"), result.joinToString(separator = "\n"))
}

private fun getInputTestCases(filename: String) =
    getResourceAsText("${directory}/$filename.in")!!
        .lines().drop(1).filter { it.isNotEmpty() }.map { it.toInt() }

fun solve(n: Int): Array<CharArray>? {
    val board = Array(n) { CharArray(n) { '.' } }

    val start = 0 to 0

    return null
}

enum class Tiles(vararg tiles: Pair<Int, Int>) {
    O(0 to 0, 0 to 1, 1 to 0, 1 to 1),
    S(0 to 0, 0 to 1, -1 to 1, -1 to 2),
    SR(0 to 0, 1 to 0, 1 to 1, 2 to 1),
    Z(0 to 0, 0 to 1, 1 to 1, 1 to 2),
    ZR(0 to 0, 1 to 0, 0 to 1, -1 to 1),
    L(0 to 0, 1 to 0, 1 to 1),
    L2(0 to 0, 1 to 0, 0 to 1),
    L3(0 to 0, 0 to 1, 1 to 1),
    L4(0 to 1, 1 to 1, 1 to 0)
}