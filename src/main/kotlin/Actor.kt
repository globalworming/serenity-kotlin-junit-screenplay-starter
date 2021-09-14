class Actor(val abilities: MutableList<Ability> = mutableListOf()) {


  fun perform(performable: Performable) = performable.performAs(this)

  fun asks(question: Question): Any = question.answerAs(this)
}