package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.RememberThat
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.questions.TheMemory
import net.serenitybdd.screenplay.targets.Target
import org.hamcrest.CoreMatchers.*

open class InviteFriend : Performable {
  override fun <T : Actor> performAs(actor: T) {
    Thread.sleep(400)
    actor.attemptsTo(Click.on(".config_friend"))
    actor.attemptsTo(Click.on(".color-submits .black"))
    actor.attemptsTo(RememberThat.theValueOf("invite url").isAnsweredBy {
      Target.the("invite url").locatedBy("#challenge-id").resolveFor(actor).value
    })
    actor.should(seeThat(TheMemory.withKey("invite url").isPresent, `is`(true)))
  }

}
