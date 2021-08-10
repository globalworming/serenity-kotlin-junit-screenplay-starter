import io.mockk.confirmVerified
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.hamcrest.MatcherAssert.*
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ScreenplayTest {


  @RelaxedMockK
  lateinit var mockQuestion: Question

  @RelaxedMockK
  lateinit var mockAbility: Ability

  @RelaxedMockK
  lateinit var mockTask: Task

  @SpyK
  var task = object : Task {
    override fun performAs(actor: Actor) {}
  }

  @SpyK
  var interaction = object : Interaction {
    override fun performUsingAbility(ability: Ability) {}
  }

  @Nested
  inner class ActorTest {
    @Test
    fun `an actor exists`() {
      val actor = Actor()
      assertThat(actor, `is`(not(nullValue())))
    }

    @Test
    fun `actor has ability`() {
      val actor = Actor(ability = mockAbility)
      assertThat(actor.ability, `is`(not(nullValue())))
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

      assertThat(answer, not(`is`(nullValue())))
      verify(exactly = 1) { mockQuestion.answerAs(actor) }
      confirmVerified(mockQuestion)
    }
  }

  @Nested
  inner class AbilityTest {
    @Test
    fun `ability enables interaction`() {
      val actor = Actor(ability = mockAbility)
      actor.perform(interaction)
      verify(exactly = 1) { interaction.performUsingAbility(mockAbility) }
    }

    @Test
    fun `no ability to interact will throw`() {
      val actor = Actor()
      assertThrows(UnsupportedOperationException::class.java) {
        actor.perform(interaction)
      }
    }

    @Test
    fun `tasks don't need abilities`() {
      val actor = Actor()
      actor.perform(task)
      verify(exactly = 1) { task.performAs(actor) }
    }
  }


}