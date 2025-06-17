package com.random.problems.leetCode.lruCache

class LRUCache2(private val capacity: Int): LRUCache(capacity) {
    val map = mutableMapOf<Int, Node>()
    val left = Node(0, 0)
    val right = Node(0, 0)

    init {
        left.next = right
        right.prev = left
    }

    override fun get(key: Int): Int {
        map[key]?.let {
            remove(it)
            insertAtRight(it)
            return it.value
        }
        return -1
    }

    override fun put(key: Int, value: Int) {
        map[key]?.let { remove(it) }
        map[key] = Node(key, value)
        insertAtRight(map[key]!!)
        if(map.size > capacity) {
            val lru = left.next
            remove(lru!!)
            map.remove(lru.key)
        }
    }

    fun remove(node: Node) {
        val prev = node.prev
        val next = node.next
        prev?.next = next
        next?.prev = prev
    }
    fun insertAtRight(node: Node) {
        val prev = right.prev
        val next = right
        prev?.next = node
        next.prev = node
        node.next = next
        node.prev = prev
    }
}

class Node(val key: Int, val value: Int) {
    var prev: Node? = null
    var next: Node? = null
}