package com.example.screenplay.action

import net.serenitybdd.core.pages.WebElementFacade
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.actions.Enter
import net.serenitybdd.screenplay.conditions.Check
import net.serenitybdd.screenplay.conditions.SilentPerformable
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*
import net.serenitybdd.screenplay.questions.WebElementQuestion
import net.serenitybdd.screenplay.targets.Target
import net.serenitybdd.screenplay.waits.Wait
import net.thucydides.core.util.SystemEnvironmentVariables
import org.junit.Assume
import org.openqa.selenium.Keys

open class LoginSuccessfully : SilentPerformable() {
  private val loginWithMagicLink = Target
      .the("login via magic link")
      .locatedBy(".alternative a[href=/auth/magic-link]")

  override fun <T : Actor> performAs(actor: T) {
    val env = SystemEnvironmentVariables.createEnvironmentVariables()
    val username = env.getValue("USERNAME")
    val password = env.getValue("PASSWORD")
    Assume.assumeTrue("you will need an lichess.org account to run these", username != null)
    Assume.assumeTrue("you will need an lichess.org account to run these", password != null)
    actor.attemptsTo(Enter.theValue(username).into("#form3-username"))
    actor.attemptsTo(Enter.theValue(password).into("#form3-password").thenHit(Keys.ENTER))
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
