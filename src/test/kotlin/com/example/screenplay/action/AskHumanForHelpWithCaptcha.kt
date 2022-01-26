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
  override fun <T : Actor> performAs(bot: T) {
    alertHuman(bot)
    bot.should(eventually(seeThat(CaptchaIsSolved(), `is`(true)))
        .waitingForNoLongerThan(600).seconds())
  }


  private fun <T : Actor> alertHuman(bot: T) {
    val driver = BrowseTheWeb.`as`(bot)
        .driver as JavascriptExecutor
    driver.executeScript("alert('Hey human, would you please be so kind to solve the captcha for me? That\\'s really hard for me to do, all these samey looking pictures.. urgh.\\n\\nI would suggest turning off captchas for browser automation.\\n\\nYours truly\\nBOT')")
  }

}
