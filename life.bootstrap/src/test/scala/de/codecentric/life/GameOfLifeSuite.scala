package de.codecentric.life

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GameOfLifeSuite extends FunSuite {
  test("tick") {
    assert(GameOfLife.tick === "Tick")
  }

}
