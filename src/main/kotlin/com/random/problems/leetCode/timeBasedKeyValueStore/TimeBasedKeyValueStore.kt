package com.random.problems.leetCode.timeBasedKeyValueStore

import com.random.util.isCorrectStringWithExpected

fun main() {
    val input = listOf(
        (arrayOf("TimeMap","set","get","get","set","get","get") to arrayOf(arrayOf(),arrayOf("foo","bar",1),arrayOf("foo",1),arrayOf("foo",3),arrayOf("foo","bar2",4),arrayOf("foo",4),arrayOf("foo",5))) to arrayOf(null,null,"bar","bar",null,"bar2","bar2"),
        (arrayOf("TimeMap","set","get","get","set","get","get") to arrayOf(arrayOf(),arrayOf("foo","bar",1),arrayOf("foo",1),arrayOf("foo",3),arrayOf("foo","bar2",4),arrayOf("foo",3),arrayOf("foo",5)) to arrayOf(null,null,"bar","bar",null,"bar","bar2"))
    )

    input.forEach {
        val (input, output) = it
        val (operationNames, operationParameters) = input

        var timeMap = TimeMap()

        fun makeOperation(i: Int): String? {
            return when(operationNames[i]) {
                "set" -> {
                    val (key, value, time) = operationParameters[i]
                    timeMap.set(key.toString(), value.toString(), time as Int)
                    null
                }
                "get" -> {
                    val (key, time) = operationParameters[i]
                    timeMap.get(key.toString(), time as Int)
                }
                else -> null
            }
        }

        val result = operationNames.indices.map { makeOperation(it) }

        println("Result: ${result} - ${isCorrectStringWithExpected(result, output)}")

    }
}