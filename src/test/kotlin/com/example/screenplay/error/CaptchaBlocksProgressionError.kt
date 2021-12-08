package com.example.screenplay.error

import net.serenitybdd.core.exceptions.TestCompromisedException

class CaptchaBlocksProgressionError(override val message: String? = null, e: Throwable? = null) : TestCompromisedException(message, e)
