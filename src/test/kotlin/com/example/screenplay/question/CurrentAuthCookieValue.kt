package com.example.screenplay.question

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.EventualConsequence.*
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import org.hamcrest.CoreMatchers.*
import org.openqa.selenium.Cookie

class CurrentAuthCookieValue : QuestionWithDefaultSubject<String>() {
  override fun answeredBy(actor: Actor): String {
    actor.should(eventually(seeThat("cookie lila2 is set", { BrowseTheWeb.`as`(actor).driver.manage().cookies.first { it.name == "lila2" } }, notNullValue())))

    val authCookie: Cookie = BrowseTheWeb.`as`(actor).driver.manage().cookies.first { it.name == "lila2" }
    actor.should(eventually(seeThat("browser auth cookie lila2 value", { authCookie.value }, notNullValue())))
    return authCookie.value
  }

}
