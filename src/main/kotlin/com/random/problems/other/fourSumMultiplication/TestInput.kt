package com.random.problems.other.fourSumMultiplication

import com.random.util.getResourceAsText

const val SMALL_FILE_PATH = "/other/fourSumMultiplication/TestCase0"
const val MEDIUM_FILE_PATH = "/other/fourSumMultiplication/TestCase1"
const val LARGE_FILE_PATH = "/other/fourSumMultiplication/TestCase2"
const val VERY_LARGE_FILE_PATH = "/other/fourSumMultiplication/TestCase3"

fun smallInput() = getInput(SMALL_FILE_PATH)
fun mediumInput() = getInput(MEDIUM_FILE_PATH)
fun largeInput() = getInput(LARGE_FILE_PATH)
fun varyLargeInput() = getInput(VERY_LARGE_FILE_PATH)


fun generated1() = intArrayOf(1, 2, 3, 4, 6, 8, 12, 24, 48, 96) to listOf(
    listOf(0, 1, 2, 4), // 1 * 2 * 3 = 6
    listOf(0, 1, 3, 5), // 1 * 2 * 4 = 8
    listOf(0, 1, 4, 6), // 1 * 2 * 6 = 12
    listOf(0, 1, 6, 7), // 1 * 2 * 12 = 24
    listOf(0, 1, 7, 8), // 1 * 2 * 24 = 48
    listOf(0, 1, 8, 9), // 1 * 2 * 48 = 96
    listOf(0, 2, 3, 6), // 1 * 3 * 4 = 12
    listOf(0, 2, 5, 7), // 1 * 3 * 8 = 24
    listOf(0, 3, 4, 7), // 1 * 4 * 6 = 24
    listOf(0, 3, 6, 8), // 1 * 4 * 12 = 48
    listOf(0, 3, 7, 9), // 1 * 4 * 24 = 96
    listOf(0, 4, 5, 8), // 1 * 6 * 8 = 48
    listOf(0, 5, 6, 9), // 1 * 8 * 12 = 96
    listOf(1, 2, 3, 7), // 2 * 3 * 4 = 24
    listOf(1, 2, 5, 8), // 2 * 3 * 8 = 48
    listOf(1, 3, 4, 8), // 2 * 4 * 6 = 48
    listOf(1, 3, 6, 9), // 2 * 4 * 12 = 96
    listOf(1, 4, 5, 9), // 2 * 6 * 8 = 96
    listOf(2, 3, 5, 9) // 3 * 4 * 8 = 96
)

fun generated2() = intArrayOf(1, 2, 3, 4, 6, 8, 12, 18, 20, 21, 23, 24, 27, 29, 30, 31, 39, 40, 41, 44, 47, 49) to
        listOf(
            listOf(0, 1, 2, 4), // 1 * 2 * 3 = 6
            listOf(0, 1, 3, 5), // 1 * 2 * 4 = 8
            listOf(0, 1, 4, 6), // 1 * 2 * 6 = 12
            listOf(0, 1, 6, 11), // 1 * 2 * 12 = 24
            listOf(0, 1, 8, 17), // 1 * 2 * 20 = 40
            listOf(0, 2, 3, 6), // 1 * 3 * 4 = 12
            listOf(0, 2, 4, 7), // 1 * 3 * 6 = 18
            listOf(0, 2, 5, 11), // 1 * 3 * 8 = 24
            listOf(0, 3, 4, 11), // 1 * 4 * 6 = 24
            listOf(1, 2, 3, 11) // 2 * 3 * 4 = 24
        )

fun generated3() = intArrayOf(
    65, 40, 57, 30, 83, 20, 1, 6, 21, 81, 18, 24, 49, 12, 12, 55, 8, 31, 54, 100, 93, 18, 23,
    3, 51, 84, 56, 47, 41, 84, 29, 39, 2, 8, 4, 40, 6, 27, 44, 24
) to listOf(
    listOf(5, 6, 32, 35),
    listOf(6, 7, 34, 39),
    listOf(6, 13, 32, 39),
    listOf(6, 14, 32, 39),
    listOf(6, 16, 23, 39),
    listOf(6, 23, 32, 36),
    listOf(6, 23, 33, 39),
    listOf(6, 34, 36, 39),
    listOf(23, 32, 34, 39)
)

fun generated4() = intArrayOf(
    44, 85, 22, 96, 24, 41, 81, 2, 54, 6, 65, 27, 50, 12, 51, 8, 30, 18, 98, 3, 33,
    36, 24, 49, 78, 73, 72, 40, 48, 26, 58, 45, 79, 39, 52, 1, 68, 4, 72, 69
) to listOf(
    listOf(4, 19, 35, 38),
    listOf(7, 9, 19, 21),
    listOf(7, 13, 19, 26),
    listOf(7, 13, 19, 38),
    listOf(7, 15, 19, 28),
    listOf(7, 21, 35, 38),
    listOf(9, 13, 35, 38),
    listOf(9, 19, 37, 38),
    listOf(17, 35, 37, 38),
    listOf(19, 22, 35, 38)
)


private fun getInput(filePath: String) =
    getResourceAsText("${filePath}_in")!!.toIntArray() to getResourceAsText("${filePath}_out")!!.toListOfIntLists()

private fun String.toListOfIntLists() = this.split("],").map {
    it.split(",").map { i -> i.stripParentheses().strip().toInt() }.toList()
}.toList()

private fun String.toIntArray() = this.stripParentheses().split(",").map { it.toInt() }.toIntArray()
private fun String.stripParentheses() = this.replace("[", "").replace("]", "")