package com.example.screenplay.action.presentation

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.actions.Open

open class ShowRalph : Performable {
  override fun <T : Actor> performAs(actor: T) {
    actor.attemptsTo(Open.url("https://giphy.com/gifs/the-simpsons-hello-hi-ASd0Ukj0y3qMM/fullscreen"))
  }

}
