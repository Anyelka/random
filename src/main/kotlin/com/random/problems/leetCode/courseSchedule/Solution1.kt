package com.random.problems.leetCode.courseSchedule

class Solution1 {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        return canFinish1(prerequisites)
    }

    //  1. First solution without optimization:
    //  DFS with locally visited and global visited lists to keep track of cycles and
    //      TC: O(V + E)
    //      SC: O(V + E)
    //      Runtime on leetcode: 62 ms
    private fun canFinish1(prerequisites: Array<IntArray>): Boolean {
        val adjacencyList = mutableMapOf<Int, MutableList<Int>>()

        for (prerequisite in prerequisites) {
            adjacencyList.getOrPut(prerequisite[0]) { mutableListOf() }.add(prerequisite[1])
        }

        val visited = mutableListOf<Int>()
        fun hasCycle(course: Int, localVisited: MutableList<Int>): Boolean {
            if (visited.contains(course)) return false
            if (localVisited.contains(course)) return true
            localVisited.add(course)
            val hasCycle = adjacencyList[course]?.any { hasCycle(it, localVisited) } ?: false
            localVisited.remove(course)
            visited.add(course)
            return hasCycle
        }

        for (course in adjacencyList) {
            if (visited.contains(course.key)) continue
            val localVisited = mutableListOf<Int>()
            if (hasCycle(course.key, localVisited)) return false
        }

        return true
    }

    // 2. Optimized DFS:
    //      TC: O(V + E)
    //      SC: O(V + E)
    //      Runtime on leetcode: 17 ms
    private fun canFinish2(prerequisites: Array<IntArray>): Boolean {
        val adjacencyList = mutableMapOf<Int, MutableList<Int>>()

        for (prerequisite in prerequisites) {
            adjacencyList.getOrPut(prerequisite[0]) { mutableListOf() }.add(prerequisite[1])
        }

        val visited = mutableSetOf<Int>()
        val visiting = mutableSetOf<Int>()
        fun hasCycle(course: Int): Boolean {
            if (course in visited) return false
            if (course in visiting) return true
            visiting.add(course)
            for(next in adjacencyList[course] ?: emptyList()) {
                if(hasCycle(next)) return true
            }
            visiting.remove(course)
            visited.add(course)
            return false
        }

        for (course in adjacencyList.keys) {
            if (hasCycle(course)) return false
        }

        return true
    }
}