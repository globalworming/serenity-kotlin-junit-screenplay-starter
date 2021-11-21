package com.headissue.screenplay

import io.mockk.MockKAnnotations
import org.junit.Before

open class MockkSetup {

  @Before
  fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

}
