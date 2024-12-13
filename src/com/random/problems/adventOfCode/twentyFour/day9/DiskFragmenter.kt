package com.random.problems.adventOfCode.twentyFour.day9

import com.random.util.getResourceAsText
import kotlin.time.measureTime

const val FILE_PATH = "/adventOfCode/2024/day9/TestCase1"

fun main() {
    val input = getResourceAsText(FILE_PATH)!!

    val timeTaken1 = measureTime {
        val result = Part1().getChecksum(input)
        println("Checksum of the compacted filesystem: ${result}")
    }
    println("Time taken: $timeTaken1")

    val timeTaken12 = measureTime {
        val result = Part1().getChecksumDirectly1(input)
        println("-- directly: Checksum of the compacted filesystem: ${result}")
    }
    println("Time taken: $timeTaken12")

    val timeTaken13 = measureTime {
        val result = Part1().getChecksumDirectly2(input)
        println("-- directly v2: Checksum of the compacted filesystem: ${result}")
    }
    println("Time taken: $timeTaken13")

    val timeTaken2 = measureTime {
        val result = Part2().getChecksum(input)
        println("2. Only moving whole files --- Checksum of the compacted filesystem: ${result}")
    }
    println("Time taken: $timeTaken2")
}

private const val SPACE = "."

class Part1: Part() {

    // Direct solution (only for Part1) without writing the diskmap to the file block representation
    //      and then compacting it
    // this solution takes the block size alternatively from the left and right side of the diskmap
    //      and writes their index into the condensed
    fun getChecksumDirectly1(diskMap: String): Long {
        val charArray = diskMap.toCharArray()
        var startIndex = 0
        var endIndex = diskMap.length-1
        val compactedLayout = mutableListOf<String>()
        var isBlock = true

        var blockLength = 0
        while(startIndex < endIndex) {
            if(isBlock) {
                var startBlockLength = charArray[startIndex].digitToInt()
                while(startBlockLength > 0) {
                    compactedLayout.add((startIndex/2).toString())
                    startBlockLength--
                }
            } else {
                var spaceLength = charArray[startIndex].digitToInt()
                while(spaceLength > 0) {
                    if (blockLength == 0) {
                        blockLength = charArray[endIndex].digitToInt()
                    }
                    compactedLayout.add((endIndex/2).toString())
                    blockLength--
                    spaceLength--
                    if(blockLength == 0 && startIndex < endIndex) {
                        endIndex -= 2
                    }
                }
            }
            startIndex++
            isBlock = !isBlock
        }
        while(blockLength > 0) {
            compactedLayout.add((endIndex/2).toString())
            blockLength--
        }
        return compactedLayout.countChecksum()
    }

    fun getChecksumDirectly2(diskMap: String): Long {
        val charArray = diskMap.toCharArray()
        var startIndex = 0
        var endIndex = diskMap.length-1
        var isBlock = true
        var blockLength = 0

        var checksum = 0L
        var checksumIndex = 0
        while(startIndex < endIndex) {
            if(isBlock) {
                var startBlockLength = charArray[startIndex].digitToInt()
                while(startBlockLength > 0) {
                    checksum += checksumIndex * startIndex/2
                    startBlockLength--
                    checksumIndex++
                }
            } else {
                var spaceLength = charArray[startIndex].digitToInt()
                while(spaceLength > 0) {
                    if (blockLength == 0) {
                        blockLength = charArray[endIndex].digitToInt()
                    }
                    checksum += checksumIndex * endIndex/2
                    checksumIndex++
                    blockLength--
                    spaceLength--
                    if(blockLength == 0 && startIndex < endIndex) {
                        endIndex -= 2
                    }
                }
            }
            startIndex++
            isBlock = !isBlock
        }
        while(blockLength > 0) {
            checksum += checksumIndex * endIndex/2
            checksumIndex++
            blockLength--
        }
        return checksum
    }

    override fun compact(layout: MutableList<String>) {
        var firstSpaceIndex = layout.indexOfFirst { it == SPACE }
        var lastBlockIndex = layout.indexOfLast { it != SPACE }
        while(firstSpaceIndex < lastBlockIndex) {
            layout.swap(firstSpaceIndex, lastBlockIndex)
            while(layout[firstSpaceIndex] != SPACE) firstSpaceIndex++
            while(layout[lastBlockIndex] == SPACE) lastBlockIndex--
        }
    }
}

