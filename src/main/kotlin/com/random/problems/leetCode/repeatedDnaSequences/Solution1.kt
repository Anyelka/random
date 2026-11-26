package com.random.problems.leetCode.repeatedDnaSequences

class Solution1 {
    fun findRepeatedDnaSequences(s: String): List<String> {
        return findRepeatedDnaSequences2(s)
    }

    // 1. Sliding window with string manipulation
    // TC: O(n^2)
    //      because string.drop(n) has O(length-n) complexity
    fun findRepeatedDnaSequences1(s: String): List<String> {
        val n = s.length
        if(n < 10) return emptyList()
        val frequencyMap = mutableMapOf<String, Int>()
        var current = ""
        for(i in 0..<10) {
            current += s[i]
        }
        for(i in 0..n - 10) {
            frequencyMap[current] = (frequencyMap[current] ?: 0) +1
            if(i+10 < n){
                current = current.drop(1)
                current += s[i+10]
            }
        }
        return frequencyMap
            .filter { it.value > 1 }
            .map { it.key }
    }

    fun findRepeatedDnaSequences2(s: String): List<String> {
        val n = s.length
        if(n < 10) return emptyList()

        // This mask is to remove the leftmost character
        //  this is a 20 bit int with all 1s:
        //      - 1 shifted left 20 times -> 00..001 => 100..00 (20 zeroes in total)
        //      - 1 deducted from it                 => 011..11 (1 zero, 20 1s in total)
        //                                           => we can remove the 1 zero: 20 bit all with 1s
        //      => if we AND mask anything, it will only keep the last 20 bits
        val mask = (1 shl 20) - 1
        var hash = 0

        // 4 values => can be represented in 2 bits
        fun code(c: Char): Int =
            when (c) {      //  2 bits
                'A' -> 0    //  00
                'C' -> 1    //  01
                'G' -> 2    //  10
                else -> 3 // T  11
            }

        val seen = mutableSetOf<Int>()
        val repeated = mutableSetOf<Int>()
        val result = mutableListOf<String>()

        fun shiftWindowWithNextChar(c: Char) {
            // - shift left 2: make place for the next character at the end
            //      => this also increases the length to 22 bits
            //  - add the new char code to the last 2 bits
            //  - remove the first two bits with the mask
            hash = ((hash shl 2) or code(c)) and mask
        }

        for(i in 0..<10) {
            shiftWindowWithNextChar(s[i])
        }

        for(i in 0..n - 10) {
            // We add it to result, if we have already seen it once, but it was not repeated yet
            //          => this is exactly the second time we see it
            if(!seen.add(hash) && repeated.add(hash)) {
                result.add(s.substring(i, i+10))
            }

            if(i+10 < n) {
                shiftWindowWithNextChar(s[i + 10])
            }
        }
        return result
    }
}