package com.example.e2e.browser

import com.example.LichessBase
import com.example.screenplay.action.EnsureTutorialIsDone
import com.example.screenplay.action.FinishTutorial
import com.example.screenplay.action.StartTutorial
import net.serenitybdd.junit5.SerenityJUnit5Extension
import net.thucydides.core.annotations.Narrative
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@Narrative(
  text = ["automating a training game", "as homework: please go ahead and finish the first tutorial"]
)
@ExtendWith(SerenityJUnit5Extension::class)
class LichessTutorialIT : LichessBase() {

  @Test
  fun `when playing the first tutorial, part one`() {
    host.attemptsTo(StartTutorial())
    host.attemptsTo(FinishTutorial())
    host.attemptsTo(EnsureTutorialIsDone(1))
  }

  @Test
  fun `when playing the first tutorial, part two`() {
    `when playing the first tutorial, part one`()
    host.attemptsTo(FinishTutorial())
    host.attemptsTo(EnsureTutorialIsDone(2))
  }

  @Test
  fun `when playing the first tutorial, the rest`() {
    `when playing the first tutorial, part two`()
    host.attemptsTo(FinishTutorial())
    host.attemptsTo(EnsureTutorialIsDone(3))
    host.attemptsTo(FinishTutorial())
    host.attemptsTo(EnsureTutorialIsDone(4))

    host.attemptsTo(FinishTutorial()) // FIXME :)
    host.attemptsTo(EnsureTutorialIsDone(5))
    host.attemptsTo(FinishTutorial())
    host.attemptsTo(EnsureTutorialIsDone(6))
  }
}