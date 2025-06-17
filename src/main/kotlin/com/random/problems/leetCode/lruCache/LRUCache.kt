package com.random.problems.leetCode.lruCache

abstract class LRUCache(capacity: Int) {
    abstract fun get(key: Int): Int
    abstract fun put(key: Int, value: Int)
}