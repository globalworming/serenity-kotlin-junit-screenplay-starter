package com.example.screenplay.question

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*
import net.serenitybdd.screenplay.questions.WebElementQuestion
import net.serenitybdd.screenplay.targets.Target

class GuestIsOnline : QuestionWithDefaultSubject<Boolean>() {
  override fun answeredBy(actor: Actor): Boolean {
    val onlineIndicator = Target.the("opponents online status")
        .locatedBy(".ruser-top ruser.user-link.online")
    actor.should(seeThat(WebElementQuestion.the(onlineIndicator), isNotPresent()))
    return true
  }

}
