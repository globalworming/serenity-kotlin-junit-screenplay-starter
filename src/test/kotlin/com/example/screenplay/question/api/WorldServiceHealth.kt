package com.example.screenplay.question.api

import com.example.screenplay.ability.PerformHttpsRequests
import com.example.screenplay.question.QuestionWithDefaultSubject
import net.serenitybdd.core.exceptions.TestCompromisedException
import net.serenitybdd.screenplay.Actor
import okhttp3.Request
import java.net.ConnectException

open class WorldServiceHealth : QuestionWithDefaultSubject<String?>() {
  override fun answeredBy(actor: Actor): String? {
    val client = PerformHttpsRequests.`as`(actor)
    val request = Request.Builder().get().url("http://localhost:8001").build()
    try {
      val response = client.newCall(request).execute()
      return response.body?.string()
    } catch (e: ConnectException) {
      throw TestCompromisedException(e.message)
    }
  }

}
