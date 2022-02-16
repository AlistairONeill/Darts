package uk.co.alistaironeill.darts.domain.solver

import uk.co.alistaironeill.darts.domain.model.Out
import uk.co.alistaironeill.darts.domain.model.Score
import uk.co.alistaironeill.darts.domain.model.Throw.*

class ExhaustiveOutFinder : OutFinder {
    private val outs = getAll().groupBy { it.score }.mapValues { (_, outs) -> outs.toSet() }

    override fun findOuts(score: Score): Set<Out> = outs[score] ?: emptySet()

    private fun getAll(): List<Out> = getBase().bolster().bolster()

    private fun getBase(): List<Out> = Population.doubles.map(Out::Tail)

    private fun List<Out>.bolster(): List<Out> =
        this + flatMap { out -> Population.all.map { dart -> dart + out } }
}