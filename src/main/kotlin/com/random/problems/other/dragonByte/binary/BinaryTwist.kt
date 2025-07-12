package com.random.problems.other.dragonByte.binary

import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.sqrt

const val directory = "/other/dragonByte/binary"

fun getResourceAsText(path: String): String? =
    object {}.javaClass.getResource(path)?.readText()


fun main() {
    solve("B030")
}

private fun solve(filename: String) {
    val cache = mutableMapOf<Pair<Long,Long>, Boolean>()
    val result = getInputTestCases(filename).map { solve(it, cache) }
    Files.writeString(Paths.get("${filename}.out"), result.joinToString(separator = "\n"))
}

private fun getInputTestCases(filename: String) =
    getResourceAsText("$directory/$filename.in")!!
        .lines().drop(1).filter { it.isNotEmpty() }.map { it.toLong() }

private fun solve(n: Long, cache: MutableMap<Pair<Long,Long>, Boolean>): Long {
    if(n == 1L || n == 3L) return 3
    if(n == 2L) return -1
    for(i in 3..sqrt(n.toDouble()).toLong()) {
        if(cache[i to n] == true) return i
        var number = n
        var valid = true
        val numbers = mutableListOf<Long>()
        while(number > 0) {
            if(cache[i to number] == true) break
            numbers.add(number)
            val lastDigit = number % i
            if(lastDigit > 1) {
                valid = false
                break
            }
            number /= i
        }
        if(valid) {
            numbers.forEach { cache[i to it] = true }
            return i
        }
    }
    return n - 1
}