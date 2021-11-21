package com.headissue.screenplay

import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.verify
import org.junit.Test

class TaskTest : MockkSetup() {


  @RelaxedMockK
  lateinit var mockAbility: Ability

  @SpyK
  var task = object : Task {
    override fun performAs(actor: Actor) {
      actor.perform(subTask)
    }
  }

  @SpyK
  var subTask = object : Task {
    override fun performAs(actor: Actor) {
      actor.perform(interaction)
    }
  }

  @SpyK
  var interaction = object : Interaction<Ability> {
    override fun performUsing(ability: Ability) {}

    override fun performAs(actor: Actor) {
      performUsing(actor.abilities.first())
    }

  }

  @Test
  fun `task are performed recursively`() {
    val actor = Actor(abilities = mutableListOf(mockAbility))
    actor.perform(task)
    verify(exactly = 1) { interaction.performUsing(mockAbility) }
  }

}
