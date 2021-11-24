package com.example

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import net.thucydides.core.annotations.Managed
import org.junit.Before
import org.openqa.selenium.WebDriver

open class LichessBase {
  @Managed(driver = "chrome")
  private lateinit var aBrowser: WebDriver

  @Managed(driver = "chrome")
  private lateinit var anotherBrowser: WebDriver

  val host = Actor("host")
  val guest = Actor("Anonymous")

  @Before
  fun setUp() {
    host.can(BrowseTheWeb.with(aBrowser))
    guest.can(BrowseTheWeb.with(anotherBrowser))
  }

}
