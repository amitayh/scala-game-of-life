package com.conway

import org.specs2.mutable.SpecificationWithJUnit

class GameOfLifeTest extends SpecificationWithJUnit {

  "string builder" should {
    "build a universe from marked cells" in {
      val universe = Universe(
        "1 0",
        "0 1")

      universe.isAlive(Cell(0, 0)) must beTrue
      universe.isAlive(Cell(1, 1)) must beTrue
      universe.isAlive(Cell(0, 1)) must beFalse
    }

    "use FormatConfig to determine format" in {
      val formatConfig = FormatConfig(livingCell = "X", deadCell = "O", colSeparator = "|")
      val universe = Universe(formatConfig,
        "X|O",
        "O|X")

      universe.isAlive(Cell(0, 0)) must beTrue
      universe.isAlive(Cell(1, 1)) must beTrue
    }
  }

  "isAlive" should {
    "be false by default" in {
      val universe = Universe()

      universe.isAlive(Cell(0, 0)) must beFalse
    }

    "be true if cell is alive" in {
      val universe = Universe("1")

      universe.isAlive(Cell(0, 0)) must beTrue
    }
  }

  "nextGeneration" should {
    "kill a cell with 0 living neighbours" in {
      val universe = Universe("1")

      universe.nextGeneration.isAlive(Cell(0, 0)) must beFalse
    }

    "kill a cell with 1 living neighbours" in {
      val universe = Universe("1 1")

      val nextGeneration = universe.nextGeneration

      nextGeneration.isAlive(Cell(0, 0)) must beFalse
      nextGeneration.isAlive(Cell(1, 0)) must beFalse
    }

    "kill a cell with 4 living neighbours" in {
      val universe = Universe(
        "1 1",
        "1 1",
        "1 0")

      val nextGeneration = universe.nextGeneration

      nextGeneration.isAlive(Cell(1, 1)) must beFalse
    }

    "keep a cell with 2 living neighbours" in {
      val universe = Universe("1 1 1")
      val nextGeneration = universe.nextGeneration

      nextGeneration.isAlive(Cell(1, 0)) must beTrue
    }

    "keep a cell with 3 living neighbours" in {
      val universe = Universe(
        "0 1 0",
        "1 1 1")

      val nextGeneration = universe.nextGeneration

      nextGeneration.isAlive(Cell(1, 0)) must beTrue
    }

    "revive dead cell with exactly 3 living neighbours" in {
      val universe = Universe("1 1 1")
      val nextGeneration = universe.nextGeneration

      nextGeneration.isAlive(Cell(1, 1)) must beTrue
    }
  }

}
