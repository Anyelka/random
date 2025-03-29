package com.random.problems.leetCode.binarySearch

class Solution1 {

    fun search(nums: IntArray, target: Int): Int {
        return nums.findIndexBinarySearchRecursively(target)
    }


// Brute Force Solution: going through all the elements
//      Time complexity:    O(n)
//      Space complexity:   O(1)

    fun IntArray.findIndexBruteForce(num:Int): Int {
        var index = -1
        for(i in indices) {
            if(this[i] == num) index = i
        }
        return index
    }

    // Brute Force Solution improved: return directly
//      Time complexity:    O(n)
//      Space complexity:   O(1)
    fun IntArray.findIndexBruteForceDirectReturn(num:Int): Int {
        for(i in indices) {
            if(this[i] == num) return i
        }
        return -1
    }

// Binary Search Solution: recursively
//      Time complexity:    O(log(n))
//      Space complexity:   O(log(n))

    fun IntArray.findIndexBinarySearchRecursively(num: Int): Int {
        return findIndexBinarySearch(num, 0, this.size - 1)
    }

    fun IntArray.findIndexBinarySearch(num: Int, start: Int, end: Int): Int {
        if(start > end) return -1
        val mid = (start + end) / 2
        return if(this[mid] == num) {
            mid
        } else if(num > this[mid]) {
            findIndexBinarySearch(num, mid + 1, end)
        } else {
            findIndexBinarySearch(num, start, mid - 1)
        }
    }

// Binary Search Solution: iteratively
//      Time complexity:    O(log(n))
//      Space complexity:   O(1)

    fun IntArray.findIndexBinarySearchIteratively(num: Int): Int {
        var start = 0
        var end = size - 1
        while(start <= end) {
            val mid = (start + end) / 2
            if (get(mid) == num) return mid
            else if (num > get(mid)) start = mid + 1
            else end = mid - 1
        }
        return -1
    }
}