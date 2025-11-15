package com.random.problems.leetCode.longestRepeatingCharacterReplacement

class Solution2 {
    fun characterReplacement(s: String, k: Int): Int {
        // input: a string and an int
        // we can change at most k letters
        // get the longest sequence of the same letters

        // -> we are looking for the longest substring where max k letters are not the same

        // how do we check how many different characters we can have?
        // - keep track of the most frequent characters
        // - keep track of the number of all the characters except for the most frequent characters

        // ABAB


        /*
        k = 2
        ABCACB

        i = 0; j = 0
                A   -> A: 1                => valid
        j++     B   -> A: 1; B: 1          => valid
        j++     C   -> A: 1; B: 1; C:1     => valid
        j++     A   -> A: 2; B: 1; C:1     => valid
        j++     C   -> A: 2; B: 1; C:2     => invalid
        i++     /A  -> A: 1; B: 1; C:2     => valid
        j++     B   -> A: 1; B: 2; C:2     => invalid
        */

        var i = 0
        var j = 0

        var maxLength = 0

        val charFrequencyMap = mutableMapOf<Char,Int>()

        while(j < s.length) {
            charFrequencyMap[s[j]] = (charFrequencyMap[s[j]] ?: 0) + 1

            val maxCount = charFrequencyMap.maxByOrNull { it.value }?.value ?: 0
            while((j - i + 1) - maxCount > k) {
                charFrequencyMap[s[i]] = (charFrequencyMap[s[i]] ?: 0) - 1
                i++
            }

            maxLength = maxOf(maxLength, j - i + 1)
            j++
        }

        return maxLength
    }
}