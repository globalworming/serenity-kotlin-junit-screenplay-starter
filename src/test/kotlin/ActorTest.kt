import org.hamcrest.MatcherAssert.*
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test

class ActorTest {

  @Test
  internal fun `an actor exists`() {
    val actor = Actor(canAnswerQuestions = false)
    assertThat(actor, `is`(not(nullValue())))
  }


  @Test
  internal fun `actor has abilities`() {
    val actor = Actor(canAnswerQuestions = false)
    assertThat(actor.ability, `is`(not(nullValue())))
  }

  @Test
  internal fun `actor performs tasks`() {
    val actor = Actor(canAnswerQuestions = false)
    actor.perform(object : Performable {
      override fun perform() {}
    })
    assertThat(actor.hasPerformedTask, `is`(true))
  }

  @Test
  internal fun `actor asks question`() {
    val actor = Actor(canAnswerQuestions = false)
    val answer = actor.asks(object : Question {
      override fun answer(): Any {
        return true
      }

      override fun answerAs(actor: Actor): Any {
        return true
      }
    })

    assertThat(answer, `is`(true))
    assertThat(actor.hasAnswerdedQuestion, `is`(true))
  }


  @Test
  internal fun `abilities enables questions`() {
    val actor = Actor(canAnswerQuestions = true)
    val question = object : Question {
      override fun answer(): Any {
        return true
      }

      override fun answerAs(actor: Actor): Any {
        return if (!actor.canAnswerQuestions) throw UnsupportedOperationException() else {
          actor.hasAnswerdedQuestion = true
          true
        }
      }
    }
    val answer = question.answerAs(actor)


    assertThat(answer, `is`(true))
    assertThat(actor.hasAnswerdedQuestion, `is`(true))
  }
}