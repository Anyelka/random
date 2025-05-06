package com.random.problems.leetCode.handOfStraights

class Solution1 {
    fun isNStraightHand(hand: IntArray, groupSize: Int): Boolean {
        return isNStraightHand1(hand, groupSize)
    }

    //  1. Greedy solution

    private fun isNStraightHand1(hand: IntArray, groupSize: Int): Boolean {
        if (hand.size % groupSize != 0) return false

        val handMap = hand.toList().groupingBy { it }.eachCount().toSortedMap().toMutableMap()

        fun pickCard(card: Int): Boolean {
            if (!handMap.contains(card)) return false
            handMap[card] = handMap[card]!!.minus(1)
            if (handMap[card] == 0) handMap.remove(card)
            return true
        }

        var currentGroupSize = 0
        var lastCard = -1

        fun nextCard() = if (lastCard == -1) handMap.entries.first().key else lastCard + 1

        while (handMap.isNotEmpty()) {
            val currentCard = nextCard()
            if (!pickCard(currentCard)) return false
            lastCard = currentCard
            currentGroupSize++
            if (currentGroupSize == groupSize) {
                currentGroupSize = 0
                lastCard = -1
            }
        }
        return true
    }
}