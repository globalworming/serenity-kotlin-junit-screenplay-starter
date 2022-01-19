package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.actions.Enter

open class ReceivesMagicLinkEmail : Performable {
  override fun <T : Actor> performAs(actor: T) {
    actor.attemptsTo(NavigateToLoginPage())
    actor.attemptsTo(Click.on("a[href\$='magic-link']"))
    actor.attemptsTo(Enter.theValue("pleasant-warn@thktud08.mailosaur.net").into("#form3-email"))
    actor.attemptsTo(AskHumanForHelpWithCaptcha())
    actor.attemptsTo(Click.on("button[type=\"submit\"]"))
  }

}
