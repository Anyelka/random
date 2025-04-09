package com.random.problems.hackerrank.biggerIsGreater

class Solution3 {
    fun biggerIsGreater(w: String): String {

        // h e f g
        // 8 5 6 7
        // next greater:
        // 8 5 7 6
        //=h e g f
        //
        // d  k  h  c
        // 4  11 8  3       - > this is the greatest one starting with 4
        // next greater:    - > has to start with 8
        // 8  3  4  11
        // h  c  d  k
        //  - we have to find the first char place i where w[i] < w[i + 1]
        //  - we have to find the first char place j for i where w[i] < w[j]
        //  - we have to swap the two,
        //  - we have to invert the rest of the array after i
        //
        val chars = w.toCharArray()
        var i = w.length - 2
        while(i >= 0 && w[i].code >= w[i + 1].code) i--

        if(i < 0) return "no answer"

        var j = w.length - 1
        while(j > 0 && w[j].code <= w[i].code) j--

        chars.swap(i, j)

        return (chars.take(i+1) + chars.drop(i+1).invert()).joinToString("")
    }
}

private fun List<Char>.invert(): List<Char> {
    val result = mutableListOf<Char>()
    for(i in this.size - 1 downTo 0) result.add(this[i])
    return result
}

private fun CharArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}
