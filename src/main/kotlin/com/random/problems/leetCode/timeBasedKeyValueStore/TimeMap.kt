package com.random.problems.leetCode.timeBasedKeyValueStore

class TimeMap() {
    val map: MutableMap<String, MutableList<Pair<Int, String>>> = mutableMapOf()

    fun set(key: String, value: String, timestamp: Int) {
        val index = map.getOrPut(key) { mutableListOf() }.binarySearchBy(timestamp) { it.first }
        if(index > 0) map[key]!![index] = timestamp to value
        else map[key]!!.add((-index-1), timestamp to value)
    }

    fun get(key: String, timestamp: Int): String {
        val values = map[key] ?: emptyList()
        var l = 0
        var r = values.lastIndex
        while(l <= r) {
            val mid = (l + r) / 2
            val (time1, value1) = values[mid]
            val time2 = if(mid + 1 > values.lastIndex) Int.MAX_VALUE else values[mid + 1].first
            if(timestamp in time1..<time2) {
                return value1
            } else if(timestamp < time1) {
                r = mid - 1
            } else {
                l = mid + 1
            }
        }

        return ""
    }

}