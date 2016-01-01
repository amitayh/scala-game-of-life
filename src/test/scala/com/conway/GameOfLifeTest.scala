package com.conway

import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.Scope

class GameOfLifeTest extends SpecificationWithJUnit {

  trait Context extends Scope {
    def buildUniverse(rows: String*): Universe = {
      val cells = for {
        (row, rowIndex) <- rows.zipWithIndex
        (col, colIndex) <- row.split(" ").zipWithIndex
        if col == "1"
      } yield Cell(colIndex, rowIndex)

      Universe(cells.toSet)
    }
  }

  "isAlive" should {
    "be false by default" in {
      val universe = Universe()

      universe.isAlive(Cell(0, 0)) must beFalse
    }

    "be true if cell is alive" in new Context {
      val universe = buildUniverse("1")

      universe.isAlive(Cell(0, 0)) must beTrue
    }
  }

  "nextGeneration" should {
    "kill a cell with 0 living neighbours" in new Context {
      val universe = buildUniverse("1")

      universe.nextGeneration.isAlive(Cell(0, 0)) must beFalse
    }

    "kill a cell with 1 living neighbours" in new Context {
      val universe = buildUniverse("1 1")

      val nextGeneration = universe.nextGeneration

      nextGeneration.isAlive(Cell(0, 0)) must beFalse
      nextGeneration.isAlive(Cell(1, 0)) must beFalse
    }

    "kill a cell with 4 living neighbours" in new Context {
      val universe = buildUniverse(
        "1 1",
        "1 1",
        "1 0")

      val nextGeneration = universe.nextGeneration

      nextGeneration.isAlive(Cell(1, 1)) must beFalse
    }

    "keep a cell with 2 living neighbours" in new Context {
      val universe = buildUniverse("1 1 1")
      val nextGeneration = universe.nextGeneration

      nextGeneration.isAlive(Cell(1, 0)) must beTrue
    }

    "keep a cell with 3 living neighbours" in new Context {
      val universe = buildUniverse(
        "0 1 0",
        "1 1 1")

      val nextGeneration = universe.nextGeneration

      nextGeneration.isAlive(Cell(1, 0)) must beTrue
    }

    "revive dead cell with exactly 3 living neighbours" in new Context {
      val universe = buildUniverse("1 1 1")
      val nextGeneration = universe.nextGeneration

      nextGeneration.isAlive(Cell(1, 1)) must beTrue
    }
  }

}
