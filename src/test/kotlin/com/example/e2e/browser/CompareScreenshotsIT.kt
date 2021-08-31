package com.example.e2e.browser

import com.example.screenplay.question.image.NoDifferenceToSnapshot
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.actions.Open
import net.serenitybdd.screenplay.actors.OnlineCast
import org.hamcrest.CoreMatchers.*
import org.junit.jupiter.api.Test

class CompareScreenshotsIT {

  /**
   * naive example of a failing screenshot comparison
   * would need to be extended to only screenshot specific elements, see implementation CreateSnapshot
   */
  @Test
  fun `when comparing full size page`() {
    val tester = OnlineCast().actorNamed("tester")
    tester.attemptsTo(Open.url("https://www.gns.cri.nz/Home/Our-Science/Energy-Futures/Oil-and-Gas/Petroleum-Basin-Explorer"))
    tester.should(seeThat(NoDifferenceToSnapshot("homepage.png"), `is`(true)))
  }
}