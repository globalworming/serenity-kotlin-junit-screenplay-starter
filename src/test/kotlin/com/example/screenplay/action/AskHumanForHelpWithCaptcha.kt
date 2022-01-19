package com.example.screenplay.action

import com.example.screenplay.question.CaptchaIsSolved
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.EventualConsequence.*
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import org.hamcrest.CoreMatchers.*
import org.openqa.selenium.JavascriptExecutor

open class AskHumanForHelpWithCaptcha : Performable {
  override fun <T : Actor> performAs(actor: T) {
    val driver = BrowseTheWeb.`as`(actor).driver as JavascriptExecutor
    driver.executeScript("alert('hey human, \\ncould you please solve the captcha')")
    actor.should(eventually(seeThat(CaptchaIsSolved(), `is`(true)))
        .waitingForNoLongerThan(30).seconds())
  }

}
