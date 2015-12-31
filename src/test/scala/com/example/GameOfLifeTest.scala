package com.example

import com.example.GameOfLife.{Cell, Universe}
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.Scope

class GameOfLifeTest extends SpecificationWithJUnit {

  trait Context extends Scope

  "Universe" >> {
    "isAlive" should {
      "be false by default" in {
        val universe = Universe()

        universe.isAlive(Cell(0, 0)) must beFalse
      }

      "be true if cell in position is alive" in {
        val cell = Cell(0, 0)
        val universe = Universe(cell)

        universe.isAlive(cell) must beTrue
      }
    }

    "nextGeneration" should {
      "kill a cell with 0 living neighbours" in {
        val cell = Cell(0, 0)
        val universe = Universe(cell)

        universe.nextGeneration.isAlive(cell) must beFalse
      }

      "kill a cell with 1 living neighbours" in {
        val cell1 = Cell(0, 0)
        val cell2 = Cell(1, 0)
        val universe = Universe(cell1, cell2)
        val nextGeneration = universe.nextGeneration

        nextGeneration.isAlive(cell1) must beFalse
        nextGeneration.isAlive(cell2) must beFalse
      }

      "keep a cell with 2 living neighbours" in {
        val cell1 = Cell(0, 0)
        val cell2 = Cell(1, 0)
        val cell3 = Cell(2, 0)
        val universe = Universe(cell1, cell2, cell3)
        val nextGeneration = universe.nextGeneration

        nextGeneration.isAlive(cell2) must beTrue
      }

      "keep a cell with 3 living neighbours" in {
        val cell1 = Cell(0, 1)
        val cell2 = Cell(1, 0)
        val cell3 = Cell(1, 1)
        val cell4 = Cell(2, 1)
        val universe = Universe(cell1, cell2, cell3, cell4)
        val nextGeneration = universe.nextGeneration

        nextGeneration.isAlive(cell2) must beTrue
      }

      "revive dead cell with exactly 3 living neighbours" in {
        val cell1 = Cell(0, 0)
        val cell2 = Cell(1, 0)
        val cell3 = Cell(2, 0)
        val universe = Universe(cell1, cell2, cell3)
        val nextGeneration = universe.nextGeneration

        nextGeneration.isAlive(Cell(1, 1)) must beTrue
      }
    }
  }

}
