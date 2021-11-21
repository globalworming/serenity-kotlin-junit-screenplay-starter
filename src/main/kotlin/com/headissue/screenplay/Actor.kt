package com.headissue.screenplay

import com.headissue.screenplay.ability.BrowseTheWeb
import org.openqa.selenium.WebDriver

class Actor(val abilities: MutableList<Ability> = mutableListOf()) {


  var webDriver: WebDriver? = null

  fun perform(performable: Performable) = performable.performAs(this)

  fun asks(question: Question): Any = question.answerAs(this)
  fun can(ability: Ability) {
    when (ability) {
      is BrowseTheWeb -> webDriver = ability.webDriver
    }
  }
}