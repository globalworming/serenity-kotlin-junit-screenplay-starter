fun interface Question {
  fun answerAs(actor: Actor): Any {
    val ability = actor.ability
    if (ability == null) throw UnsupportedOperationException("no ability")
    return answerUsingAbility(ability)
  }

  fun answerUsingAbility(ability: Ability): Any

}
