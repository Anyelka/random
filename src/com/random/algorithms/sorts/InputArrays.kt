package com.random.algorithms.sorts

object InputArrays {
    fun get(): Map<IntArray, IntArray> {
        return mapOf(
                Pair(intArrayOf(20, 35, -15, 7, -22, 1, 55), intArrayOf(-22, -15, 1, 7, 20, 35, 55)),
                Pair(
                        intArrayOf(25852, 18681, 34660, 83664, 4773, 29044, 41343, 73432, 27534, 69171, 35708, 4870, 60518, 82254, 37345),
                        intArrayOf(4773, 4870, 18681, 25852, 27534, 29044, 34660, 35708, 37345, 41343, 60518, 69171, 73432, 82254, 83664)
                ),
                Pair(intArrayOf(64, -30, 22, 49, 85, 95, 45, -15, 19, 134), intArrayOf(-30, -15, 19, 22, 45, 49, 64, 85, 95, 134)),
        )
    }

    fun pair100kInt(): Pair<IntArray, IntArray> {
        return IntArray100k.pair()
    }
}