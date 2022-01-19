package com.example.screenplay.ability

import com.mailosaur.MailosaurClient
import net.serenitybdd.screenplay.Ability
import net.serenitybdd.screenplay.Actor

class AccessEmail(val mailosaurClient: MailosaurClient) : Ability {


  companion object {
    fun with(mailosaurClient: MailosaurClient): AccessEmail = AccessEmail(mailosaurClient)
    fun `as`(actor: Actor) = actor.usingAbilityTo(AccessEmail::class.java).mailosaurClient

  }
}