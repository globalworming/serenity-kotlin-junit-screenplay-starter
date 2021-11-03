package com.example.screenplay.action.presentation

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable

open class GreetTheAudience : Performable {
  override fun <T : Actor> performAs(actor: T) {
    actor.attemptsTo(ShowRalph())
  }

}
