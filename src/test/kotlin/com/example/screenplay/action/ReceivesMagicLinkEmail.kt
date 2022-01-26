package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.EventualConsequence.*
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.actions.Enter
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers
import net.serenitybdd.screenplay.questions.WebElementQuestion

open class ReceivesMagicLinkEmail : Performable {
  override fun <T : Actor> performAs(actor: T) {
    actor.attemptsTo(NavigateToLoginPage())
    actor.attemptsTo(Click.on("a[href\$='magic-link']"))
    actor.attemptsTo(Enter.theValue("pleasant-warn@$serverId.mailosaur.net").into("#form3-email"))
    actor.attemptsTo(AskHumanForHelpWithCaptcha())
    actor.attemptsTo(Click.on("form[action\$=\"magic-link/send\"] button[type=\"submit\"]"))
    actor.should(eventually(seeThat(WebElementQuestion.the("h1.is-green"), WebElementStateMatchers.isVisible())))
  }

}
