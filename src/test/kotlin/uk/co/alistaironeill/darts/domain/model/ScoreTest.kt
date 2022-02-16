package uk.co.alistaironeill.darts.domain.model

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class ScoreTest {
    @Test
    fun `can sum a list of scores`() {
        val scores = (0 .. 10).map(::Score)

        expectThat(scores) {
            get { sum() }.isEqualTo(Score(55))
        }
    }

    @Test
    fun `can inline add scores`() {
        expectThat(
            Score(5) + Score(3)
        ).isEqualTo(Score(8))
    }

    @Test
    fun `can inline subtract scores`() {
        expectThat(
            Score(5) - Score(3)
        ).isEqualTo(Score(2))
    }
}