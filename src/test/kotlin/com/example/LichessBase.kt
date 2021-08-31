package com.example

import com.example.screenplay.ability.AccessEmail
import com.example.screenplay.actor.Memory
import com.mailosaur.MailosaurClient
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.actors.OnlineCast
import net.thucydides.core.util.EnvironmentVariables
import net.thucydides.core.util.SystemEnvironmentVariables
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.BeforeEach
import java.util.stream.Stream

open class LichessBase {


  private val environmentVariables: EnvironmentVariables = SystemEnvironmentVariables.createEnvironmentVariables()

  lateinit var host: Actor
  lateinit var guest: Actor

  @BeforeEach
  open fun setUp() {
    Assumptions.assumeTrue(environmentVariables.aValueIsDefinedFor("MAILOSAUR_API_KEY"))
    Assumptions.assumeTrue(environmentVariables.aValueIsDefinedFor("MAILOSAUR_SERVER"))

    val cast = OnlineCast()
    host = cast.actorUsingBrowser("chrome").named("host")
    guest = cast.actorUsingBrowser("firefox").named("guest")

    val mailosaurServerId = environmentVariables.getValue("MAILOSAUR_SERVER")
    val mailosaurClient = MailosaurClient(environmentVariables.getValue("MAILOSAUR_API_KEY"))

    Stream.of(host, guest).forEach {
      it.remember(Memory.MAILOSAUR_SERVER_ID, mailosaurServerId)
      it.remember(Memory.MAILOSAUR_DOMAIN, "$mailosaurServerId.mailosaur.net")
      it.can(AccessEmail.with(mailosaurClient))
    }

    host.remember(Memory.EMAIL_ADDRESS, "energy-eat@$mailosaurServerId.mailosaur.net")

    mailosaurClient.messages().deleteAll(mailosaurServerId)
  }

}
