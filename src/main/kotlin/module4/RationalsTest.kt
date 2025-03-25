package org.example.module4

import java.math.BigInteger

fun BigInteger.lcm(other: BigInteger): BigInteger = this / this.gcd(other) * other

fun main() {
    val d1: BigInteger = BigInteger.valueOf(24);
    val d2: BigInteger = BigInteger.valueOf(8);
    println(d2.gcd(d1))

    println(BigInteger.valueOf(-43).negate())

    println(BigInteger.valueOf(3).lcm(BigInteger.valueOf(9)))
}