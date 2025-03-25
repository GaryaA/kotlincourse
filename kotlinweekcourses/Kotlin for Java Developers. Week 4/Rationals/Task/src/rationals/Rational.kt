package rationals

import java.math.BigInteger

class Rational(numerator: BigInteger, denominator: BigInteger) : Comparable<Rational> {

    val n: BigInteger
    val d: BigInteger

    init {
        require(denominator != BigInteger.ZERO) { "The d must be non-zero" }
        val gcd = numerator.gcd(denominator)
        val sign = denominator.signum().toBigInteger()
        n = numerator / gcd * sign
        d = denominator / gcd * sign
    }

    operator fun plus(other: Rational): Rational {
        val lcm = d.lcm(other.d)
        return Rational(
            n * (lcm / d) + other.n * (lcm / other.d),
            lcm
        )
    }

    operator fun minus(other: Rational): Rational {
        val lcm = d.lcm(other.d)
        return Rational(
            n * (lcm / d) - other.n * (lcm / other.d),
            lcm
        )
    }

    operator fun unaryMinus(): Rational = Rational(-n, d)

    operator fun times(other: Rational): Rational = Rational(
        n * other.n,
        d * other.d
    )

    operator fun div(other: Rational): Rational = Rational(
        n * other.d,
        d * other.n
    )

    override operator fun compareTo(other: Rational): Int {
        val lcm = d.lcm(other.d)
        val nLcm = n * (lcm / d)
        val othernLcm = other.n * (lcm / other.d)
        return nLcm.compareTo(othernLcm)
    }

    override fun toString(): String {
        if (d == BigInteger.ONE) return n.toString()
        return "$n/$d"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rational

        if (n != other.n) return false
        if (d != other.d) return false

        return true
    }

    override fun hashCode(): Int {
        var result = n.hashCode()
        result = 31 * result + d.hashCode()
        return result
    }
}

fun BigInteger.lcm(other: BigInteger): BigInteger = this / this.gcd(other) * other

infix fun Number.divBy(other: Number): Rational {
    checkDivByOperand(this)
    checkDivByOperand(other)
    return Rational(BigInteger("" + this), BigInteger("" + other))
}

private fun checkDivByOperand(arg: Number) {
    if (arg !is Int && arg !is Long && arg !is BigInteger)
        throw IllegalArgumentException("The argument must be of type Int, Long, BigInteger")
}

fun String.toRational(): Rational {
    val ratStr = this.replace("\\s", "")
    if (!this.contains("/")) return Rational(ratStr.toBigInteger(), BigInteger.ONE)
    val (n, d) = ratStr.split("/")
    return Rational(n.toBigInteger(), d.toBigInteger())
}

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println(
        "912016490186296920119201192141970416029".toBigInteger() divBy
                "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2
    )
}