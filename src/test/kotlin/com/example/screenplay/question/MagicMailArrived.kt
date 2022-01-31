package com.example.screenplay.question

import com.example.screenplay.ability.AccessEmail
import com.example.screenplay.actor.Memory
import com.mailosaur.models.SearchCriteria
import net.serenitybdd.screenplay.Actor

class MagicMailArrived : QuestionWithDefaultSubject<Boolean>() {
  override fun answeredBy(actor: Actor): Boolean {
    val serverId = actor.recall<String>(Memory.MAILOSAUR_SERVER_ID)
    val mailosaurClient = AccessEmail.`as`(actor)
    return mailosaurClient.messages()
        .get(serverId, SearchCriteria().withSentFrom("login@lichess.org")) != null
  }

}
