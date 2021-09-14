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

  /**
   * A relaxed mock is the mock that returns some simple value for all functions. This allows you to skip specifying behavior for each case, while still stubbing things you need. For reference types, chained mocks are returned.
   */
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
  var interaction = object : Interaction<Ability> {
    override fun performUsing(interfaze: Ability) {}
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
      val actor = Actor(abilities = mutableListOf(mockAbility))
      assertThat(actor.abilities[0], `is`(not(nullValue())))
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
      val actor = Actor(abilities = mutableListOf(mockAbility))
      actor.perform(interaction)
      verify(exactly = 1) { interaction.performUsing(mockAbility) }
    }

    @Test
    fun `no ability to interact will throw`() {
      val actor = Actor()
      assertThrows(NoSuchElementException::class.java) {
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

  @Nested
  inner class TaskTest {

    @SpyK
    var task = object : Task {
      override fun performAs(actor: Actor) {
        actor.perform(subTask)
      }
    }

    @SpyK
    var subTask = object : Task {
      override fun performAs(actor: Actor) {
        actor.perform(interaction)
      }
    }

    @SpyK
    var interaction = object : Interaction<Any> {
      override fun performUsing(interfaze: Any) {
      }
    }

    @SpyK
    var listInteraction = object : Interaction<InteractWithList> {
      override fun performUsing(interfaze: InteractWithList) {
        interfaze.list.add("")
      }
    }

    inner class InteractWithList(val list: MutableList<String>) : Ability


    @Test
    fun `task are performed recursively`() {
      val actor = Actor(abilities = mutableListOf(mockAbility))
      actor.perform(task)
      verify(exactly = 1) { interaction.performUsing(mockAbility) }
    }

    @Test
    fun `you can specify which abilities to use`() {
      val list = mutableListOf<String>()
      val actor = Actor(abilities = mutableListOf(InteractWithList(list)))
      actor.perform(listInteraction)
      assertThat(list.first(), `is`(""))
    }


  }


}