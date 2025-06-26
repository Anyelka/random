package com.random.problems.leetCode.implementTrie

class Solution1: Trie() {
    val root = Node('#')

    override fun insert(word: String) {
        insert(root, word, 0)
    }

    override fun search(word: String): Boolean {
        return search(root, word, 0)
    }

    override fun startsWith(prefix: String): Boolean {
        return startsWith(root, prefix, 0)
    }

    private fun insert(parent: Node, word: String, index: Int) {
        val current = word[index]
        val next = parent.children.getOrPut(current) { Node(current) }
        if(index == word.lastIndex) {
            next.isEndOfWord = true
        } else {
            insert(next, word, index + 1)
        }
    }

    private fun search(parent: Node, word: String, index: Int): Boolean {
        if(index == word.length) return parent.isEndOfWord
        val current = word[index]
        val next = parent.children[current] ?: return false
        return search(next, word, index + 1)
    }

    private fun startsWith(parent: Node, prefix: String, index: Int): Boolean {
        if(index == prefix.length) return true
        val current = prefix[index]
        val next = parent.children[current] ?: return false
        return startsWith(next, prefix, index + 1)
    }
}

class Node(val `val`: Char) {
    val children = mutableMapOf<Char, Node>()
    var isEndOfWord = false
}