interface Interaction<T : Ability> : Performable {

  override fun performAs(actor: Actor) {
    val ability = findAbility(actor)
    when (ability) {
      null -> {
        throw UnsupportedOperationException("no ability")
      }
      else -> performUsingAbility(ability)
    }
  }

  fun findAbility(actor: Actor): T {
    return actor.ability!! as T
  }

  fun performUsingAbility(ability: T)

}
