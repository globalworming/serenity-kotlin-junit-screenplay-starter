package com.example.e2e.api

import com.example.WorldServiceBase
import com.example.screenplay.question.api.WorldServiceHealth
import net.serenitybdd.junit5.SerenityJUnit5Extension
import net.serenitybdd.screenplay.GivenWhenThen.*
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(SerenityJUnit5Extension::class)
open class HelloWorldServiceIT : WorldServiceBase() {

  @Test
  fun `when checking health`() {
    tester.should(seeThat(WorldServiceHealth(), `is`("OK")))
  }

}