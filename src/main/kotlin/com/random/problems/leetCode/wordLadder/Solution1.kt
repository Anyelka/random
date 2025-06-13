package com.random.problems.leetCode.wordLadder

import kotlin.math.abs

class Solution1 {
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        return ladderLength2(beginWord, wordList, endWord)
    }

    // 1. Bellman - Ford algorithm
    //  TC: O(n ^ 2 * L)
    //  SC: O(n)
    //  too slow...
    fun ladderLength1(
        beginWord: String,
        wordList: List<String>,
        endWord: String
    ): Int {
        var lengths = mutableMapOf<String,Int>()
        for (word in wordList) { lengths[word] = Int.MAX_VALUE }
        lengths[beginWord] = 1

        while (true) {
            val nextLengths = lengths.toMutableMap()

            for ((src, length) in lengths) {
                if (length == Int.MAX_VALUE) continue
                for (dst in wordList) {
                    if (nextLengths[dst] != Int.MAX_VALUE) continue
                    if (src.canTransformTo(dst)) {
                        nextLengths[dst] = length + 1
                    }
                }
            }

            if((lengths[endWord] ?: Int.MAX_VALUE) != Int.MAX_VALUE) return lengths[endWord]!!

            if (lengths != nextLengths) {
                lengths = nextLengths
            } else {
                break
            }
        }
        return if ((lengths[endWord] ?: Int.MAX_VALUE) == Int.MAX_VALUE) 0 else lengths[endWord]!!
    }

    // 2. BFS
    //  TC: O(n ^ 2 * L)
    //  SC: O(n)
    fun ladderLength2(
        beginWord: String,
        wordList: List<String>,
        endWord: String
    ): Int {
        val wordSet = wordList.toMutableSet()
        val queue = ArrayDeque<Pair<String,Int>>()
        queue.add(beginWord to 1)

        while (queue.isNotEmpty()) {
            val (word, length) = queue.removeFirst()
            if(word == endWord) return length
            val toRemove = mutableSetOf<String>()
            for(next in wordSet) {
                if(word.canTransformTo(next)) {
                    queue.add(next to length + 1)
                    toRemove.add(next)
                }
            }
            wordSet.removeAll(toRemove)
        }
        return 0
    }

    fun String.canTransformTo(other: String): Boolean {
        var differentChars = 0
        for (i in this.indices) {
            if(this[i] != other[i]) differentChars++
            if(differentChars > 1) return false
        }
        return true
    }
}