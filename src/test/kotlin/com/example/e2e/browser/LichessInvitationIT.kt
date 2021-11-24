package com.example.e2e.browser

import com.example.LichessBase
import com.example.screenplay.action.AcceptsInvitation
import com.example.screenplay.action.InviteFriend
import com.example.screenplay.action.LoginSuccessfully
import com.example.screenplay.question.GuestIsOnline
import net.serenitybdd.junit.runners.SerenityRunner
import net.serenitybdd.screenplay.EventualConsequence.*
import net.serenitybdd.screenplay.GivenWhenThen.*
import net.serenitybdd.screenplay.actions.Open
import org.hamcrest.CoreMatchers.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(SerenityRunner::class)
class LichessInvitationIT : LichessBase() {


  @Test
  fun `when we invite a friend to play`() {
    host.attemptsTo(Open.url("https://lichess.org/login"))
    host.attemptsTo(LoginSuccessfully())
    host.attemptsTo(InviteFriend())
    guest.attemptsTo(AcceptsInvitation.forGame(host.recall("invite url")))
    host.should(eventually(seeThat(GuestIsOnline(), `is`(true))))

  }
}