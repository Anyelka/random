package com.random.problems.other.dragonByte.twentySix.round1.waterSlides

import com.random.problems.other.dragonByte.Problem

const val PATH = "/other/dragonByte/twentySix/round1/waterSlides"
const val CHECKER_SCRIPT_PATH = "src/main/kotlin/com/random/problems/other/dragonByte/twentySix/round1/waterSlides/W-checker.py"

class WaterSlides: Problem<Triple<Int,Int, IntArray>>() {
    override fun getPath(): String = PATH

    override fun convertInput(lines: List<String>): List<Triple<Int, Int, IntArray>> {
        return lines
            .chunked(2)
            .map { (header, values) ->
                val (first, second) = header.split(" ").map(String::toInt)
                val array = values.split(" ").map(String::toInt).toIntArray()
                Triple(first, second, array)
            }
    }

    override fun solve(input: Triple<Int, Int, IntArray>): List<String> {
        val (n,k,points) = input
        return Solution1().solve(n,k,points)
    }

    override fun testWithChecker(file: String) {
        val process = ProcessBuilder(
            "python3",
            CHECKER_SCRIPT_PATH,
            "${getResourcesPath()}${getInputFile(file)}",
            "${getResourcesPath()}${getOutputFile(file)}",
            "1",
            "1"
        ).redirectErrorStream(true).start()


        val output = process.inputStream.bufferedReader().readText()
        val exitCode = process.waitFor()
        println("Output of checker:")
        println(output)

    }

}