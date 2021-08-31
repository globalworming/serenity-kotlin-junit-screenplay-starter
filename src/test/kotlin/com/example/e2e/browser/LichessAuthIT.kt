package com.example.e2e.browser

import com.example.LichessBase
import com.example.screenplay.action.DeleteAllCookies
import com.example.screenplay.action.Login
import com.example.screenplay.action.LoginSuccessfully
import com.example.screenplay.action.LoginSuccessfullyUsingAuthCookie
import com.example.screenplay.action.NavigateToMagicMailLink
import com.example.screenplay.action.ReceiveMagicLinkEmail
import com.example.screenplay.actor.Memory
import com.example.screenplay.question.TheyAreLoggedIn
import com.example.screenplay.question.TooManyRequestsMessageIsVisible
import net.serenitybdd.junit5.SerenityJUnit5Extension
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.actions.Open
import net.thucydides.core.annotations.Narrative
import org.hamcrest.CoreMatchers.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.fail


@Narrative(
  text = ["lichess stores a session cookie.",
    "lichess has magic mail login.",
    "lichess has captcha.",
    "lichess has very accessible UI components.",
    "lichess has some interesting browser automation protection.", "have fun :)"]
)
@ExtendWith(SerenityJUnit5Extension::class)
class LichessAuthIT : LichessBase() {


  @Test
  fun `where too many logins from same IP triggers too-many-requests`() {
    host.attemptsTo(Open.url("https://lichess.org"))
    repeat(10) {
      host.attemptsTo(DeleteAllCookies())
      host.attemptsTo(Login())
      val tooManyRequests = host.asksFor(TooManyRequestsMessageIsVisible())
      if (tooManyRequests) {
        host.should(seeThat(TooManyRequestsMessageIsVisible(), `is`(true)))
        return
      }
    }
    fail()
  }

  @Test
  @Disabled
  fun `where we login via email magic link`() {
    givenThat(host).wasAbleTo(ReceiveMagicLinkEmail())
    host.attemptsTo(NavigateToMagicMailLink())
    host.should(seeThat(TheyAreLoggedIn(), `is`(true)))
  }


  @Test
  fun `where reusing browser session cookie skips login form`() {
    host.attemptsTo(LoginSuccessfully())
    guest.attemptsTo(
      LoginSuccessfullyUsingAuthCookie(host.recall(Memory.AUTH_COOKIE_VALUE))
    )
    guest.should(seeThat<Boolean>(TheyAreLoggedIn()))
  }

}