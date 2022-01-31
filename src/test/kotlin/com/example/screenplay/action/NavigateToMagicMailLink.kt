package com.example.screenplay.action

import com.example.screenplay.question.LatestMessagesFirstLink
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.actions.Open

open class NavigateToMagicMailLink : Performable {
  override fun <T : Actor> performAs(actor: T) {
    val url = actor.asksFor(LatestMessagesFirstLink())
    actor.attemptsTo(Open.url(url))
  }

}
