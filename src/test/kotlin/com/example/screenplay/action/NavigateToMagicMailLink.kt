package com.example.screenplay.action

import com.example.screenplay.ability.AccessEmail
import com.example.screenplay.actor.Memory
import com.mailosaur.models.SearchCriteria
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable

open class NavigateToMagicMailLink : Performable {
  override fun <T : Actor> performAs(actor: T) {
    val searchCriteria = SearchCriteria()
    searchCriteria.withSentTo("pleasant-warn@" + actor.recall(Memory.MAILOSAUR_DOMAIN))
    val serverId = actor.recall<String>(Memory.MAILOSAUR_SERVER)
    val message = AccessEmail.`as`(actor)
        .messages().get(serverId, searchCriteria)
    // FIXME
  }

}
