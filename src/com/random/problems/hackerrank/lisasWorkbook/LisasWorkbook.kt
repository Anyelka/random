package com.random.problems.hackerrank.lisasWorkbook

import com.random.util.getResourceAsText

const val FILE_PATH = "/hackerrank/lisasWorkbook/TestCase2"

fun main() {
    val file = getResourceAsText(FILE_PATH)

    val nAndK = file!!.lines()[0].split(" ")
    val arr = file.lines()[1].split(" ").map { it.toInt() }.toTypedArray()

    var result = workbook(nAndK[0].toInt(), nAndK[1].toInt(), arr)

    println(result)
}

fun workbook(n: Int, k: Int, arr: Array<Int>): Int {
    var result = 0
    var page = 1
    for(chapter in arr) {
        for(problem in 1..chapter) {
            if(problem == page) {
                result++
            }
            if(problem % k == 0 || problem == chapter) {
                page++
            }
        }
    }
    return result
}