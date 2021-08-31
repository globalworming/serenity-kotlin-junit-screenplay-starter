package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.Tasks.*
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.actions.Open
import net.serenitybdd.screenplay.targets.Target


open class AcceptsInvitation(val url: String) : Performable {
  private val acceptChallenge: Target = Target.the("accept challenge button").locatedBy(".challenge-page button")

  override fun <T : Actor> performAs(actor: T) {
    actor.attemptsTo(Open.url(url), Click.on(acceptChallenge))

  }

  companion object {
    fun forGame(url: String): Performable {
      return instrumented(AcceptsInvitation::class.java, url)
    }

  }
}
