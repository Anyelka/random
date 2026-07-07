package com.random.problems.other.dragonByte

import java.nio.file.Files
import java.nio.file.Paths

const val RESOURCES_FOLDER = "src/main/resources"

fun getResourceAsText(path: String): String? =
    object {}.javaClass.getResource(path)?.readText()

abstract class Problem<INPUT> {
    fun test(vararg files: String) = files.forEach { test(it) }
    fun testWithChecker(vararg files: String) = files.forEach { testWithChecker(it) }
    fun solveAndTestWithChecker(vararg files: String)  = files.forEach { solveAndTestWithChecker(it) }
    fun solve(filename: String) {
        val result = getInputTestCases(filename).map { solve(it).joinToString("\n") }
        Files.writeString(Paths.get("${getResourcesPath()}/${getPath()}/${filename}.out"), result.joinToString(separator = "\n"))
    }
    fun solveAndTestWithChecker(filename: String) {
        println("Solving ${filename}...")
        solve(filename)
        println("Testing ${filename} with checker...")
        testWithChecker(filename)
    }

    abstract fun getPath(): String
    fun getResourcesPath() = RESOURCES_FOLDER
    abstract fun convertInput(lines: List<String>): List<INPUT>
    abstract fun solve(input: INPUT): List<String>
    open fun testWithChecker(file: String) {
        println("testWithChecker function not implemented...")
    }

    fun getInputFile(filename: String) = "${getPath()}/$filename.in"
    fun getOutputFile(filename: String) = "${getPath()}/$filename.out"
    private fun test(filename: String) {
        val result = getInputTestCases(filename).flatMap { solve(it) }
        val expectedResult = getOutputTestCaseResults("${filename}_expected")
        println("Result for: $filename is: ${isCorrect(result, expectedResult)}")
    }

    private fun isCorrect(result: List<String>, expected: List<String>): String {
        return if(result == expected) "CORRECT" else "wrong... - first wrong answer: ${firstWrong(result, expected)}"
    }

    private fun firstWrong(result: List<String>, expected: List<String>): String {
        return result.withIndex().first { (index, it) -> it != expected[index] }
            .let { (index, pair) -> "line: $index is $pair - it should be ${expected[index]}" }
    }

    private fun parseInputLines(filename: String) =
        getResourceAsText(getInputFile(filename))!!.lines().drop(1).filter { it.isNotEmpty() }

    private fun getInputTestCases(filename: String): List<INPUT> = convertInput(parseInputLines(filename))

    private fun getOutputTestCaseResults(filename: String) =
        getResourceAsText(getOutputFile(filename))!!.lines().filter { it.isNotEmpty() }

}