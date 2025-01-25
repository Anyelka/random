package com.random.problems.adventOfCode.twentyFour.day4

import com.random.util.getResourceAsText

const val FILE_PATH = "/adventOfCode/2024/day4/TestCase1"

const val WORD = "XMAS"

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val occurrences = Part1.countOccurrences(lines)

    println("XMAS occurs $occurrences times")
}

abstract class Part {
    fun getCharacterMap(lines: List<String>) = lines.map(String::toCharArray).map(CharArray::toList)

    abstract fun countOccurrences(lines: List<String>): Int

}
object Part1: Part() {

    override fun countOccurrences(lines: List<String>): Int {
        val characterMap = getCharacterMap(lines)
        return characterMap.withIndex().sumOf { (i, line) ->
            line.indices.sumOf { j -> WORD.countOccurrencesStartingFrom(i, j, characterMap) }
        }
    }

    private fun String.countOccurrencesStartingFrom(i: Int, j: Int, characterMap: List<List<Char>>): Int {
        if (this[0] == characterMap[i][j]) {
            return Direction.entries.sumOf { countOccurrences(i, j, it, characterMap, this) }
        }
        return 0
    }

    private fun countOccurrences(i: Int, j: Int, direction: Direction, characterMap: List<List<Char>>, word: String): Int {
        if(i < 0 || j < 0 || i >= characterMap.size || j >= characterMap[i].size) {
            return 0
        }
        if (word[0] != characterMap[i][j]) {
            return 0
        }
        if(word.length < 2) {
            return 1
        }
        return countOccurrences(i+direction.y, j+direction.x, direction, characterMap, word.substring(1))
    }
}

// Searching for following shape:
//      M.S
//      .A.
//      M.s
object Part2: Part() {

    override fun countOccurrences(lines: List<String>): Int {
        val characterMap = getCharacterMap(lines)
        return characterMap.withIndex().sumOf { (i, line) ->
            line.indices.sumOf { countOccurrencesStartingFrom(i, it, characterMap) }
        }
    }

    private fun countOccurrencesStartingFrom(i: Int, j: Int, characterMap: List<List<Char>>): Int =
            if(isXMasShape(i, j, characterMap)) 1 else 0

    private fun isXMasShape(i: Int, j: Int, characterMap: List<List<Char>>): Boolean {
        return matchingCenter(characterMap, i, j)
                && inboundCoordinates(i, j, characterMap)
                && matchingCorners(i, j, characterMap)
    }

    private fun matchingCenter(characterMap: List<List<Char>>, i: Int, j: Int) = characterMap[i][j] == 'A'

    private fun inboundCoordinates(i: Int, j: Int, characterMap: List<List<Char>>) =
            (i > 0 && j > 0 && i < characterMap.size - 1 && j < characterMap[i].size - 1)

    private fun matchingCorners(i: Int, j: Int, characterMap: List<List<Char>>) =
            ((characterMap[i - 1][j - 1] == 'M' && characterMap[i + 1][j + 1] == 'S')
                    || (characterMap[i - 1][j - 1] == 'S' && characterMap[i + 1][j + 1] == 'M'))
                    && ((characterMap[i + 1][j - 1] == 'M' && characterMap[i - 1][j + 1] == 'S')
                    || (characterMap[i + 1][j - 1] == 'S' && characterMap[i - 1][j + 1] == 'M'))

}

    // Coordinate system where x increases from left to right and y increases from top to bottom
//   0  --------->  x
//   |
//   |
//   |
//   v
//   y
enum class Direction(val x: Int, val y: Int) {
    N(0,-1), NE(1,-1),
    E(1,0), SE(1, 1),
    S(0,1), SW(-1, 1),
    W(-1,0), NW(-1, -1)
}