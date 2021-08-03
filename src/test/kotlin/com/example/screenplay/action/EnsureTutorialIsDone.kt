package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.EventualConsequence.*
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import org.hamcrest.CoreMatchers.*
import org.openqa.selenium.By

open class EnsureTutorialIsDone(private val int: Int) : Performable {
  override fun <T : Actor> performAs(actor: T) {
    actor.should(eventually(seeThat(
        "number of completed tutorials",
        { BrowseTheWeb.`as`(it).findAll(By.cssSelector(".progress .done")).size },
        `is`(int))))
  }

}
