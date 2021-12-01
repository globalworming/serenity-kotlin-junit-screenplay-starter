package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.actions.Enter
import net.thucydides.core.util.SystemEnvironmentVariables
import org.junit.Assume
import org.openqa.selenium.Keys

open class FillAndSubmitLoginForm : Performable {
  override fun <T : Actor> performAs(actor: T) {
    val env = SystemEnvironmentVariables.createEnvironmentVariables()
    val username = env.getValue("USERNAME")
    val password = env.getValue("PASSWORD")
    assumeLoginCredentialsAreInEnv(username, password)
    actor.attemptsTo(
        Enter.theValue(username).into("#form3-username"),
        Enter.theValue(password).into("#form3-password").thenHit(Keys.ENTER))
  }

  private fun assumeLoginCredentialsAreInEnv(username: String?, password: String?) {
    Assume.assumeTrue("you will need an lichess.org account to run these", username != null)
    Assume.assumeTrue("you will need an lichess.org account to run these", password != null)
  }

}
