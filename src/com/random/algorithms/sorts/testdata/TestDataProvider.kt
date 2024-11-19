package com.random.algorithms.sorts.testdata

const val RESOURCES_PATH = "/algorithms/sorts/"

object TestDataProvider {
    private val BASE_ARRAY_PAIR = Pair(intArrayOf(20, 35, -15, 7, -22, 1, 55), intArrayOf(-22, -15, 1, 7, 20, 35, 55))

    fun getBase(): Map<IntArray, IntArray> {
        return mapOf(BASE_ARRAY_PAIR)
    }

    fun getAll(): Map<IntArray, IntArray> {
        return mapOf(
                BASE_ARRAY_PAIR,
                Pair(
                        intArrayOf(25852, 18681, 34660, 83664, 4773, 29044, 41343, 73432, 27534, 69171, 35708, 4870, 60518, 82254, 37345),
                        intArrayOf(4773, 4870, 18681, 25852, 27534, 29044, 34660, 35708, 37345, 41343, 60518, 69171, 73432, 82254, 83664)
                ),
                Pair(intArrayOf(64, -30, 22, 49, 85, 95, 45, -15, 19, 134), intArrayOf(-30, -15, 19, 22, 45, 49, 64, 85, 95, 134)),
        )
    }

    fun getForCountingSort(): Map<IntArray, IntArray> {
        return mapOf(Pair(intArrayOf(2,5,9,8,2,8,7,10,4,3), intArrayOf(2,2,3,4,5,7,8,8,9,10)))
    }

    fun get100kLength(): Map<IntArray, IntArray> {
        val data = dataFromFile("/algorithms/sorts/IntArray100kInput", "/algorithms/sorts/IntArray100kOutput")
        return mapOf(data.pair())
    }

    fun get1MLength(): Map<IntArray, IntArray> {
        val data = dataFromFile("/algorithms/sorts/IntArray1MInput", "/algorithms/sorts/IntArray1MOutput")
        return mapOf(data.pair())
    }

    private fun dataFromFile(inputFileName: String, outputFileName: String): TestDataFromFile {
        return TestDataFromFile(file(inputFileName), file(outputFileName))
    }

    private fun file(name: String): String {
        return "$RESOURCES_PATH$name"
    }
}