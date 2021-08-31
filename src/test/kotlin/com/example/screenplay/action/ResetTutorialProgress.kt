package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.actions.Open
import net.serenitybdd.screenplay.conditions.Check
import net.serenitybdd.screenplay.conditions.SilentPerformable
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers
import net.serenitybdd.screenplay.questions.WebElementQuestion
import net.serenitybdd.screenplay.targets.Target

open class ResetTutorialProgress : SilentPerformable() {

  private val resetProgress: Target =
    Target.the("reset progress in the sidebar").locatedBy(".learn__side-home .actions .confirm")


  override fun <T : Actor> performAs(actor: T) {
    actor.attemptsTo(Open.url("https://lichess.org/learn#/"))
    actor.attemptsTo(
      Check.whether(
        WebElementQuestion.the(resetProgress),
        WebElementStateMatchers.isPresent()
      ).andIfSo(Click.on(resetProgress))
    )
  }

}
