package com.random.problems.adventOfCode.twentyFour.util

data class Grid(val fields: Array<Array<Char>>) {
    companion object {
        fun of(lines: List<String>) = Grid(lines.map { it.toCharArray().toTypedArray() }.toTypedArray())
    }

    fun all(): kotlin.collections.Map<Position, Char> {
        return fields.withIndex().map { (i, line) ->
            line.withIndex().map { (j, char) -> Position(j, i) to char }
        }.flatten().toMap()
    }
    fun checkIfInBounds(position: Position)
            = position.y >= 0 && position.y < fields.size && position.x >= 0 && position.x < fields[0].size

    operator fun get(position: Position) = charAt(position)
    fun charAt(position: Position)
            = if(position.y in fields.indices && position.x in fields[position.y].indices)
        fields[position.y][position.x]
    else null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Grid

        return fields.contentDeepEquals(other.fields)
    }

    override fun hashCode(): Int {
        return fields.contentDeepHashCode()
    }

}