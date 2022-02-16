package uk.co.alistaironeill.darts.domain.model

import uk.co.alistaironeill.darts.domain.model.Throw.Dub

data class Out(
    val precursors : List<Throw>,
    val final: Dub
) {
    constructor(final: Dub) : this(emptyList(), final)
    constructor(first: Throw, final: Dub) : this(listOf(first), final)
    constructor(first: Throw, second: Throw, final: Dub) : this(listOf(first, second), final)

    val score : Score by lazy { Score(precursors.map(Throw::score).sumOf(Score::value) + final.score.value) }
}