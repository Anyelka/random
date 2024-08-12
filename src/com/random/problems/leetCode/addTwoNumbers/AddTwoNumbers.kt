package com.random.problems.leetCode.addTwoNumbers

fun main() {
    val inputs: List<ListNode> = getInput(
            arrayOf(2,4,3), arrayOf(5,6,4)
    )
    val result = AddTwoNumbers().addTwoNumbers(inputs[0], inputs[1])!!
    println(result)
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
        return l1
    }
}

class ListNode(var value: Int) {
    var next: ListNode? = null
}