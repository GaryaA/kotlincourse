package org.example.module4

import java.util.Random

val foo: Int
    get() = Random().nextInt()

fun main(args: Array<String>) {
    // The values should be different:
    println(foo)
    println(foo)
    println(foo)
}