package com.example.e2e.browser

import com.example.LichessBase
import com.example.screenplay.action.DeleteAllCookies
import com.example.screenplay.action.Login
import com.example.screenplay.action.LoginSuccessfully
import com.example.screenplay.action.LoginSuccessfullyUsingAuthCookie
import com.example.screenplay.actor.Memory
import com.example.screenplay.question.TheyAreLoggedIn
import com.example.screenplay.question.TooManyRequestsMessageIsVisible
import net.serenitybdd.core.Serenity
import net.serenitybdd.junit.runners.SerenityRunner
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.actions.Open
import org.hamcrest.CoreMatchers.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(SerenityRunner::class)
class LichessAuthIT : LichessBase() {


  @Test
  fun `where too many logins from same IP triggers too-many-requests`() {
    Serenity.reportThat("login from the same IP multiple times until we get an error") {}
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
    Assert.fail()
  }


  @Test
  fun `where reusing browser session cookie skips login form`() {
    host.attemptsTo(LoginSuccessfully())
    guest.attemptsTo(
        LoginSuccessfullyUsingAuthCookie(host.recall(Memory.AUTH_COOKIE_VALUE)))
    guest.should(seeThat<Boolean>(TheyAreLoggedIn()))
  }

}