package com.random.problems.hackerrank.timeinwords

import com.random.util.getResourceAsText
import com.random.util.toDigits

val FILE_PATH = "/hackerrank/timeinminutes/TestCase0"

fun main() {
    val file = getResourceAsText(FILE_PATH)

    file?.split("\n")?.map { printLineResult(it) }
}

fun printLineResult(line: String) {
    if(line != "") {
        printTimeInWords(line.split(":")[0], line.split(":")[1])
    }
}

/*fun main(args: Array<String>) {
    val h = readLine()!!.trim().toInt()

    val m = readLine()!!.trim().toInt()

    val result = timeInWords(h, m)

    println(result)
}*/

fun printTimeInWords (h: String, m: String) {
    println(timeInWords(h.toInt(), m.toInt()))
}

fun timeInWords(h: Int, m: Int): String {
    if (m == 0) {
        return convertNumberToWord(h) + " o' clock"
    }
    val afterHalf = m > 30
    val minute = if (afterHalf) 60 - m else m
    val hour = if(afterHalf) h + 1 else h
    return convertNumberToWord(minute) + " " + getPreposition(minute, afterHalf) + " " + convertNumberToWord(hour)
}

fun getPreposition(m: Int, afterHalf: Boolean): String {
    val hourPreposition : String = if (afterHalf) "to" else "past"
    val minutePreposition = getMinutePreposition(m)
    val concatenation = if(minutePreposition != "") " " else ""
    return minutePreposition + concatenation + hourPreposition
}

fun getMinutePreposition(m: Int): String {
    return if(m == 1) "minute"
                else (if(m == 15 || m == 30) ""
                    else "minutes")
}

fun convertNumberToWord(number: Int): String {
    return numberToWordMap.getOrElse(number) { number.getAsText() }
}

fun Int.getAsText(): String  {
    val digits = this.toDigits()
    if(digits[0] == 1) {
        return convertNumberToWord(digits[1]) + "teen"
    }
    if(digits[0] == 2) {
        return "twenty " + convertNumberToWord(digits[1])
    }
    if(digits[0] == 3) {
        return "thirty " + convertNumberToWord(digits[1])
    }
    if(digits[0] == 4) {
        return "forty " + convertNumberToWord(digits[1])
    }
    if(digits[0] == 5) {
        return "fifty " + convertNumberToWord(digits[1])
    }
    throw IllegalArgumentException("Unsupported number: $this")
}


val numberToWordMap = mapOf<Int, String>(
        0 to "o' clock",
        1 to "one",
        2 to "two",
        3 to "three",
        4 to "four",
        5 to "five",
        6 to "six",
        7 to "seven",
        8 to "eight",
        9 to "nine",
        10 to "ten",
        11 to "eleven",
        12 to "twelve",
        13 to "thirteen",
        15 to "quarter",
        20 to "twenty",
        30 to "half",
        40 to "forty",
        50 to "fifty"
)

