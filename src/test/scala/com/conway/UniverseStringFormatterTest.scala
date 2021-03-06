package com.conway

import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.Scope

class UniverseStringFormatterTest extends SpecificationWithJUnit {

  trait Context extends Scope {
    val formatConfig = FormatConfig("1", "0")
  }

  "format a dead universe" in new Context {
    val universe = Universe()
    val formatter = new UniverseStringFormatter(Cell(0, 0), Dimensions(3, 2), formatConfig)

    val expected =
      """
        |0 0 0
        |0 0 0
      """.stripMargin.trim

    formatter.format(universe) must equalTo(expected)
  }

  "show living cells in view" in new Context {
    val universe = Universe(
      "1 0",
      "0 1")

    val formatter = new UniverseStringFormatter(Cell(0, 0), Dimensions(2, 2), formatConfig)

    val expected =
      """
        |1 0
        |0 1
      """.stripMargin.trim

    formatter.format(universe) must equalTo(expected)
  }

  "show relative position" in new Context {
    val universe = Universe(
      "1 0",
      "0 1")

    val formatter = new UniverseStringFormatter(Cell(1, 1), Dimensions(2, 2), formatConfig)

    val expected =
      """
        |1 0
        |0 0
      """.stripMargin.trim

    formatter.format(universe) must equalTo(expected)
  }

  "formatting config" in {
    val universe = Universe("1")
    val formatConfig = FormatConfig("X", "O", "|", "-")
    val formatter = new UniverseStringFormatter(Cell(0, 0), Dimensions(2, 2), formatConfig)

    formatter.format(universe) must equalTo("X|O-O|O")
  }

}
