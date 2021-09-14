fun interface Question {
  fun answerAs(actor: Actor): Any {
    val ability = actor.abilities[0]
    return answerUsingAbility(ability)
  }

  fun answerUsingAbility(ability: Ability): Any

}
