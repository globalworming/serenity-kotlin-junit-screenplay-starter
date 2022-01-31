package com.example.screenplay.question

import com.example.screenplay.ability.AccessEmail
import com.example.screenplay.actor.Memory
import com.mailosaur.models.SearchCriteria
import net.serenitybdd.screenplay.Actor

class LatestMessagesFirstLink : QuestionWithDefaultSubject<String>() {
  override fun answeredBy(actor: Actor): String {
    val searchCriteria = SearchCriteria().withSentTo(actor.recall(Memory.EMAIL_ADDRESS))
    val serverId = actor.recall<String>(Memory.MAILOSAUR_SERVER_ID)
    val mailosaurClient = AccessEmail.`as`(actor)
    val message = mailosaurClient.messages().get(serverId, searchCriteria)
    return message.html().links()[0].href()
  }

}
