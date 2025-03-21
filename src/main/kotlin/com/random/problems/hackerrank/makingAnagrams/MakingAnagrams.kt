package com.random.problems.hackerrank.makingAnagrams

import com.random.util.isCorrectStringWithExpected

fun main() {
    val input = listOf(
        ("abc" to "amnop") to 6,
        ("cde" to "abc") to 4,
        ("" to "") to 0,
        ("a" to "abcdefghijk") to 10,
        ("a" to "a") to 0,
        ("absdjkvuahdakejfnfauhdsaavasdlkj" to "djfladfhiawasdkjvalskufhafablsdkashlahdfa") to 19
    )

    input.forEach { it.test() }
}

private fun Pair<Pair<String, String>, Int>.test() {
    val result = Solution1().makingAnagrams(first.first, first.second)
    println("Result for strings: $first = $result - ${isCorrectStringWithExpected(result, second)}")
}
