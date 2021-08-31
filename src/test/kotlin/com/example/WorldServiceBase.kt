package com.example

import com.example.screenplay.ability.PerformHttpsRequests
import net.serenitybdd.screenplay.Actor
import okhttp3.OkHttpClient
import org.junit.jupiter.api.BeforeEach


open class WorldServiceBase {
  lateinit var tester: Actor

  @BeforeEach
  fun setUp() {
    tester = Actor("tester")
    tester.can(PerformHttpsRequests.with(OkHttpClient()))
  }

}
