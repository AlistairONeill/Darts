package uk.co.alistaironeill.darts.domain.model

import uk.co.alistaironeill.darts.domain.model.Throw.Dub


sealed interface Out {
    data class Link(val dart: Throw, val next: Out): Out {
        override val score = dart.score + next.score
        override val throws = 1 + next.throws
    }

    data class Tail(val dart: Dub): Out {
        override val score = dart.score
        override val throws = 1
    }

    companion object {
        operator fun invoke(final: Dub) = Tail(final)
        operator fun invoke(first: Throw, final: Dub) = Link(first, Tail(final))
        operator fun invoke(first: Throw, second: Throw, final: Dub) = Link(first, Link(second, Tail(final)))
    }

    val score : Score
    val throws: Int
}