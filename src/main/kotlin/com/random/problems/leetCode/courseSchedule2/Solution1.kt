package com.random.problems.leetCode.courseSchedule2

class Solution1 {
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        return findOrder2Fun(prerequisites, numCourses)
    }

    //  Solution 1:
    //  Iterate over each course number
    //      DFS through prerequisites
    //      - write each course number into the results list if they have no more prerequisites = when we reach the bottom with DFS
    //
    //      TC: O(V + E)
    //      SC: O(V)
    private fun findOrder1(prerequisites: Array<IntArray>, numCourses: Int): IntArray {
        val sequence = mutableListOf<Int>()

        if (prerequisites.isEmpty()) return IntArray(numCourses) { it }

        fun writePrerequisite(course: Int, visited: MutableList<Int>): List<Int>? {
            val result = mutableListOf<Int>()
            if(sequence.contains(course)) return emptyList()
            if (visited.contains(course)) return null
            visited.add(course)
            for (prerequisite in prerequisites) {
                if (prerequisite[0] == course) {
                    result.add(prerequisite[1])
                    result.addAll(writePrerequisite(prerequisite[1], visited) ?: return null)
                }
            }
            if ((result.isEmpty() || sequence.containsAll(result))) sequence.add(course)
            visited.remove(course)
            return result
        }

        for (i in 0..<numCourses) {
            writePrerequisite(i, mutableListOf()) ?: return intArrayOf()
        }
        return sequence.toIntArray()
    }

    // 2 . Topological sort with adjacency list
    //  TC: O(V + E)
    //  SC: O(V)
    private fun findOrder2(prerequisites: Array<IntArray>, numCourses: Int): IntArray {
        // 1. build adjacency list:
        val adjacencyMap = prerequisites.groupBy { it[0] }.mapValues { it -> it.value.map { it[1] }.toMutableList() }.toMutableMap()

        val result = mutableListOf<Int>()

        // 2. visit all nodes BFS
        fun visitDFS(course: Int, visited: MutableList<Int>): Boolean {
            if(visited.contains(course)) return false
            if(result.contains(course)) return true
            visited.add(course)
            val dependencies = adjacencyMap[course] ?: emptyList()
            for(dependency in dependencies) {
                if(!visitDFS(dependency, visited)) return false
            }
            result.add(course)
            visited.remove(course)
            return true
        }

        val hasCycle = (0..<numCourses).any { !visitDFS(it, mutableListOf()) }

        return if(hasCycle) intArrayOf() else result.toIntArray()
    }

    private fun findOrder2Fun(prerequisites: Array<IntArray>, numCourses: Int): IntArray {
        val adjacencyMap = prerequisites.groupBy { it[0] }.mapValues { it -> it.value.map { it[1] }.toMutableList() }.toMutableMap()

        val result = mutableListOf<Int>()

        fun visitDFS(course: Int, visited: MutableList<Int>): Boolean {
            if (visited.contains(course)) return false
            if (result.contains(course)) return true
            visited.add(course)
            (adjacencyMap[course] ?: emptyList())
                .takeIf { it.any { i -> !visitDFS(i, visited) } }
                ?.let { return false }
            result.add(course)
            visited.remove(course)
            return true
        }

        return if ((0..<numCourses).any { !visitDFS(it, mutableListOf()) }) intArrayOf() else result.toIntArray()
    }
}