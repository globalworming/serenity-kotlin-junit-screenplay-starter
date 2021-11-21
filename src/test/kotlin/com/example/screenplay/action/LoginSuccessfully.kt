package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.actions.Enter
import net.serenitybdd.screenplay.conditions.SilentPerformable
import net.thucydides.core.util.SystemEnvironmentVariables
import org.junit.Assume
import org.openqa.selenium.Keys

open class LoginSuccessfully : SilentPerformable() {
  override fun <T : Actor> performAs(actor: T) {
    val username = SystemEnvironmentVariables.createEnvironmentVariables().getValue("USERNAME")
    val password = SystemEnvironmentVariables.createEnvironmentVariables().getValue("PASSWORD")
    Assume.assumeTrue("you will need an lichess.org account to run these", username != null)
    Assume.assumeTrue("you will need an lichess.org account to run these", password != null)
    actor.attemptsTo(Enter.theValue(username).into("#form3-username"))
    actor.attemptsTo(Enter.theValue(password).into("#form3-password").thenHit(Keys.ENTER))
  }
}
