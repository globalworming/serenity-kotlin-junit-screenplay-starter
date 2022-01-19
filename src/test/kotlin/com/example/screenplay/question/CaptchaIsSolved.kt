package com.example.screenplay.question

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class CaptchaIsSolved : QuestionWithDefaultSubject<Boolean>() {
  override fun answeredBy(actor: Actor): Boolean {
    val driver = BrowseTheWeb.`as`(actor)
        .driver
    val checkedCheckbox = driver.switchTo().frame(0).findElements<WebElement>(By.cssSelector("div[aria-checked=\"true\"]"))
        .size > 0
    driver.switchTo().defaultContent()
    return checkedCheckbox
  }

}
