package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import org.openqa.selenium.Cookie
import java.time.Instant
import java.util.*

open class AddSessionCookie(private val cookieValue: String) : Performable {
  override fun <T : Actor> performAs(actor: T) {
    BrowseTheWeb.`as`(actor).driver.manage()
        .addCookie(
            Cookie(
                "lila2",
                cookieValue,
                ".lichess.org",
                "/",
                Date.from(Instant.now().plusSeconds(600)), true, true)
        )

  }

}
