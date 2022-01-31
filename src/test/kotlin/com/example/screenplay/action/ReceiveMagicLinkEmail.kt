package com.example.screenplay.action

import com.example.screenplay.question.MagicMailArrived
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.EventualConsequence.*
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*
import net.serenitybdd.screenplay.questions.WebElementQuestion.*
import net.serenitybdd.screenplay.targets.Target
import org.hamcrest.CoreMatchers.*

open class ReceiveMagicLinkEmail : Performable {

  val successMessage = Target.the("heading with green tick")
      .locatedBy("body h1.is-green")

  override fun <T : Actor> performAs(actor: T) {
    actor.attemptsTo(NavigateToLoginPage())
    actor.attemptsTo(Click.on("a[href\$='magic-link']"))
    actor.attemptsTo(FillRequestMagicLinkForm())
    actor.should(eventually(seeThat(the(successMessage), isVisible())))
    actor.should(seeThat(MagicMailArrived(), `is`(true)))
  }

}
