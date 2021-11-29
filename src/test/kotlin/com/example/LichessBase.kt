package com.example

import com.example.screenplay.ability.AccessEmail
import com.mailosaur.MailosaurClient
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.actors.OnlineCast
import net.thucydides.core.util.SystemEnvironmentVariables
import org.junit.Before

open class LichessBase {


  val environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables()
  val mailosaurClient = MailosaurClient(environmentVariables.getProperty("MAILOSAUR_API_KEY"))

  lateinit var host: Actor
  lateinit var guest: Actor

  @Before
  fun setUp() {
    val cast = OnlineCast()
    host = cast.actorUsingBrowser("chrome").named("host")
    host.can(AccessEmail.with(mailosaurClient))
    guest = cast.actorUsingBrowser("firefox").named("guest")
    guest.can(AccessEmail.with(mailosaurClient))
  }
}
