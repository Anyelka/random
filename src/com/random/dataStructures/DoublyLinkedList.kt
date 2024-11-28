package com.random.dataStructures


fun main() {
    val linkedList = DoublyLinkedList<Employee>()
    val viksi = Employee("Kovacs", "Viktoria", "1")
    val marsi = Employee("Takacs", "Marton", "2")
    val laci = Employee("Kovacs", "Laszlo", "3")

    linkedList.insertAtTail(viksi)
    linkedList.insertAtTail(marsi)
    linkedList.insertAtTail(laci)

    val atHead = Employee("At", "Head", "321")
    linkedList.insertAtHead(atHead)

    val inBetween = Employee("Between", "Two", "1-2")
    linkedList.insertBetween(inBetween, viksi, marsi)

    linkedList.deleteBetween(inBetween, laci)

    println(linkedList)

}

class DoublyLinkedList<E> {
    var head: Node<E>? = null
    var tail: Node<E>? = null
     var size = 0

    fun isEmpty(): Boolean {
        return head == null && tail == null
    }

    fun insertAtHead(element: E) {
        val previousHead = head
        val node = Node(previousHead, null, element)
        if(isEmpty()) {
            tail = node
        }
        previousHead?.previous = node
        head = node
        size++
    }
    fun insertAtTail(element: E) {
        val previousTail = tail
        val node = Node(null, previousTail, element)
        if(isEmpty()) {
            head = node
        }
        previousTail?.next = node
        tail = node
        size++
    }
    fun deleteFromHead() {
        if(isEmpty()) {
            System.err.println("Could not delete from empty list")
            return
        }
        val nextHead = head?.next
        if(nextHead == null) {
            tail = null
        }
        nextHead?.previous = null
        head = nextHead
        size--
    }
    fun deleteFromTail() {
        if(isEmpty()) {
            System.err.println("Could not delete from empty list")
            return
        }
        val nextTail = tail?.previous
        if(nextTail == null) {
            head = null
        }
        nextTail?.next = null
        tail = nextTail
        size--
    }

    fun insertBetween(element: E, first: E, second: E) {
        var currentElement = head
        var node: Node<E>? = null
        while(currentElement?.equals(tail) != true) {
            if(currentElement?.isValue(first) == true && currentElement.next?.isValue(second) == true) {
                val current = currentElement
                val next = currentElement.next
                node = Node(next, current, element)
                current.next = node
                next?.previous = node
                break
            }
            currentElement = currentElement?.next
        }
        if(node != null) {
            size++
        } else {
            System.err.println("Could not insert between $first and $second")
        }
    }

    fun deleteBetween(first: E, second: E) {
        if(isEmpty()) {
            System.err.println("Could not delete from empty list")
            return
        }
        var currentElement = head
        var deleted = false
        while(currentElement?.equals(tail) != true) {
            if(currentElement?.isValue(first) == true
                    && currentElement.next?.next?.isValue(second) == true) {
                val current = currentElement
                val next = currentElement.next?.next
                current.next = next
                next?.previous = current
                deleted = true
                break
            }
            currentElement = currentElement?.next
        }
        if(deleted) {
            size--
        } else {
            System.err.println("Could not delete between $first and $second")
        }
    }


    private val linkArrow = "<->"

    override fun toString(): String {
        return "HEAD ${concat(head)} $linkArrow TAIL"
    }

    private fun concat(head: Node<E>?): String {
        if(head == null) {
            return ""
        }
        return " $linkArrow ${head.value}${concat(head.next)}"
    }
}

class Node<E>(var next: Node<E>?, var previous: Node<E>?, var value: E) {
    fun isValue(value: E): Boolean {
        return this.value!! == value
    }
}

class Employee(val firstName: String, val lastName: String, val id: String) {
    override fun toString(): String {
        return "[$id $firstName $lastName]"
    }
}