package com.example

import com.example.screenplay.ability.AccessEmail
import com.example.screenplay.actor.Memory
import com.mailosaur.MailosaurClient
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.actors.OnlineCast
import net.thucydides.core.util.SystemEnvironmentVariables
import org.junit.Before
import java.util.stream.Stream

open class LichessBase {


  val environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables()
  val mailosaurClient = MailosaurClient(environmentVariables.getValue("MAILOSAUR_API_KEY"))
  val mailosaurServerId = environmentVariables.getValue("MAILOSAUR_SERVER")

  lateinit var host: Actor
  lateinit var guest: Actor

  @Before
  fun setUp() {
    val cast = OnlineCast()
    host = cast.actorUsingBrowser("chrome").named("host")
    guest = cast.actorUsingBrowser("firefox").named("guest")

    Stream.of(host, guest).forEach {
      it.can(AccessEmail.with(mailosaurClient))
      it.remember(Memory.MAILOSAUR_SERVER, mailosaurServerId)
      it.remember(Memory.MAILOSAUR_DOMAIN, "$mailosaurServerId.mailosaur.net")
    }

    host.can(AccessEmail.with(mailosaurClient))
    guest.can(AccessEmail.with(mailosaurClient))
  }

}
