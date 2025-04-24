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

fun convertListNodeToIntArray(root: ListNode?): IntArray {
    val list = mutableListOf<Int>()
    var current = root
    while(current != null) {
        list.add(current.`val`)
        current = current.next
    }
    return list.toIntArray()
}