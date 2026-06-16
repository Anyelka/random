package com.random.problems.leetCode.satisfiabilityOfEqualityOfEquations

class Solution1 {
    fun equationsPossible(equations: Array<String>): Boolean {
    val minCode = 'a'.code
    val maxCode = 'z'.code
    val parents = IntArray(maxCode - minCode + 1) { it }

    fun findX(x: Int): Int {
        if(parents[x] != x) {
            return findX(parents[x])
        }
        return parents[x]
    }
    fun find(a: Char): Int = findX(a.code-minCode)

    fun unionXY(x: Int, y: Int) {
        val xParent = findX(x)
        val yParent = findX(y)
        if(xParent < yParent) parents[yParent] = xParent
        if(xParent > yParent) parents[xParent] = yParent
    }
    fun union(a: Char, b: Char) = unionXY(a.code-minCode, b.code-minCode)

    val cantBeEqual = mutableListOf<Pair<Char, Char>>()
    for(eq in equations) {
        val a = eq[0]
        val b = eq[3]
        if(eq[1] == '=') union(a, b)
        else cantBeEqual.add(a to b)
    }

    for((a, b) in cantBeEqual) {
        if(find(a) == find(b)) return false
    }

    return true
}
}