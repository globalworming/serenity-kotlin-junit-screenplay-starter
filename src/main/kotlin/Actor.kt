class Actor(val ability: Ability? = null) {


  fun perform(performable: Performable) = performable.performAs(this)

  fun asks(question: Question): Any = question.answerAs(this)
}