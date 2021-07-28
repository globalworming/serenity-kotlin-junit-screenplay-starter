package com.example.e2e.browser

import com.example.screenplay.action.EnsureTutorial1IsDone
import com.example.screenplay.action.FinishTutorial
import com.example.screenplay.action.StartTutorial
import net.serenitybdd.junit.runners.SerenityRunner
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import net.thucydides.core.annotations.Managed
import org.junit.Test
import org.junit.runner.RunWith
import org.openqa.selenium.WebDriver

@RunWith(SerenityRunner::class)
class ChessliIT {

  @Managed(driver = "chrome")
  private lateinit var aBrowser: WebDriver

  @Test
  fun `when playing the first tutorial`() {
    val actor = Actor("chess player")
    actor.can(BrowseTheWeb.with(aBrowser))
    actor.attemptsTo(StartTutorial())
    actor.attemptsTo(FinishTutorial())
    actor.attemptsTo(EnsureTutorial1IsDone())

  }
}