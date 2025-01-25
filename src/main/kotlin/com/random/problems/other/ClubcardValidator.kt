package com.random.problems.other

fun main() {
    val inputs: Map<Number, String> = mapOf(
        346088187380157 to "True",
        4916283358950480 to "True",
        5454411550469035 to "False",
        4518377421351212 to "False",
        "000000000000000000".toLong() to "True",
        123456789123456789 to "True",
        757575757575757575 to "True",
        888285347688482669 to "True",
        10 to "False",
        111111111111111111 to "False",
        439438978430223493 to "False",
        "012432823491235903".toLong() to "False"
    )

    var correct = true
    inputs.forEach{
        val result = validateClubcardNumber(it.key)
        if(result != it.value) {
            println("Wrong result: ${result} instead of ${it.value} for ${it.key}")
            correct = false
        }
    }
    if(correct) {
        println("PERFECT SOLUTION")
    }

}

fun validateClubcardNumber(number: Number): String {
    val numberAsString = number.toString()
    var sum = 0
    for(i in numberAsString.indices) {
        val currentNum = numberAsString[numberAsString.length - i - 1].digitToInt()
        val currentValue = if(i % 2 != 0) (if(currentNum * 2 > 9) currentNum * 2 - 9 else currentNum * 2) else currentNum
        sum += currentValue
    }
    return if(sum % 10 == 0) "True" else "False"
}