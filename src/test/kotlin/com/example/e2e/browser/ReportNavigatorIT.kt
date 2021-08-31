package com.example.e2e.browser

import com.example.screenplay.action.AccessTheLatestReport
import com.example.screenplay.action.LookUpAllUnsuccessfulOutcomes
import com.example.screenplay.question.OutcomesShown
import net.serenitybdd.screenplay.EventualConsequence.*
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.actors.OnlineCast
import net.serenitybdd.screenplay.questions.CountQuestion
import net.thucydides.core.annotations.Narrative
import org.hamcrest.CoreMatchers.*
import org.junit.jupiter.api.Test

@Narrative(text = ["describes the report behavior"])
class ReportNavigatorIT {


  @Test
  fun whenTracingErrors() {
    val tester = OnlineCast().actorNamed("tester")
    tester.attemptsTo(AccessTheLatestReport())
    tester.attemptsTo(LookUpAllUnsuccessfulOutcomes())
    tester.should(eventually(seeThat(CountQuestion(OutcomesShown()), `is`(9))))
  }
}