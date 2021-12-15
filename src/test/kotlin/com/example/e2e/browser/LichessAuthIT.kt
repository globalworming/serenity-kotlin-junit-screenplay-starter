package com.example.e2e.browser

import com.example.LichessBase
import com.example.screenplay.action.LoginSuccessfully
import com.example.screenplay.action.LoginSuccessfullyUsingAuthCookie
import com.example.screenplay.actor.Memory
import com.example.screenplay.question.TheyAreLoggedIn
import net.serenitybdd.junit.runners.SerenityRunner
import net.serenitybdd.screenplay.GivenWhenThen.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(SerenityRunner::class)
class LichessAuthIT : LichessBase() {


  // @Test
  // fun `when we trigger captcha`() {
  //   val cast = OnlineCast()
  //   repeat(10) {
  //     val randomActor = randomActor(cast)
  //     randomActor.attemptsTo(LoginSuccessfully())
  //     val actorNeedsToSolveCaptcha = ActorNeedsToSolveCaptcha()
  //     val currentValue = actorNeedsToSolveCaptcha.answeredBy(randomActor)
  //     randomActor.attemptsTo(Check.whether(actorNeedsToSolveCaptcha).andIfSo(throw CaptchaBlocksProgressionError()))
  //   }
  // }

  @Test
  fun `where reusing browser session cookie skips login form`() {
    host.attemptsTo(LoginSuccessfully())
    guest.attemptsTo(
        LoginSuccessfullyUsingAuthCookie(host.recall(Memory.AUTH_COOKIE_VALUE)))
    guest.should(seeThat<Boolean>(TheyAreLoggedIn()))
  }

}