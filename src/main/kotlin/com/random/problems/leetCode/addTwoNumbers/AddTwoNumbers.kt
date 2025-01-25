package com.random.problems.leetCode.addTwoNumbers

fun main() {
    val inputs: List<List<ListNode>> = listOf(
            getInput(arrayOf(2,4,3), arrayOf(5,6,4)),
            getInput(arrayOf(0), arrayOf(0)),
            getInput(arrayOf(9,9,9,9,9,9,9), arrayOf(9,9,9,9))
    )

    inputs.forEach {
        val result = AddTwoNumbers().addTwoNumbers(it[0], it[1])!!
        printResult(result)
    }
}

fun printResult(result: ListNode) {
    print("Result: ")
    printNext(result)
    println()
}

fun printNext(result: ListNode) {
    print(result.`val`)
    if (result.next != null) printNext(result.next!!)
}

fun getInput(array1: Array<Int>, array2: Array<Int>): List<ListNode> {
    val firstList = getListNode(array1)
    val secondList = getListNode(array2)
    return mutableListOf(firstList, secondList)
}

fun getListNode(array: Array<Int>): ListNode {
    val listNodes = mutableListOf<ListNode>()
    for(i in array.indices) {
        val elementValue = array[array.size-1-i]
        val node = ListNode(elementValue)
        if(i > 0) {
            node.next = listNodes.last()
        }
        listNodes.add(node)
    }
    return listNodes[listNodes.size-1]
}

class AddTwoNumbers {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val queue = ArrayDeque<ListNode>()
        queue.add(l1!!)
        queue.add(l2!!)
        val nodes = ArrayDeque<ListNode>()
        var addition = 0
        while (queue.isNotEmpty()) {
            var nextNumber: Int
            val first = queue.removeFirst()
            val second = queue.removeFirstOrNull()
            nextNumber = if(second != null) (first.`val`+second.`val`+addition) else (first.`val`+addition)
            if(first.next != null) queue.add(first.next!!)
            if(second?.next != null) queue.add(second.next!!)

            if(nextNumber >= 10) {
                nextNumber-= 10
                addition = 1
            } else {
                addition = 0
            }
            val nextElement = ListNode(nextNumber)

            val last = nodes.lastOrNull()
            if(last != null) {
                last.next = nextElement
            }
            nodes.add(nextElement)

        }
        if(addition == 1) {
            val nextElement = ListNode(addition)

            val last = nodes.lastOrNull()
            if(last != null) {
                last.next = nextElement
            }
            nodes.add(nextElement)
        }
        return nodes[0]
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}