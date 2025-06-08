package com.random.problems.leetCode.longestRepeatingCharacterReplacement

import com.random.util.test

fun main() {
    val input = listOf(
        ("ABAB" to 2) to 4,
        ("AABABBA" to 1) to 4,
        ("ABABB" to 1) to 4,
        ("ABCCBA" to 2) to 4,
        ("BBAABBAA" to 2) to 6,
        ("BBABAAA" to 1) to 5,
        ("ABBB" to 2) to 4,
        ("KRSCDCSONAJNHLBMDQGIFCPEKPOHQIHLTDIQGEKLRLCQNBOHNDQGHJPNDQPERNFSSSRDEQLFPCCCARFMDLHADJADAGNNSBNCJQOF" to 4) to 7,
        ("BAAAB" to 2) to 5,
        ("BAAA" to 1) to 4
    )

    input.forEach {
        val (s, k) = it.first
        it.test { Solution1().characterReplacement(s, k) }
    }
}