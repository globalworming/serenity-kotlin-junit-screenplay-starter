package com.headissue.screenplay

import com.headissue.screenplay.ability.BrowseTheWeb

interface BrowserInteraction : Interaction<BrowseTheWeb> {

  override fun performUsing(ability: BrowseTheWeb)

  override fun performAs(actor: Actor) {
    performUsing(BrowseTheWeb.with(actor.webDriver!!))
  }
}
