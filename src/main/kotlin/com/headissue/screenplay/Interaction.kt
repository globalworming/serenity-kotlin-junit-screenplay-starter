package com.headissue.screenplay

interface Interaction<A : Ability> : Performable {


  fun performUsing(ability: A)

}
