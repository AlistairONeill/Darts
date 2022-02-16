package uk.co.alistaironeill.darts.domain.model

operator fun Out.Companion.invoke(final: Throw.Dub) = Out.Tail(final)
operator fun Out.Companion.invoke(first: Throw, final: Throw.Dub) = Out.Link(first, Out.Tail(final))
operator fun Out.Companion.invoke(first: Throw, second: Throw, final: Throw.Dub) = Out.Link(first, Out.Link(second, Out.Tail(final)))