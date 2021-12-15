package com.example.screenplay.action

import com.example.screenplay.question.TheyAreLoggedIn
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.actions.Open

open class LoginSuccessfullyUsingAuthCookie(val cookieValue: String) : Performable {

  override fun <T : Actor> performAs(actor: T) {
    actor.attemptsTo(Open.url("https://lichess.org/"))
    actor.attemptsTo(DeleteAllCookies())
    actor.attemptsTo(AddSessionCookie(cookieValue))
    actor.attemptsTo(Open.url("https://lichess.org/"))
    actor.should(seeThat<Boolean>(TheyAreLoggedIn()))
  }

}
