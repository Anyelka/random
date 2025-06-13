package com.random.problems.leetCode.timeBasedKeyValueStore

class TimeMap() {
    val map: MutableMap<String, MutableList<Pair<Int, String>>> = mutableMapOf()

    fun set(key: String, value: String, timestamp: Int) {
        val list = map.getOrPut(key) { mutableListOf() }
        val index = list.binarySearchBy(timestamp) { it.first }
        if(index >= 0) list[index] = timestamp to value
        else list.add((-index-1), timestamp to value)
    }

    fun get(key: String, timestamp: Int): String {
        val values = map[key] ?: return ""
        var i = values.binarySearchBy(timestamp) { it.first }
        if(i == -1) return ""
        if(i < 0) i = -i-2
        return values[i].second
    }

}