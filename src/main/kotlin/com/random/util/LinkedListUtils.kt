package com.random.util

fun convertIntArrayToListNode(array: IntArray): ListNode? {
    var root: ListNode? = null
    var current: ListNode? = null
    for(i in array) {
        val next = ListNode(i)
        if(root == null) root = next
        else current!!.next = next
        current = next
    }
    return root
}

fun convertIntArrayToListNodeWithCycle(array: IntArray, lastNodeConnectingTo: Int): ListNode? {
    var root: ListNode? = null
    var current: ListNode? = null
    var lastNodeConnectingNode: ListNode? = null
    for((i, num) in array.withIndex()) {
        val next = ListNode(num)
        if(i == lastNodeConnectingTo) lastNodeConnectingNode = next
        if(root == null) root = next
        else current!!.next = next
        current = next
    }
    current!!.next = lastNodeConnectingNode
    return root
}

fun convertListNodeToIntArray(root: ListNode?): IntArray {
    val list = mutableListOf<Int>()
    var current = root
    while(current != null) {
        list.add(current.`val`)
        current = current.next
    }
    return list.toIntArray()
}