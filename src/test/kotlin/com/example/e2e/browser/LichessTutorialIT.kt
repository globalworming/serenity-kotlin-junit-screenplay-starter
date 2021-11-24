package com.example.e2e.browser

import com.example.LichessBase
import com.example.screenplay.action.EnsureTutorialIsDone
import com.example.screenplay.action.FinishTutorial
import com.example.screenplay.action.StartTutorial
import net.serenitybdd.junit.runners.SerenityRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(SerenityRunner::class)
class LichessTutorialIT : LichessBase() {

  val actor = host

  @Test
  fun `when playing the first tutorial, part one`() {
    actor.attemptsTo(StartTutorial())
    actor.attemptsTo(FinishTutorial())
    actor.attemptsTo(EnsureTutorialIsDone(1))
  }

  @Test
  fun `when playing the first tutorial, part two`() {
    `when playing the first tutorial, part one`()
    actor.attemptsTo(FinishTutorial())
    actor.attemptsTo(EnsureTutorialIsDone(2))
  }

  @Test
  fun `when playing the first tutorial, the rest`() {
    `when playing the first tutorial, part two`()
    actor.attemptsTo(FinishTutorial())
    actor.attemptsTo(EnsureTutorialIsDone(3))
    actor.attemptsTo(FinishTutorial())
    actor.attemptsTo(EnsureTutorialIsDone(4))

    actor.attemptsTo(FinishTutorial()) // FIXME :)
    actor.attemptsTo(EnsureTutorialIsDone(5))
    actor.attemptsTo(FinishTutorial())
    actor.attemptsTo(EnsureTutorialIsDone(6))
  }
}