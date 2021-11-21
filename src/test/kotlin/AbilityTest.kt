import ability.BrowseTheWeb
import io.mockk.confirmVerified
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test
import org.openqa.selenium.WebDriver

class AbilityTest {


  @RelaxedMockK
  lateinit var mockAbility: Ability

  @SpyK
  var task = object : Task {
    override fun performAs(actor: Actor) {}
  }


  @SpyK
  var interaction = object : Interaction<Ability> {
    override fun performUsing(ability: Ability) {}
    override fun performAs(actor: Actor) {
      performUsing(actor.abilities.first())
    }
  }

  @Test
  fun `ability enables interaction`() {
    val actor = Actor(abilities = mutableListOf(mockAbility))
    actor.perform(interaction)
    verify(exactly = 1) { interaction.performUsing(mockAbility) }
    verify(exactly = 1) { interaction.performAs(actor) }
    confirmVerified(interaction)

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
    confirmVerified(interaction)
  }

  @RelaxedMockK
  lateinit var browser: WebDriver

  @Test
  internal fun `ability to browse with a webdriver`() {
    val actor = Actor()
    actor.can(BrowseTheWeb.with(browser))
    actor.perform(object : BrowserInteraction {
      override fun performUsing(ability: BrowseTheWeb) {
        ability.webDriver.navigate().to("page")
      }
    })
    verify(exactly = 1) { browser.navigate().to("page") }
    confirmVerified(browser)

  }
}
