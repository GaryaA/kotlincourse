package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {

    val rightPositions = secret.zip(guess).count { it.first == it.second }

    val commonLetters = "ABCDEF".sumOf { ch ->

        Math.min(secret.count { it == ch }, guess.count { it == ch })
    }
    return Evaluation(rightPositions, commonLetters - rightPositions)
}

//fun evaluateGuess(secret: String, guess: String): Evaluation {
//    var rightPosition = 0;
//    var wrongPosition = 0;
//
//    val mapSecretChars = HashMap<Char, Int>()
//
//    for ((idx, ch) in secret.withIndex()) {
//        if(ch == guess[idx]) {
//            rightPosition++
//            continue
//        }
//        mapSecretChars[ch] = mapSecretChars.getOrDefault(ch, 0) + 1
//    }
//
//    for ((idx, ch) in guess.withIndex()) {
//        if (!mapSecretChars.contains(ch) || ch == secret[idx]) continue
//
//        wrongPosition++
//
//        if (mapSecretChars[ch] == 1) mapSecretChars.remove(ch)
//        else mapSecretChars[ch] = mapSecretChars.getValue(ch) - 1
//    }
//    return Evaluation(rightPosition, wrongPosition)
//}
