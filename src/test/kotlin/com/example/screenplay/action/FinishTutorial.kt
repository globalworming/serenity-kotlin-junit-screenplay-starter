package com.example.screenplay.action

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.EventualConsequence.*
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*
import net.serenitybdd.screenplay.questions.WebElementQuestion.*
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions

open class FinishTutorial : Performable {

  private val starsSelector = By.cssSelector(".apple")
  private val reachableStarSelector = By.cssSelector(".move-dest .apple")
  private val rookSelector = By.cssSelector(".rook.white")
  private val steps = mutableListOf<Map.Entry<Int, Int>>()

  private val selectRook = Task.where<Performable>(
      "{0} selects rook",
      object : Performable {
        override fun <T : Actor?> performAs(actor: T) {
          selectRook(BrowseTheWeb.`as`(actor).driver, rookSelector)
        }
      }
  )
  private val moveToReachableStar = Task.where<Performable>(
      "{0} moves to first reachable star",
      object : Performable {
        override fun <T : Actor> performAs(actor: T) {
          `move to star remembering step`(actor, reachableStarSelector)
        }
      }
  )

  override fun <T : Actor> performAs(actor: T) {
    val driver = BrowseTheWeb.`as`(actor).driver
    actor.attemptsTo(selectRook)
    repeat(driver.findElements(starsSelector).size) {
      val hasReachableStar = driver.findElements(reachableStarSelector).size > 0
      if (hasReachableStar) {
        actor.attemptsTo(moveToReachableStar)
      } else {
        Actions(driver).moveToElement(driver.findElement(rookSelector)).moveByOffset(steps.last().key, steps.last().value).click().build().perform()
        `move to star remembering step`(actor, reachableStarSelector)
      }
    }

  }

  private fun selectRook(driver: WebDriver, rookSelector: By?) {
    Actions(driver).moveToElement(driver.findElement(rookSelector)).click().build().perform()
  }

  private fun `move to star remembering step`(actor: Actor, reachableStarSelector: By?) {
    val driver = BrowseTheWeb.`as`(actor).driver
    val currentField = driver.findElement(rookSelector).getAttribute("data-key");
    val reachableStar = driver.findElement(reachableStarSelector)
    steps.add(buildStep(driver.findElement(rookSelector).location.getX() - reachableStar.location.getX(), driver.findElement(rookSelector).location.getY() - reachableStar.location.getY()))
    Actions(driver).moveToElement(reachableStar).click().build().perform()
    actor.should(eventually(seeThat(
        the("body [data-key=\"$currentField\"]"),
        isNotPresent())))

  }

  private fun buildStep(x: Int, y: Int): Map.Entry<Int, Int> {
    return object : Map.Entry<Int, Int> {
      override val key: Int = x
      override val value: Int = y
    }
  }

}
