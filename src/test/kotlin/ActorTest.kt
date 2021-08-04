import org.hamcrest.MatcherAssert.*
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test

class ActorTest {

  @Test
  internal fun `an actor exists`() {
    val actor = Actor()
    assertThat(actor, `is`(not(nullValue())))
  }


  @Test
  internal fun `actor has abilities`() {
    val actor = Actor()
    assertThat(actor.ability, `is`(not(nullValue())))
  }

  @Test
  internal fun `actor performs tasks`() {
    val actor = Actor()
    actor.perform(object : Performable {
      override fun perform() {}
    })
    assertThat(actor.hasPerformedTask, `is`(true))
  }
}