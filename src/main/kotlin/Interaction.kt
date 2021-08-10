interface Interaction : Performable {

  override fun performAs(actor: Actor) {
    val ability = actor.ability
    when (ability) {
      null -> {
        throw UnsupportedOperationException("no ability")
      }
      else -> performUsingAbility(ability)
    }
  }

  fun performUsingAbility(ability: Ability)

}
