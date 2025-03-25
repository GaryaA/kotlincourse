package org.example.module5

import org.example.module3.eq

fun fibonacci(): Sequence<Int> = sequence {
    var n1 = 0
    var n2 = 1
    yield(n1)
    yield(n2)
    while (true) {
        val tmp = n2
        n2 += n1
        n1 = tmp
        yield(n2)
    }
}

fun main(args: Array<String>) {
    fibonacci().take(4).toList().toString() eq "[0, 1, 1, 2]"

    fibonacci().take(10).toList().toString() eq "[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]"
}