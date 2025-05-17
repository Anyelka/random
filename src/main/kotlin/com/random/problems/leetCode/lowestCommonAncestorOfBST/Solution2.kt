package com.random.problems.leetCode.lowestCommonAncestorOfBST

import com.random.util.TreeNode

class Solution2 {
    //  TC: O(h)
    //  SC: O(h)
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        val (lower, higher) = if(p!!.`val` > q!!.`val`) q to p else p to q
        if(root!!.`val` in lower.`val`..higher.`val`) return root
        else if(root.`val` > higher.`val`) return lowestCommonAncestor(root.left, p, q)
        else return lowestCommonAncestor(root.right, p, q)
    }
}