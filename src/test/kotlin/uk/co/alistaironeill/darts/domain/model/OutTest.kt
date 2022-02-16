package uk.co.alistaironeill.darts.domain.model

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import uk.co.alistaironeill.darts.domain.model.Throw.Dub
import uk.co.alistaironeill.darts.domain.model.Throw.Trip

class OutTest {
    @Test
    fun `score for an out is calculated correctly`() {
        expectThat(
            Out(Dub(2), Trip(3), Dub(4))
        ) {
            get { score }.isEqualTo(Score(21))
        }
    }
}