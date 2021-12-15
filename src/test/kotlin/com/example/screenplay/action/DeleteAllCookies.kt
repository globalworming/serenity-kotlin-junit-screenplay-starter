package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.abilities.BrowseTheWeb

open class DeleteAllCookies : Performable {
  override fun <T : Actor?> performAs(actor: T) {
    BrowseTheWeb.`as`(actor).driver.manage().deleteAllCookies()
  }

}
