package uk.co.alistaironeill.darts.domain.solver

import uk.co.alistaironeill.darts.domain.model.Out
import uk.co.alistaironeill.darts.domain.model.Score

interface OutFinder {
    fun findOuts(score: Score) : Set<Out>
}