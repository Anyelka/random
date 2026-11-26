package com.random.problems.leetCode.repeatedDnaSequences

import com.random.util.test
import com.random.util.testWithMeasuredTime
import com.random.util.testWithSet

fun main() {
    val input = listOf(
        "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT" to listOf("AAAAACCCCC","CCCCCAAAAA"),
        "AAAAAAAAAAAAA" to listOf("AAAAAAAAAA"),
        "TTTGTTTTTTTTTTTTTTTGTTTTTCTGCGCGGTTTTTTTCTGCGCGGTTTTTTTTTTTTTTTATTTTTTGTTT" to listOf("TTTTTTTTTT","TTTTTCTGCG","TTTTCTGCGC","TTTCTGCGCG","TTCTGCGCGG","TCTGCGCGGT","CTGCGCGGTT","TGCGCGGTTT","GCGCGGTTTT","CGCGGTTTTT","GCGGTTTTTT","CGGTTTTTTT","GTTTTTTTTT","TTTTTTGTTT"),
        "T" to listOf()
    )

    input.forEach {
        val s = it.first
        it.testWithMeasuredTime { Solution1().findRepeatedDnaSequences(s) }
    }
}