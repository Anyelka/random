package com.random.problems.leetCode.kokoEatingBananas

class Solution2 {
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        return minEatingSpeed2(piles, h)
    }

    // 1. Linear search by incrementing speed from 1 to piles.max and trying out each solution
    //  TC: O(m * p)
    //  SC: O(1)
    //   where:
    //  - m = max pile size
    //  - p = number of piles
    private fun minEatingSpeed1(piles: IntArray, h: Int): Int {
        var speed = 1
        while (speed <= piles.max()) {
            var timeLeft = h
            for (pile in piles) {
                val fullHoursSpent = pile / speed
                val fractionalHoursSpent = (pile % speed).coerceAtMost(1)
                val hoursSpent = fullHoursSpent + fractionalHoursSpent
                timeLeft -= hoursSpent
                if (timeLeft < 0) break
            }
            if (timeLeft < 0) {
                speed++
                continue
            }
            return speed
        }
        return 0
    }

    // 2. Binary search the speed between 1 and max
    //  TC: O(log(m) * p)
    //  SC: O(1)
    private fun minEatingSpeed2(piles: IntArray, h: Int): Int {
        fun getTimeLeft(speed: Int): Int {
            if(speed == 0) return -1
            var timeLeft = h
            for (pile in piles) {
                timeLeft -= pile / speed + (pile % speed).coerceAtMost(1)
                if (timeLeft < 0) break
            }
            return timeLeft
        }

        var left = 1
        var right = piles.max()
        while (left <= right) {
            val speed = (left + right) / 2

            val prevTimeLeft = getTimeLeft(speed - 1)
            val timeLeft = getTimeLeft(speed)

            if(prevTimeLeft < 0 && timeLeft >= 0) return speed
            else if(timeLeft < 0) left = speed + 1
            else right = speed - 1
        }
        return 0
    }
}