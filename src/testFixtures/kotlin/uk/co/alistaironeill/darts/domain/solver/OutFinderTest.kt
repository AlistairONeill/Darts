package uk.co.alistaironeill.darts.domain.solver

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.single
import uk.co.alistaironeill.darts.domain.model.Out
import uk.co.alistaironeill.darts.domain.model.Score
import uk.co.alistaironeill.darts.domain.model.Throw.*

@Suppress("FunctionName")
abstract class OutFinderTest(private val outFinder: OutFinder) {

    private infix fun Int.hasOnly(out: Out) =
        let(::Score)
            .let(outFinder::findOuts)
            .let(::expectThat)
            .single()
            .isEqualTo(out)

    private infix fun Int.hasOuts(outs: Set<Out>) =
        let(::Score)
            .let(outFinder::findOuts)
            .let(::expectThat)
            .isEqualTo(outs)

    @Test
    fun `can find single out solutions`() {
        2 hasOnly Out(Dub(1))
        3 hasOnly Out(Single(1), Dub(1))
        160 hasOnly Out(Trip(20), Trip(20), Dub(20))
    }

    @Test
    fun `can handle zero out solutions`() {
        -1 hasOuts emptySet()
        0 hasOuts emptySet()
        1 hasOuts emptySet()
        159 hasOuts emptySet()
        161 hasOuts emptySet()
    }

    @Test
    fun `can find multiple out solutions`() {
        4 hasOuts setOf(
            Out(Dub(2)),
            Out(Single(1), Single(1), Dub(1)),
            Out(Single(2), Dub(1)),
            Out(Dub(1), Dub(1))
        )
    }
}