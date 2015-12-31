package com.example

object GameOfLife {

  case class Cell(x: Int, y: Int)

  case class Universe(livingCells: Set[Cell]) {

    def isAlive(cell: Cell): Boolean = livingCells.contains(cell)

    lazy val nextGeneration: Universe = Universe(nextLivingCells)

    private lazy val nextLivingCells = nextLivingCellsFromLiving ++ nextLivingCellsFromDead

    private lazy val nextLivingCellsFromLiving = livingCells.filter(hasTwoOrThreeLivingNeighbours)

    private lazy val nextLivingCellsFromDead = livingCellsDeadNeighbours.filter(countLivingNeighbours(_) == 3)

    private lazy val livingCellsDeadNeighbours = livingCells.flatMap(deadNeighbours)

    private def deadNeighbours(cell: Cell) = neighbours(cell).filter(!isAlive(_))

    private def hasTwoOrThreeLivingNeighbours(cell: Cell) = {
      val livingNeighbours = countLivingNeighbours(cell)
      livingNeighbours == 2 || livingNeighbours == 3
    }

    private def countLivingNeighbours(cell: Cell) = neighbours(cell).count(isAlive)

    private def neighbours(cell: Cell) = for {
      x <- cell.x - 1 to cell.x + 1
      y <- cell.y - 1 to cell.y + 1
      if !(x == cell.x && y == cell.y)
    } yield Cell(x, y)

  }

  case object Universe {
    def apply(livingCells: Cell*): Universe = Universe(livingCells.toSet)
  }

  class UniverseViewFormatter(universe: Universe, x: Int, y: Int, width: Int, height: Int) {

    def format: String = formatRows.mkString("\n")

    private def formatRows: Seq[String] = for {
      row <- x until x + height
    } yield formatRow(row)

    private def formatRow(row: Int): String = {
      val areAlive = for {
        col <- y until y + width
      } yield universe.isAlive(Cell(col, row))
      areAlive.map(isAlive => if (isAlive) "1" else "0").mkString(" ")
    }

  }

}
