package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable

open class Login : Performable {
  override fun <T : Actor> performAs(actor: T) {
    actor.attemptsTo(NavigateToLoginPage())
    actor.attemptsTo(FillAndSubmitLoginForm())
  }

}
