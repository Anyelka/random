package com.random.problems.leetCode.regularExpressionMatching

class Solution1 {
    fun isMatch(s: String, p: String): Boolean {
        val memo = Array(s.length + 1) { Array<Boolean?>(p.length + 1) { null } }

        fun isMatch(si: Int, pi: Int): Boolean {
            memo[si][pi]?.let { return it }
            if(si == s.length && pi == p.length) return true
            if(pi >= p.length) return false
            val char = p[pi]
            val isMultiple = pi < p.lastIndex && p[pi+1] == '*'
            var isMatch = false
            if(isMultiple) {
                if(isMatch(si, pi + 2)) {
                    isMatch = true
                } else {
                    for(i in si..s.lastIndex) {
                        if(char != '.' && s[i] != char) break
                        if(isMatch(i + 1, pi + 2)) {
                            isMatch = true
                            break
                        }
                    }
                }
            } else {
                isMatch = si <= s.lastIndex && (char == '.' || s[si] == char) && isMatch(si + 1, pi + 1)
            }
            memo[si][pi] = isMatch
            return isMatch
        }

        return isMatch(0,0)
    }
}