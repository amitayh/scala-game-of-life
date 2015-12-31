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

}
