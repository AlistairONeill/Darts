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

    val score : Score
    val throws: Int

    companion object
}