package com.random.algorithms.sorts.testdata

const val RESOURCES_PATH = "/algorithms/sorts/"

object TestDataProvider {
    private val BASE_ARRAY_PAIR = Pair(arrayOf(20, 35, -15, 7, -22, 1, 55), arrayOf(-22, -15, 1, 7, 20, 35, 55))

    fun getBase(): Map<Array<Int>, Array<Int>> {
        return mapOf(BASE_ARRAY_PAIR)
    }

    fun getAll(): Map<Array<Int>, Array<Int>> {
        return mapOf(
                BASE_ARRAY_PAIR,
                Pair(
                        arrayOf(25852, 18681, 34660, 83664, 4773, 29044, 41343, 73432, 27534, 69171, 35708, 4870, 60518, 82254, 37345),
                        arrayOf(4773, 4870, 18681, 25852, 27534, 29044, 34660, 35708, 37345, 41343, 60518, 69171, 73432, 82254, 83664)
                ),
                Pair(arrayOf(64, -30, 22, 49, 85, 95, 45, -15, 19, 134), arrayOf(-30, -15, 19, 22, 45, 49, 64, 85, 95, 134)),
        )
    }

    fun getForCountingSort(): Map<IntArray, IntArray> {
        return mapOf(Pair(intArrayOf(2,5,9,8,2,8,7,10,4,3), intArrayOf(2,2,3,4,5,7,8,8,9,10)))
    }

    fun getForRadixSort(): Map<Array<Int>, Array<Int>> {
        return mapOf(Pair(arrayOf(4725, 4586, 1330, 8792, 1594, 5729), arrayOf(1330, 1594, 4586, 4725, 5729, 8792)))
    }

    fun get100kLength(): Map<Array<Int>, Array<Int>> {
        return getDataFromFile("100k")
    }

    fun get1MLength(): Map<Array<Int>, Array<Int>> {
        return getDataFromFile("1M")
    }

    fun get100kForCountingSort(): Map<Array<Int>, Array<Int>> {
        return getDataFromFile("CountingSort100k")
    }

    fun get100kForRadixSort(): Map<Array<Int>, Array<Int>> {
        return getDataFromFile("RadixSort100k")
    }

    private fun getDataFromFile(pattern: String): Map<Array<Int>, Array<Int>> {
        val data = dataFromFile("IntArray${pattern}Input", "IntArray${pattern}Output")
        return mapOf(data.pair())
    }

    private fun dataFromFile(inputFileName: String, outputFileName: String): TestDataFromFile {
        return TestDataFromFile(file(inputFileName), file(outputFileName))
    }

    private fun file(name: String): String {
        return "$RESOURCES_PATH$name"
    }
}