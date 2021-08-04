class Actor(var hasPerformedTask: Boolean = false) {


  fun perform(performable: Performable) {
    performable.perform()
    hasPerformedTask = true
  }

  val ability: Ability? = object : Ability {}
}