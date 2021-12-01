package com.example.screenplay.action

import net.serenitybdd.core.pages.WebElementFacade
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.actions.Enter
import net.serenitybdd.screenplay.actions.Open
import net.serenitybdd.screenplay.conditions.Check
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*
import net.serenitybdd.screenplay.questions.WebElementQuestion
import net.serenitybdd.screenplay.targets.Target
import net.serenitybdd.screenplay.waits.Wait

open class LoginSuccessfully : Performable {
  private val loginWithMagicLink = Target
      .the("login via magic link")
      .locatedBy(".alternative a[href=/auth/magic-link]")

  override fun <T : Actor> performAs(actor: T) {
    actor.attemptsTo(Open.url("https://lichess.org/login"))
    actor.attemptsTo(FillAndSubmitLoginForm())
    Check.whether(WebElementQuestion.the(loginWithMagicLink), isPresent())
        .andIfSo(
            Click.on(loginWithMagicLink),
            Enter.theValue("beautiful-bread@65sg9qgw.mailosaur.net").into("#form3-email"),
            SolveCaptchaManually()
        )
  }
}

open class SolveCaptchaManually : Performable {
  override fun <T : Actor?> performAs(actor: T) {

    Wait.until(WebElementQuestion.the("h1.is-green"), isVisible<WebElementFacade>()).forNoLongerThan(60000).seconds()
  }

}
