package com.example.screenplay.question

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.questions.Text
import net.serenitybdd.screenplay.questions.Visibility
import net.serenitybdd.screenplay.targets.Target

class TooManyRequestsMessageIsVisible : QuestionWithDefaultSubject<Boolean>() {

  private val loginForm: Target = Target.the("login form").locatedBy("body form.form3")


  override fun answeredBy(actor: Actor): Boolean {
    return Visibility.of(loginForm).asBoolean().answeredBy(actor) && Text.of(loginForm).asString().answeredBy(actor)
      .contains("many requests")
  }

}
