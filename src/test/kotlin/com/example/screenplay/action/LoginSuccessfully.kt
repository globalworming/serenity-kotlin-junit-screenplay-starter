package com.example.screenplay.action

import com.example.screenplay.actor.Memory
import com.example.screenplay.question.CurrentAuthCookieValue
import com.example.screenplay.question.TheyAreLoggedIn
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.Performable

open class LoginSuccessfully : Performable {

  override fun <T : Actor> performAs(actor: T) {
    actor.attemptsTo(Login())
    actor.remember(Memory.AUTH_COOKIE_VALUE, CurrentAuthCookieValue())
    actor.should(seeThat<Boolean>(TheyAreLoggedIn()))
  }

}
