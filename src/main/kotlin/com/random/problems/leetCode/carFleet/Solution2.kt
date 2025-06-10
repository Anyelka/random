package com.random.problems.leetCode.carFleet

class Solution2 {
    fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
        // calculate the time-to-finish for each car:
        //  target - position / speed

        /*
                target = 12
                10 8 0 5 3
                sort by position:
                10 8 5 3 0      pos
                 2 4 1 3 1      speed
                TTF:
                 1 1 7 3 12

                target = 100
                0 2 4
                sort by position:
                4  2  0           pos
                1  2  4           speed
                TTF:
                96 49 25
                if TTF > largest TTF fleet++
         */
        var maxTTF = 0.0
        val sortedPos = mutableListOf<Pair<Int,Int>>()
        for(i in 0..position.lastIndex) {
            sortedPos.add(position[i] to speed[i])
        }
        sortedPos.sortBy { -it.first }

        var fleetCount = 0
        for((pos, pace) in sortedPos) {
            val ttf = (target.toDouble() - pos) / pace
            if(ttf > maxTTF) {
                fleetCount++
                maxTTF = ttf
            }
        }
        return fleetCount
    }
}