package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.actions.Enter
import net.serenitybdd.screenplay.conditions.SilentPerformable
import net.thucydides.core.util.SystemEnvironmentVariables
import org.openqa.selenium.Keys

open class LoginSuccessfully : SilentPerformable() {
  override fun <T : Actor> performAs(actor: T) {
    actor.attemptsTo(Enter.theValue("globalworming").into("#form3-username"))
    val password = SystemEnvironmentVariables.createEnvironmentVariables().getValue("PASSWORD")
    actor.attemptsTo(Enter.theValue(password).into("#form3-password").thenHit(Keys.ENTER))
  }
}
