package uk.co.alistaironeill.darts.domain.model

data class Score(val value: Int) {
    operator fun plus(other: Score) = Score(value + other.value)
    operator fun minus(other: Score) = Score(value - other.value)
    operator fun compareTo(other: Score) = value.compareTo(other.value)
}

fun Collection<Score>.sum() : Score = sumOf(Score::value).let(::Score)