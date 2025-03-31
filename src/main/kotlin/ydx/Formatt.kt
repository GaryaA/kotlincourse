package org.example.ydx

fun main() {
    val n: Int = readln().toInt()
    var s: String = readln()
    val q: Int = readln().toInt()
    val list: MutableList<Operation> = mutableListOf()
    repeat(q) {
        val (t, x, c) = readln().split(" ")
        list.add(Operation(t.toInt(), x.toInt(), c[0]))
    }

    list.withIndex()
        .associate { (idx, op) ->
            Pair((if (op.t == 2 || op.t == 3) 0 else 1), op.x) to
                    Pair(idx, Operation(op.t, op.x, op.c))
        }
        .values
        .sortedBy { it.first }
        .forEach { (_, v) ->
            s = when (v.t) {
                1 -> s.op1(v.x, v.c)
                2 -> s.op2()
                3 -> s.op3()
                else -> throw IllegalArgumentException("t must be in [1,3]")
            }
        }
    println(s);
}

fun String.op1(x: Int, c: Char): String = replaceRange(x - 1, x, c.toString())

fun String.op2(): String = lowercase()

fun String.op3(): String = uppercase()

data class Operation(val t: Int, val x: Int, val c: Char)
