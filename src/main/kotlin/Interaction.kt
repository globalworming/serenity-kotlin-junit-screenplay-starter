interface Interaction<T> : Performable {

  override fun performAs(actor: Actor) {
    val interfaze = actor.abilities.first() as T
    performUsing(interfaze)
  }


  fun performUsing(interfaze: T)

}
