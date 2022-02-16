package uk.co.alistaironeill.darts.domain.solver

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.hasSize
import uk.co.alistaironeill.darts.domain.model.Score

class OutFinderConsistencyTest {
    private val outFinders = listOf(
        ExhaustiveOutFinder(),
        RecursiveOutFinder()
    )

    @Test
    fun `try to find differences`() =
        (-1 .. 181)
            .map(::Score)
            .forEach { score ->
                outFinders.map { finder -> finder.findOuts(score) }
                    .toSet()
                    .let(::expectThat)
                    .hasSize(1)
            }
}