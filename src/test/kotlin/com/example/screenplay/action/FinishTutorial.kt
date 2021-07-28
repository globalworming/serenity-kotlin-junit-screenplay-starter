package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import org.openqa.selenium.By
import org.openqa.selenium.interactions.Actions

open class FinishTutorial : Performable {
  override fun <T : Actor?> performAs(actor: T) {
    val driver = BrowseTheWeb.`as`(actor).driver
    Actions(driver).moveToElement(driver.findElement(By.cssSelector(".rook.white"))).click().build().perform()
    Actions(driver).moveToElement(driver.findElement(By.cssSelector(".apple"))).click().build().perform()

  }

}
