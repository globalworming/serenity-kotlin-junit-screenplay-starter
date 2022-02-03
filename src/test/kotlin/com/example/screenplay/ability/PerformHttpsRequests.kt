package com.example.screenplay.ability

import net.serenitybdd.screenplay.Ability
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.NoMatchingAbilityException
import okhttp3.OkHttpClient

class PerformHttpsRequests(httpClient: OkHttpClient) : Ability {
  private val httpClient: OkHttpClient

  init {
    this.httpClient = httpClient
  }

  companion object {
    fun with(httpClient: OkHttpClient): PerformHttpsRequests {
      return PerformHttpsRequests(httpClient)
    }

    fun <T : Actor?> `as`(actor: T): OkHttpClient {
      val performHttpsRequests = actor!!.abilityTo(
        PerformHttpsRequests::class.java
      ) ?: throw NoMatchingAbilityException(actor.name)
      return performHttpsRequests.httpClient
    }
  }
}