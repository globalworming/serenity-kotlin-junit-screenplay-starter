package com.example.e2e.presentation

import com.example.screenplay.action.presentation.ChuckleLightly
import com.example.screenplay.action.presentation.GreetTheAudience
import com.example.screenplay.action.presentation.IntroduceThemselves
import net.serenitybdd.junit.runners.SerenityRunner
import net.serenitybdd.screenplay.actors.OnlineCast
import net.serenitybdd.screenplay.actors.Stage
import net.thucydides.core.annotations.Narrative
import org.junit.Test
import org.junit.runner.RunWith

@Narrative(text = ["""
  Andreas Worm would like to talk about the screenplay pattern. The Kotlin User Group may like that. This executable specification shows how an entertaining presentation would look like, what the speaker does, the audience sees and hears, and what interactions we expect
  """])
@RunWith(SerenityRunner::class)
class PresentationIT {


  @Test
  fun `the one where Andreas "globalworming" Worm presents to the Kotlin User Group`() {
    val speaker = stage.shineSpotlightOn("Andreas")
    val theAudience = stage.shineSpotlightOn("audience")

    speaker.attemptsTo(GreetTheAudience())
    theAudience.attemptsTo(ChuckleLightly())
    speaker.attemptsTo(IntroduceThemselves())

    //speaker.attemptsTo(TalkAboutTheWhyOfScreenplay())

  }

  companion object {
    val stage = Stage(OnlineCast())
  }
}