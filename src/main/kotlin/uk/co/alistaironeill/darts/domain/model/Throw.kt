package uk.co.alistaironeill.darts.domain.model

sealed class Throw(private val multiplier: Int) {
    protected abstract val value: Int
    val score by lazy { Score(value * multiplier) }

    data class Single(override val value: Int) : Throw(1)
    data class Dub(override val value: Int) : Throw(2)
    data class Trip(override val value: Int) : Throw(3)

    operator fun plus(other: List<Throw>) = listOf(this) + other
    operator fun plus(out: Out) = Out.Link(this, out)

    companion object {
        fun has(score: Score): (Throw) -> Boolean = { it.score == score }
    }

    object Population {
        private val sectors = (1 .. 20).toList()

        private val singles = sectors.map(::Single)
        val doubles : List<Dub> = sectors.map(::Dub)
        private val triples = sectors.map(::Trip)

        val all : List<Throw> = singles + doubles + triples
    }
}