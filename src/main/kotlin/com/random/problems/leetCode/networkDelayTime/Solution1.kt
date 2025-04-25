package com.random.problems.leetCode.networkDelayTime

class Solution1 {
    // [s,d,t]
    //  set k as source
    //  try and travel to all the nodes
    //      get the max travel time
    //
    //  How to populate the dp?
    //  times: (2,1,1),(2,3,1),(3,4,1),(2,4,1)
    //  dp:
    //  0   1   2   3   4   j
    //  1   .   .   .   .
    //  2   1   .   1   .
    //  3   .   .   .   1
    //  4   .   .   .   .
    //  i

    //  times: (1,2,1),(3,4,1),(2,3,2)
    //  dp:
    //  0   1   2   3   4   j
    //  1   .   1   .   .
    //  2   .   .   2   .
    //  3   .   .   .   1
    //  4   .   .   .   .
    //  i


    // write in the time: populate(source, target, time):
    // - check the field in source - target:
    //      - if empty (=null) we write in the time
    //      - if not empty, we write in the min of (current value, time)
    // - check all the rows = sources where column=j=source:
    //      - if empty do nothing
    //      - if not empty - populate(rowj,target,value(rowj,j) + time)
    // - check all the columns = targets where row=i=target:
    //      - if empty do nothing
    //      - if not empty - populate(source,columni, value(i,columni) + time)

    //  TC: O(n ^ 3)
    //  SC: O(n ^ 2)
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
        val minTimes: Array<Array<Int?>> = Array(n) { Array(n) { null } }

        fun populate(source: Int, target:Int, time:Int) {
            if ((minTimes[source - 1][target - 1] ?: Int.MAX_VALUE) > time) {
                minTimes[source - 1][target - 1] = time

                for (i in 1..n) {
                    minTimes[i - 1][source - 1]?.let { populate(i, target, it + time) }
                    minTimes[target - 1][i - 1]?.let { populate(source, i, it + time) }
                }
            }
        }

        times.forEach { populate(it[0], it[1], it[2]) }

        return (1..n).filter{ it != k }.maxOf { minTimes[k - 1][it - 1] ?: return -1 }
    }
}