package com.example.screenplay.question

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.EventualConsequence.*
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*
import net.serenitybdd.screenplay.questions.WebElementQuestion
import net.serenitybdd.screenplay.targets.Target
import org.openqa.selenium.By

class GuestIsOnline : QuestionWithDefaultSubject<Boolean>() {
  override fun answeredBy(actor: Actor): Boolean {
    val onlineIndicator = Target.the("opponents online status")
        .located(By.cssSelector(".ruser-top.ruser.user-link.online"))
    actor.should(eventually(seeThat(WebElementQuestion.the(onlineIndicator), isPresent())))
    return true
  }

}
