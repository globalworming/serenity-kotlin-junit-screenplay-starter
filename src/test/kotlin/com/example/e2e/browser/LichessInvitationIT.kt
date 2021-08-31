package com.example.e2e.browser

import com.example.LichessBase
import com.example.screenplay.action.AcceptsInvitation
import com.example.screenplay.action.InviteFriend
import com.example.screenplay.action.LoginSuccessfully
import com.example.screenplay.question.GuestIsOnline
import net.serenitybdd.junit5.SerenityJUnit5Extension
import net.serenitybdd.screenplay.EventualConsequence.*
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import net.thucydides.core.annotations.Narrative
import org.hamcrest.CoreMatchers.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.stream.Collectors

@Narrative(
  text = [
    "example using multiple actors, one Chrome, other Firefox"]
)
@ExtendWith(SerenityJUnit5Extension::class)
class LichessInvitationIT : LichessBase() {

  @Test
  fun `when we invite a friend to play`() {
    host.attemptsTo(LoginSuccessfully())
    //printCookieForDebuggingPurposes()
    host.attemptsTo(InviteFriend.toGame())
    val inviteUrl = host.recall<String>("invite url")
    guest.attemptsTo(AcceptsInvitation.forGame(inviteUrl))
    host.should(eventually(seeThat(GuestIsOnline(), `is`(true))))
  }

  private fun printCookieForDebuggingPurposes() {
    println(BrowseTheWeb.`as`(host).driver.manage().cookies.parallelStream().map { cookie -> cookie.toString() }
      .collect(Collectors.joining(", ")))

  }
}