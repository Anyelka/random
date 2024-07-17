package com.random.problems.other.diamondDependency

import com.random.problems.getResourceAsText

val FILE_PATH = "/other/diamondDependency/TestCase99"

fun main() {
    val inputFile = getResourceAsText(FILE_PATH + "_in")
    val outputFile = getResourceAsText(FILE_PATH + "_out")

    val lines: List<String> = inputFile!!.lines()
    val outputFileLines: List<String> = outputFile!!.lines()

    var currentDictionary: MutableMap<String, List<String>> = mutableMapOf()

    var currentDictionarySize = 0
    var outputIndex = 0

    var wrongResults = 0

    for (line in lines) {
        val intLine = line.toIntOrNull()
        if(intLine != null) {
            currentDictionarySize = intLine
        } else {
            val segments = line.split(" ")
            currentDictionary[segments[1]] = segments.takeLast(segments.size-2)
            currentDictionarySize--
        }
        if(currentDictionarySize == 0) {
            val hasDiamondDependency = hasAnyCommonDependency(currentDictionary)
            if(outputFileLines[outputIndex] == "yes" != hasDiamondDependency) {
                println("Wrong result for dictionary: $currentDictionary")
                println(" --------- should be: " + outputFileLines[outputIndex] + " but hasDiamondDependency is $hasDiamondDependency")
                wrongResults++
            }
            currentDictionary = mutableMapOf()
            outputIndex++
        }
    }
    if(wrongResults==0) {
        println(" ===== ALL SOLUTIONS CORRECT ====")
    } else {
        println("Number of wrong dictionaries: $wrongResults...")
    }

}

fun hasAnyCommonDependency(dictionary: Map<String, List<String>>): Boolean {
    for (entry in dictionary) {
        if(hasDiamondDependency(entry, dictionary)) {
            return true
        }
    }
    return false
}

fun hasDiamondDependency(entry: Map.Entry<String, List<String>>, dictionary: Map<String, List<String>>): Boolean {
    val dependencies = entry.value
    return dependencies.isNotEmpty() && dependencies.any{ hasAnyCommonDependency(it, dependencies, dictionary) }
}

fun hasAnyCommonDependency(library: String, dependencies: List<String>, dictionary: Map<String, List<String>>): Boolean {
    return dependencies.any { otherLibrary -> otherLibrary != library
            && hasCommonDependency(otherLibrary, library, dictionary)
    }
}

fun hasCommonDependency(library1: String, library2: String, dictionary: Map<String, List<String>>): Boolean {
    return dictionary[library1]?.any { it == library2 || hasCommonDependency(it, library2, dictionary) } == true
            || dictionary[library2]?.any { it == library1 || hasCommonDependency(it, library1, dictionary) } == true
}
