package uk.co.alistaironeill.darts.domain.solver

import uk.co.alistaironeill.darts.domain.model.Out
import uk.co.alistaironeill.darts.domain.model.Score
import uk.co.alistaironeill.darts.domain.model.Throw.*
import uk.co.alistaironeill.darts.domain.model.Throw.Companion.has

class RecursiveOutFinder : OutFinder {
    override fun findOuts(score: Score): Set<Out> = findOuts(score, 3)

    private fun findOutsCached(score: Score, remaining: Int): Set<Out> =
        cache[score to remaining]
            ?: findOuts(score, remaining)
                .also { cache[score to remaining] = it }

    private fun findOuts(score: Score, remaining: Int): Set<Out> =
        if (remaining < 1) emptySet()
        else singleDartOut(score) +
                Population.all.flatMap { dart ->
                    findOutsCached(score - dart.score, remaining - 1)
                        .map { out -> dart + out }
                }.filter { it.score <= score }

    private fun singleDartOut(score: Score): Out? = Population.doubles.singleOrNull(has(score))?.let(Out::Tail)

    private operator fun Out?.plus(other: List<Out>): Set<Out> =
        (this?.let { listOf(it) + other } ?: other).toSet()

    private val cache = HashMap<Pair<Score, Int>, Set<Out>>()
}