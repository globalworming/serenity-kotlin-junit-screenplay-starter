package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.GivenWhenThen
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers
import net.serenitybdd.screenplay.questions.WebElementQuestion

open class EnsureTutorial1IsDone : Performable {
  override fun <T : Actor> performAs(actor: T) {
    actor.should(GivenWhenThen.seeThat(
        "tutorial 1 marked done",
        WebElementQuestion.the(".progress .done"), WebElementStateMatchers.isVisible()))
  }

}
