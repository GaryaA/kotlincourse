package org.example.module2

fun isValidIdentifier(s: String): Boolean = s.isNotEmpty()
        && (s.first().isLetter() || s.first() == '_')
        && s.all { c -> c.isLetter() || c == '_' || c.isDigit() }


fun main(args: Array<String>) {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}

