package com.conway

import org.specs2.mutable.SpecificationWithJUnit

class UniverseViewFormatterTest extends SpecificationWithJUnit {

  val formatConfig = FormatConfig("1", "0")

  "format a dead universe" in {
    val universe = Universe()
    val formatter = new UniverseViewFormatter(Cell(0, 0), Dimensions(3, 2), formatConfig)

    val expected =
      """
        |0 0 0
        |0 0 0
      """.stripMargin.trim

    formatter.format(universe) must equalTo(expected)
  }

  "show living cells in view" in {
    val universe = Universe(Cell(0, 0), Cell(2, 0), Cell(1, 1))
    val formatter = new UniverseViewFormatter(Cell(0, 0), Dimensions(2, 2), formatConfig)

    val expected =
      """
        |1 0
        |0 1
      """.stripMargin.trim

    formatter.format(universe) must equalTo(expected)
  }

  "show relative position" in {
    val universe = Universe(Cell(0, 0), Cell(1, 1))
    val formatter = new UniverseViewFormatter(Cell(1, 1), Dimensions(2, 2), formatConfig)

    val expected =
      """
        |1 0
        |0 0
      """.stripMargin.trim

    formatter.format(universe) must equalTo(expected)
  }

}
