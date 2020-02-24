package dev.mee42

fun <T> List<T>.sublist(start: Int, end: Int = this.size): List<T> {
    return this.subList(start, end)
}