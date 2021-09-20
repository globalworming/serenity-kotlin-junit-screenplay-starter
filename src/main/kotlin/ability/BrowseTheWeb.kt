package ability

import Ability
import org.openqa.selenium.WebDriver

class BrowseTheWeb(val webDriver: WebDriver) : Ability {


  companion object {
    fun with(webdriver: WebDriver): BrowseTheWeb = BrowseTheWeb(webdriver)
  }
}
