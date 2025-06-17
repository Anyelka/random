package com.random.problems.leetCode.mergeTripletsToFormTargetTriplet

class Solution {
    fun mergeTriplets(triplets: Array<IntArray>, target: IntArray): Boolean {
        // we need to search triplets where a given place is equal to the target (a/b/c)
        //  and all the other numbers are smaller/equal to the target
        //   we can make the transformations if we find this for all 3 places

        // TC:  O(n)
        // SC:  O(1)

        var aFound = false
        var bFound = false
        var cFound = false

        for((a,b,c) in triplets) {
            if(!aFound && a == target[0]) {
                if(b <= target[1] && c <= target[2]) aFound = true
            }
            if(!bFound && b == target[1]) {
                if(a <= target[0] && c <= target[2]) bFound = true
            }
            if(!cFound && c == target[2]) {
                if(b <= target[1] && a <= target[0]) cFound = true
            }
            if(aFound && bFound && cFound) return true
        }

        return false
    }
}