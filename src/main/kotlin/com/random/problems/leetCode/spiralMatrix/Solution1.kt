package com.random.problems.leetCode.spiralMatrix

class Solution1 {
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val visited = mutableSetOf<Pair<Int,Int>>()
        val result = mutableListOf<Int>()
        var direction = Direction.RIGHT
        var currentSquare = 0 to 0
        while(!currentSquare.isOutOfBounds(matrix) && !visited.contains(currentSquare)) {
            result.add(matrix[currentSquare.first][currentSquare.second])
            visited.add(currentSquare)

            var next = currentSquare.next(direction)
            if(next.isOutOfBounds(matrix) || visited.contains(next)) {
                direction = direction.next()
                next = currentSquare.next(direction)
            }
            currentSquare = next
        }

        return result
    }

    fun Pair<Int,Int>.next(direction: Direction) = (this.first + direction.i) to (this.second + direction.j)
    fun Pair<Int,Int>.isOutOfBounds(matrix: Array<IntArray>) = first !in 0..matrix.lastIndex || second !in 0..matrix[0].lastIndex

    enum class Direction(val i:Int, val j: Int) {
        UP(-1,0), RIGHT(0,1),DOWN(1,0),LEFT(0,-1);
         fun next(): Direction = when(this) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
            else -> throw IllegalArgumentException("Invalid direction: $this")
        }
    }
}