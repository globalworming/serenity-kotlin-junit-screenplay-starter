package com.example.e2e.api

import com.example.screenplay.action.GetListOfAllPosts
import com.example.screenplay.action.UploadNewPost
import com.example.screenplay.question.NumberOfReturnedPosts
import net.serenitybdd.junit5.SerenityJUnit5Extension
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.actors.Cast
import net.serenitybdd.screenplay.actors.Cast.*
import net.serenitybdd.screenplay.rest.abilities.CallAnApi
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence.*
import net.thucydides.core.annotations.Narrative
import org.apache.http.HttpStatus
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@Narrative(text = ["a simple REST API example against https://jsonplaceholder.typicode.com"])
@ExtendWith(SerenityJUnit5Extension::class)
open class JsonPlaceHolderIT {

  private val cast: Cast = whereEveryoneCan(CallAnApi.at("https://jsonplaceholder.typicode.com"))
  private lateinit var author: Actor

  @BeforeEach
  fun setUp() {
    author = cast.actorNamed("author")
  }

  @Test
  fun `when there are many posts`() {
    author.attemptsTo(GetListOfAllPosts())
    author.should(seeThat(NumberOfReturnedPosts(), greaterThan(99)))
  }

  @Test
  fun `when posting`() {
    author.attemptsTo(UploadNewPost.containing("{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}"))
    author.should(seeThatResponse {
      it.statusCode(HttpStatus.SC_CREATED).body("id", CoreMatchers.`is`(101))
    })
  }
}