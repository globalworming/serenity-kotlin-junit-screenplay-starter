package com.example.e2e.browser

import com.example.LichessBase
import com.example.screenplay.action.AcceptsInvitation
import com.example.screenplay.action.InviteFriend
import com.example.screenplay.action.LoginSuccessfully
import com.example.screenplay.question.GuestIsOnline
import net.serenitybdd.junit.runners.SerenityRunner
import net.serenitybdd.screenplay.EventualConsequence.*
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import org.hamcrest.CoreMatchers.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.stream.Collectors

@RunWith(SerenityRunner::class)
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
    System.out.println(BrowseTheWeb.`as`(host).driver.manage().cookies.parallelStream().map { cookie -> cookie.toString() }.collect(Collectors.joining(", ")))

  }
}