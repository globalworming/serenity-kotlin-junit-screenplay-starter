import io.mockk.confirmVerified
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class ActorTest {

  @RelaxedMockK
  lateinit var mockQuestion: Question

  @RelaxedMockK
  lateinit var mockAbility: Ability

  @RelaxedMockK
  lateinit var mockTask: Task

  @Test
  fun `an actor exists`() {
    val actor = Actor()
    MatcherAssert.assertThat(actor, Matchers.`is`(Matchers.not(Matchers.nullValue())))
  }

  @Test
  fun `actor has ability`() {
    val actor = Actor(abilities = mutableListOf(mockAbility))
    MatcherAssert.assertThat(actor.abilities[0], Matchers.`is`(Matchers.not(Matchers.nullValue())))
  }

  @Test
  fun `actor performs tasks`() {
    val actor = Actor()
    actor.perform(mockTask)
    verify(exactly = 1) { mockTask.performAs(actor) }
    confirmVerified(mockTask)
  }

  @Test
  fun `actor asks question`() {
    val actor = Actor()
    val answer = actor.asks(mockQuestion)

    MatcherAssert.assertThat(answer, Matchers.not(Matchers.`is`(Matchers.nullValue())))
    verify(exactly = 1) { mockQuestion.answerAs(actor) }
    confirmVerified(mockQuestion)
  }
}
