package com.example.screenplay.question

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.questions.Text
import net.serenitybdd.screenplay.questions.Visibility
import net.serenitybdd.screenplay.targets.Target

class TooManyRequestsMessageIsVisible : QuestionWithDefaultSubject<Boolean>() {

  val loginForm = Target.the("login form").locatedBy("body form.form3")


  override fun answeredBy(actor: Actor): Boolean {
    return Visibility.of(loginForm).viewedBy(actor).asBoolean() && Text.of(loginForm).viewedBy(actor).asString().contains("many requests")
  }

}
