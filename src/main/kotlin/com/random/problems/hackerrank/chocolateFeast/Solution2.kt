package com.random.problems.hackerrank.chocolateFeast

class Solution2 {
    fun chocolateFeast(n: Int, c: Int, m: Int): Int {
        var barsEaten = 0
        var wrappers = 0
        var money = n
        while(money >= c || wrappers >= m) {
            val barsBought = money / c
            val barsExchanged = wrappers / m
            money -= barsBought * c
            wrappers %= m

            val totalBarsEaten = barsBought + barsExchanged
            barsEaten += totalBarsEaten
            wrappers += totalBarsEaten
        }

        return barsEaten
    }
}