class Part2: Part() {
    override fun compact(layout: MutableList<String>) {
        // get the position of the first contiguous space
        var firstSpaceStart = layout.indexOfFirst { it == SPACE }
        var firstSpaceEnd = firstSpaceStart
        while(layout[firstSpaceEnd+1] == SPACE) firstSpaceEnd++
        // get the position of the last file
        var lastBlockEnd = layout.indexOfLast { it != SPACE }
        var lastBlockValue = layout[lastBlockEnd]
        var lastBlockStart = lastBlockEnd
        while(layout[lastBlockStart-1] == lastBlockValue) lastBlockStart--

        // go through each block from the end
        while(lastBlockStart > firstSpaceEnd) {
            var currentFirstSpaceStart = firstSpaceStart
            var currentFirstSpaceEnd = firstSpaceEnd
            // go through each space from the start
            while (currentFirstSpaceStart < lastBlockStart) {
                // find next space where the block fits
                while (currentFirstSpaceStart < lastBlockStart && currentFirstSpaceEnd - currentFirstSpaceStart < lastBlockEnd - lastBlockStart) {
                    currentFirstSpaceStart = currentFirstSpaceEnd + 1
                    while (currentFirstSpaceStart < layout.size && layout[currentFirstSpaceStart] != SPACE) currentFirstSpaceStart++
                    currentFirstSpaceEnd = currentFirstSpaceStart
                    while (currentFirstSpaceEnd + 1 < layout.size && layout[currentFirstSpaceEnd + 1] == SPACE) currentFirstSpaceEnd++
                }
                if (currentFirstSpaceStart < lastBlockStart && currentFirstSpaceEnd - currentFirstSpaceStart >= lastBlockEnd - lastBlockStart) {
                    layout.insert(currentFirstSpaceStart, lastBlockStart, lastBlockEnd)
                    break
                }
            }

            // update the variables the same way as in the initial step
            while(layout[firstSpaceStart] != SPACE) firstSpaceStart++
            firstSpaceEnd = firstSpaceStart
            while(layout[firstSpaceEnd+1] == SPACE) firstSpaceEnd++

            while(layout[lastBlockEnd] == SPACE || layout[lastBlockEnd] == lastBlockValue) lastBlockEnd--
            lastBlockValue = layout[lastBlockEnd]
            lastBlockStart = lastBlockEnd
            while(layout[lastBlockStart-1] == lastBlockValue) lastBlockStart--
        }
    }
}

abstract class Part {
    fun getChecksum(diskMap: String): Long {
        val blockLayout = getBlockLayout(diskMap)
        compact(blockLayout)
        return blockLayout.countChecksum()
    }

    abstract fun compact(layout: MutableList<String>)
    fun getBlockLayout(diskMap: String): MutableList<String> {
        var blockId = 0
        val layout = mutableListOf<String>()
        var isFileBlock = true
        for(char in diskMap.toCharArray()) {
            var numberOfBlocks = char.toString().toInt()
            while(numberOfBlocks > 0) {
                if(isFileBlock) {
                    layout.add(blockId.toString())
                } else {
                    layout.add(SPACE)
                }
                numberOfBlocks--
            }
            isFileBlock = !isFileBlock
            if(!isFileBlock) blockId++
        }
        return layout
    }
    fun MutableList<String>.countChecksum(): Long {
        return this.withIndex().sumOf {(i, it) -> if(it==SPACE) 0 else i * it.toLong()}
    }
}

private fun MutableList<String>.insert(toStartIndex: Int, fromStartIndex: Int, fromEndIndex: Int) =
        this.insert(toStartIndex, fromStartIndex, fromEndIndex, SPACE)

private fun <E> MutableList<E>.insert(toStartIndex: Int, fromStartIndex: Int, fromEndIndex: Int, emptyElement: E) {
    for(i in 0..fromEndIndex-fromStartIndex) this[toStartIndex + i] = this[fromStartIndex + i]
    for(i in fromStartIndex..fromEndIndex) this[i] = emptyElement
}

private fun <E> MutableList<E>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}
