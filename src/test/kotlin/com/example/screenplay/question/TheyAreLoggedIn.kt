package com.example.screenplay.question

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*
import net.serenitybdd.screenplay.questions.WebElementQuestion.*

class TheyAreLoggedIn : QuestionWithDefaultSubject<Boolean>() {
  override fun answeredBy(actor: Actor): Boolean {
    actor.should(seeThat(the(".dasher #user_tag"), isVisible()))
    return true
  }

}
