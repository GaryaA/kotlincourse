package games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
*/
fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> {
    val result = filterNotNull().toMutableList()
    if (size < 2) return result

    var windowStart = 0
    var windowEnd = 1

    while (windowEnd < result.size) {
        if (result[windowEnd] == result[windowStart]) {
            result[windowEnd] = merge(result[windowStart])
            result.removeAt(windowStart)
        } else {
            windowEnd++
            windowStart++
        }
    }
    return result.toList()
}


//fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> =
//    filterNotNull()
//        .apply { if (size < 2) return this }
//        .reversed()
//        .windowed(2, 1, false) { (a, b) ->
//            if (a == b) listOf(merge(b)) else listOf(a, b)
//        }.flatten()
//        .reversed()










