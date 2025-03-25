package nicestring

fun String.isNice(): Boolean {
//    val pred1 = {
//        !this.contains("bu") &&
//                !this.contains("ba") &&
//                !this.contains("be")
//    }
    val pred1 = setOf("bu", "ba", "be").none { contains(it) }

//    val pred2 = {
//        this.filter {
//            it == 'a'
//                    || it == 'e'
//                    || it == 'i'
//                    || it == 'o'
//                    || it == 'u'
//        }.count() >= 3
//    }
    val pred2 = count { it in "aeiou" } >= 3

//    val pred3 = l@{
//        for (ch in 'a'..'z') {
//            if (this.contains("" + ch + ch)) return@l true
//        }
//        return@l false
//    }
//    val pred3 = zipWithNext().any {it.first == it.second}
    val pred3 = (0 until lastIndex).any { this[it] == this[it + 1] }

    return listOf(pred1, pred2, pred3).count { it } >= 2
}