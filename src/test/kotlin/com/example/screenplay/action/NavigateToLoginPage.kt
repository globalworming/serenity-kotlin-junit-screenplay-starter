package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.actions.Open

open class NavigateToLoginPage : Performable {
  override fun <T : Actor> performAs(actor: T) {
    actor.attemptsTo(Open.url("https://lichess.org/login"))
  }

}
