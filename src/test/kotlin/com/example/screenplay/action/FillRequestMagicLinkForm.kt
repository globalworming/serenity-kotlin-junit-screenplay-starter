package com.example.screenplay.action

import com.example.screenplay.actor.Memory
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.actions.Enter

open class FillRequestMagicLinkForm : Performable {
  override fun <T : Actor> performAs(actor: T) {
    val serverId = actor.recall<String>(Memory.MAILOSAUR_SERVER_ID)
    actor.attemptsTo(Enter.theValue("energy-eat@$serverId.mailosaur.net").into("#form3-email"))
    actor.attemptsTo(AskHumanForHelpWithCaptcha())
    actor.attemptsTo(Click.on("form[action\$=\"magic-link/send\"] button[type=\"submit\"]"))
  }

}
