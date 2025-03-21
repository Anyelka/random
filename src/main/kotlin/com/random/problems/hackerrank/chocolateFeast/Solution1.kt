package com.random.problems.hackerrank.chocolateFeast

class Solution1 {
    fun chocolateFeast(n: Int, c: Int, m: Int): Int {
        return chocolateFeast2(n, c, m, 0)
    }

    fun chocolateFeast(money: Int, cost: Int, wrapExchangeRate: Int, wraps: Int): Int {
        if (money < cost) return 0
        val chocolates: Int = money / cost
        val totalWraps = wraps + chocolates
        val addedMoney = totalWraps / wrapExchangeRate * cost
        val remainingWraps = totalWraps % wrapExchangeRate
        return chocolates + chocolateFeast((money % cost) + addedMoney, cost, wrapExchangeRate, remainingWraps)
    }

    fun chocolateFeast2(money: Int, cost: Int, wrapExchangeRate: Int, wraps: Int): Int {
        if (money < cost && wraps < wrapExchangeRate) return 0
        val chocolates: Int = wraps / wrapExchangeRate + money / cost
        return chocolates + chocolateFeast2(money % cost, cost, wrapExchangeRate, chocolates + (wraps % wrapExchangeRate))
    }

}