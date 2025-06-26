package com.random.problems.leetCode.implementTrie

abstract class Trie {
    abstract fun insert(word: String)
    abstract fun search(word: String): Boolean
    abstract fun startsWith(prefix: String): Boolean
}