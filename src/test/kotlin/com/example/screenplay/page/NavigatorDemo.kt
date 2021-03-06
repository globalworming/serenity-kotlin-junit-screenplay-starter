package com.example.screenplay.page

import net.serenitybdd.core.pages.PageObject
import net.serenitybdd.screenplay.targets.Target
import net.serenitybdd.screenplay.targets.Target.*
import net.thucydides.core.annotations.DefaultUrl

@DefaultUrl("https://lemon-desert-049177e03.azurestaticapps.net/")
class NavigatorDemo : PageObject() {
  companion object {
    val shareCurrentView: Target = the("share current view").locatedBy(".MuiFab-label")
  }
}