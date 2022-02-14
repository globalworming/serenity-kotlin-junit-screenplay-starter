package com.headissue.screenplay

import io.mockk.MockKAnnotations
import org.junit.jupiter.api.BeforeEach

open class MockkSetup {

  @BeforeEach
  fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

}
