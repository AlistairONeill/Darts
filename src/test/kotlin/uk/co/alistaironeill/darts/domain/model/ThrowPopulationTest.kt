package uk.co.alistaironeill.darts.domain.model

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.hasSize
import strikt.assertions.isEqualTo
import uk.co.alistaironeill.darts.domain.model.Throw.*

class ThrowPopulationTest {

    private inline fun <reified T: Throw> List<Throw>.checkInPopulation(multiplier: Int) =
        filterIsInstance<T>()
            .map(Throw::score)
            .map(Score::value)
            .let(::expectThat)
            .isEqualTo(
                (1 .. 20).map { it * multiplier }
            )

    @Test
    fun `all values are in full population`() {
        Population.all.checkInPopulation<Single>(1)
        Population.all.checkInPopulation<Dub>(2)
        Population.all.checkInPopulation<Trip>(3)
        expectThat(Population.all).hasSize(60)
    }

    @Test
    fun `all doubles are in double population`() {
        Population.doubles.checkInPopulation<Dub>(2)
        expectThat(Population.doubles).hasSize(20)
    }
}