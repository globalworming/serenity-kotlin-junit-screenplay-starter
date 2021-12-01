package com.example.screenplay.action

import net.serenitybdd.core.Serenity
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.RememberThat
import net.serenitybdd.screenplay.Tasks.*
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.targets.Target

open class InviteFriend : Performable {

  private val pickBlackSide = Target.the("'pick black side' button").locatedBy(".color-submits .black")
  private val setupGameForFriend = Target.the("'Play with a friend' button").locatedBy(".config_friend")

  override fun <T : Actor> performAs(actor: T) {
    Thread.sleep(1000)
    actor.attemptsTo(Click.on(setupGameForFriend))
    actor.attemptsTo(Click.on(pickBlackSide))
    Serenity.reportThat("remember invite url") {
      val inviteUrl = Target.the("invite url").locatedBy("#challenge-id").resolveFor(actor).value
      Serenity.recordReportData().withTitle("invite url").andContents(inviteUrl)
      actor.attemptsTo(RememberThat.theValueOf("invite url").isAnsweredBy {
        inviteUrl
      })
    }
  }

  companion object {
    fun toGame(): InviteFriend = instrumented(InviteFriend::class.java)
  }

}
