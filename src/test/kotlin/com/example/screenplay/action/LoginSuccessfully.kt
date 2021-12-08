package com.example.screenplay.action

import com.example.LichessBase
import net.serenitybdd.core.pages.WebElementFacade
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.EventualConsequence.*
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import net.serenitybdd.screenplay.actions.Open
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*
import net.serenitybdd.screenplay.questions.WebElementQuestion
import net.serenitybdd.screenplay.targets.Target
import net.serenitybdd.screenplay.waits.Wait
import org.hamcrest.CoreMatchers.*
import org.openqa.selenium.Cookie
import java.time.Instant
import java.util.*

open class LoginSuccessfully : Performable {
  private val loginWithMagicLink = Target
      .the("login via magic link")
      .locatedBy(".alternative a[href=/auth/magic-link]")

  override fun <T : Actor> performAs(actor: T) {
    actor.attemptsTo(Open.url("https://lichess.org/login"))
    val driver = BrowseTheWeb.`as`(actor).driver
    if (LichessBase.authCookie != null) {
      driver.manage().addCookie(Cookie("lila2", LichessBase.authCookie, ".lichess.org", "/", Date.from(Instant.now().plusSeconds(600)), true, true))
      actor.attemptsTo(Open.url("https://lichess.org/"))
    } else {
      actor.attemptsTo(FillAndSubmitLoginForm())
    }
    driver.manage().deleteAllCookies()
    actor.should(eventually(seeThat("browser auth cookie lila2 value", { driver.manage().cookies.first { it.name == "lila2" } }, notNullValue())))
    LichessBase.authCookie = driver.manage().cookies.first { it.name == "lila2" }.value
    //Check.whether(WebElementQuestion.the(loginWithMagicLink), isPresent())
    //    .andIfSo(
    //        Click.on(loginWithMagicLink),
    //        Enter.theValue("beautiful-bread@65sg9qgw.mailosaur.net").into("#form3-email"),
    //        SolveCaptchaManually()
    //    )
  }
}

open class SolveCaptchaManually : Performable {
  override fun <T : Actor?> performAs(actor: T) {

    Wait.until(WebElementQuestion.the("h1.is-green"), isVisible<WebElementFacade>()).forNoLongerThan(60000).seconds()
  }

}
