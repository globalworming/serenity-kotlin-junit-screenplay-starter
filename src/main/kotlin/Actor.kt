class Actor(var hasPerformedTask: Boolean = false, var hasAnswerdedQuestion: Boolean = false) {


  fun perform(performable: Performable) {
    performable.perform()
    hasPerformedTask = true
  }

  fun asks(question: Question): Any {
    val answer = question.answer()
    hasAnswerdedQuestion = true
    return answer
  }

  val ability: Ability? = object : Ability {}
}