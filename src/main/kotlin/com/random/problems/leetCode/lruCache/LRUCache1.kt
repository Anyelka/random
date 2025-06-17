package com.random.problems.leetCode.lruCache

// 1. Solution:
//  TC:
//      - init: O(1)
//      - get:  O(n)
//      - set:  O(n)
class LRUCache1(private val capacity: Int): LRUCache(capacity) {
    private val map = mutableMapOf<Int, Int>()
    private val sortedByAccessTime = ArrayDeque<Int?>()

    override fun get(key: Int): Int {
        if(map[key] != null) {
            sortedByAccessTime.removeAt(sortedByAccessTime.indexOf(key))
            sortedByAccessTime.addLast(key)
        }
        return map[key] ?: -1
    }

    override fun put(key: Int, value: Int) {
        if(map[key] == null) {
            if(sortedByAccessTime.size >= capacity) {
                val removed = sortedByAccessTime.removeFirst()
                map.remove(removed)
            }
            sortedByAccessTime.addLast(key)
        } else {
            sortedByAccessTime.removeAt(sortedByAccessTime.indexOf(key))
            sortedByAccessTime.addLast(key)
        }

        map[key] = value
    }
}