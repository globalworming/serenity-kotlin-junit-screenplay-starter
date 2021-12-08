package com.example.screenplay.question

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.targets.Target

class ActorNeedsToSolveCaptcha : QuestionWithDefaultSubject<Boolean>() {
  override fun answeredBy(actor: Actor): Boolean {
    return Target.the("captcha").locatedBy(".FIXME").resolveAllFor(actor).size > 0
  }

}
