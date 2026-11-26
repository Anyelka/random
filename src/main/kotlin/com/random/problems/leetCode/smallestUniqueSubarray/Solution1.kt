package com.random.problems.leetCode.smallestUniqueSubarray

class Solution1 {
    fun smallestUniqueSubarray(nums: IntArray): Int {
        return smallestUniqueSubarray5(nums)
    }

    // 1. Brute force
    //  TC: O(n^3)
    fun smallestUniqueSubarray1(nums: IntArray): Int {
        for(i in 1..nums.size) {
            // i = size of the window
            val occurences = mutableMapOf<String,Int>()
            for(j in 0..nums.size - i) {
                // j = start of the window
                val currentNums = mutableListOf<Int>()
                for(k in j..<j+i) {
                    // k = current element in the window
                    currentNums.add(nums[k])
                }
                val key = currentNums.joinToString(",")
                occurences[key] = (occurences[key] ?: 0) + 1
            }
            if(occurences.values.min() == 1) return i
        }
        return nums.size
    }

    // 2. Inner sliding window:
    //  Still too expensive as removeFirst in array is O(n)
    //  TC: O(n^3)
    fun smallestUniqueSubarray2(nums: IntArray): Int {
        for(i in 1..nums.size) {
            // i = size of the window
            val currentNums = mutableListOf<Int>()
            val occurences = mutableMapOf<String,Int>()

            // we init the subarray at the start with size i
            for(j in 0..<i) {
                currentNums.add(nums[j])
            }

            // we slide the window
            for(j in 0..nums.size - i) {
                // j = start of the window
                val key = currentNums.joinToString(",")
                occurences[key] = (occurences[key] ?: 0) + 1
                if(i+j <= nums.lastIndex) {
                    currentNums.removeFirst()
                    currentNums.add(nums[i+j])
                }
            }
            if(occurences.values.min() == 1) {
                return i
            }
        }
        return nums.size
    }

    // 3. Optimized Sliding Window with nums.slice()
    // TC: O(n^3) still, because slice takes O(size) to copy each number
    fun smallestUniqueSubarray3(nums: IntArray): Int {
        for (size in 1..nums.size) {
            val occurrences = mutableMapOf<List<Int>, Int>()

            for (start in 0..nums.size - size) {
                val subarray = nums.slice(start until start + size)

                occurrences[subarray] =
                    (occurrences[subarray] ?: 0) + 1
            }

            if (occurrences.values.min() == 1) {
                return size
            }
        }

        return nums.size
    }

    // 4. Rolling hash with prefix sum:
    // This allows us to retrieve the number of subarrays with a given length in O(1) time
    // TC: O(n^2)
    fun smallestUniqueSubarray4(nums: IntArray): Int {
        val base = 1_000_003L
        val n = nums.size
        val pow = LongArray(n + 1).also { it[0] = 1 }
        val prefix = LongArray(n + 1)
        for (i in nums.indices) {
            pow[i + 1] = pow[i] * base
            prefix[i + 1] = prefix[i] * base + nums[i]
        }

        fun hash(start: Int, end: Int): Long {
            return prefix[end + 1] - prefix[start] * pow[end - start + 1]
        }

        for(i in 1..nums.size) {
            val occurences = mutableMapOf<Long, Int>()
            for(j in 0..n-i) {
                val hash = hash(j, j + i - 1)
                occurences[hash] = (occurences[hash] ?: 0) + 1
            }
            if(occurences.values.min() == 1) {
                return i
            }
        }
        return nums.size
    }

    // 4. Rolling hash with prefix sum and binary search
    // TC: O(n * log(n))
    fun smallestUniqueSubarray5(nums: IntArray): Int {
        val base = 1_000_003L
        val n = nums.size
        val pow = LongArray(n + 1).also { it[0] = 1 }
        val prefix = LongArray(n + 1)
        for (i in nums.indices) {
            pow[i + 1] = pow[i] * base
            prefix[i + 1] = prefix[i] * base + nums[i]
        }

        fun hash(start: Int, end: Int): Long {
            return prefix[end + 1] - prefix[start] * pow[end - start + 1]
        }

        var l = 1
        var r = n
        while(l < r) {
            val mid = (l + r) / 2

            val occurences = mutableMapOf<Long, Int>()

            for(j in 0..n-mid) {
                val hash = hash(j, j + mid - 1)
                occurences[hash] = (occurences[hash] ?: 0) + 1
            }
            if(occurences.values.any { it == 1 }) {
                r = mid
            } else {
                l = mid + 1
            }
        }
        return l
    }
}