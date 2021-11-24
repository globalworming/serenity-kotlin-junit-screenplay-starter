package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.Tasks.*
import net.serenitybdd.screenplay.actions.Open

open class AcceptsInvitation(val url: String) : Performable {
  override fun <T : Actor> performAs(actor: T) {
    actor.attemptsTo(Open.url(url))
  }

  companion object {
    fun forGame(url: String): Performable {
      return instrumented(AcceptsInvitation::class.java, url)
    }

  }
}
