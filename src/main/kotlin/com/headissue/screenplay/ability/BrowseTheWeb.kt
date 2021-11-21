package com.headissue.screenplay.ability

import com.headissue.screenplay.Ability
import org.openqa.selenium.WebDriver

class BrowseTheWeb(val webDriver: WebDriver) : Ability {


  companion object {
    fun with(webdriver: WebDriver): BrowseTheWeb = BrowseTheWeb(webdriver)
  }
}
