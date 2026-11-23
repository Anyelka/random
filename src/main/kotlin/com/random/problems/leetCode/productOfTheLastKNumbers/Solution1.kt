package com.random.problems.leetCode.productOfTheLastKNumbers

class Solution1 {
    /*
      n = [3,2,5,4]
      p = [3,6,30,120]
      product(1) = p(last) / p(last-1) = 120/30 = 4
      product(2) = p(last) / p(last-2) = 120/6 = 20
      product(3) = p(last) / p(last-3) = 120/3 = 40

      n = [3,0,2,5,4]
      p = [3,1,2,10,40]
      lastZeroIndex=1
      product(1) = p(last)/p(last-1)=40/10=4
      product(2) = p(last)/p(last-2)=40/2=20
      product(3) = p(last)/p(last-3)=40/1=40
      product(4) =>
          n = 5 => lastIndex = n-1=4
          k = 4
          n-k = 5-4 >= lastZeroIndex: 5-4=1 >=1
          product = 0
    */


    var lastZeroIndex = -1
    val products = mutableListOf<Int>().also { it.add(1) }

    // TC: O(1)
    fun add(num: Int) {
        if(num == 0) {
            products.add(1)
            lastZeroIndex = products.lastIndex
        } else {
            products.add(products.last() * num)
        }
    }
    // TC: O(1)
    fun getProduct(k: Int): Int {
        if(products.size - k <= lastZeroIndex) {
            return 0
        }
        return products.last() / products[products.lastIndex-k]
    }
}