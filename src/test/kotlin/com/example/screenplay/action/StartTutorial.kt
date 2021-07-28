package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.actions.Open

open class StartTutorial : Performable {
  override fun <T : Actor> performAs(actor: T) {
    actor.attemptsTo(Open.url("https://lichess.org/login"))
    Thread.sleep(2000)
    actor.attemptsTo(LoginSuccessfully())
    actor.attemptsTo(ResetTutorialProgress())
    actor.attemptsTo(Open.url("https://lichess.org/learn#/1"))
    actor.attemptsTo(Click.on(".next"))


  }

}
