package com.random.problems.leetCode.validParenthesesString

class Solution1 {
    fun checkValidString(s: String): Boolean {
        return checkValidString3(s)
    }

    fun checkValidString1(s: String): Boolean {
        var open = 0
        var wildcard = 0
        for(c in s) {
            if(c == '(') {
                open++
            } else if(c == '*') {
                wildcard++
            } else {
                if(open > 0) open--
                else if(wildcard > 0) wildcard--
                else return false
            }
        }

        if(wildcard < open) return false

        open = 0
        wildcard = 0

        for(i in s.lastIndex downTo 0) {
            val c = s[i]
            if(c == ')') {
                open++
            } else if(c == '*') {
                wildcard++
            } else {
                if(open > 0) open--
                else if(wildcard > 0) wildcard--
                else return false
            }
        }

        return wildcard >= open
    }

    fun checkValidString2(s: String): Boolean {
        val stack = ArrayDeque<Char>()
        for(c in s) {
            if(c == '(' || c == '*') {
                stack.addLast(c)
            } else {
                if(stack.isEmpty()) return false
                var i = stack.lastIndex

                while(i >= 0 && stack[i] != '(') i--

                if(i < 0) {
                    stack.removeLast()
                } else {
                    stack.removeAt(i)
                }
            }
        }

        var wildcards = 0
        while(stack.isNotEmpty()) {
            val next = stack.removeLast()
            if(next == '*') {
                wildcards++
            } else {
                if(wildcards == 0) {
                    return false
                }
                wildcards--
            }
        }

        return true
    }

    fun checkValidString3(s: String): Boolean {
        var minOpen = 0
        var maxOpen = 0
        for(c in s) {
            when(c) {
                '(' -> { minOpen++; maxOpen++ }
                ')' -> { minOpen = maxOf(minOpen - 1, 0); maxOpen-- }
                '*' -> { minOpen = maxOf(minOpen - 1, 0); maxOpen++ }
            }
            if(maxOpen < 0) return false
        }
        return minOpen == 0
    }
}