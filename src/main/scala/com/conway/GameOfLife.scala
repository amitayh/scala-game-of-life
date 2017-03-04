package com.conway

import java.util.regex.Pattern

import com.conway.BooleanFunction._

case class Cell(x: Int, y: Int)

case class Universe(livingCells: Set[Cell]) {

  def isAlive(cell: Cell): Boolean = livingCells.contains(cell)

  lazy val nextGeneration: Universe = Universe(nextLivingCells)

  private def nextLivingCells = nextLivingCellsFromLiving ++ nextLivingCellsFromDead

  private def nextLivingCellsFromLiving = livingCells.filter(hasLivingNeighbours(2) or hasLivingNeighbours(3))

  private def nextLivingCellsFromDead = livingCellsDeadNeighbours.filter(hasLivingNeighbours(3))

  private def livingCellsDeadNeighbours = livingCells.flatMap(deadNeighbours)

  private def deadNeighbours(cell: Cell) = neighbours(cell).filterNot(isAlive)

  private def hasLivingNeighbours(amount: Int) = (cell: Cell) => countLivingNeighbours(cell) == amount

  private def countLivingNeighbours(cell: Cell) = neighbours(cell).count(isAlive)

  private def neighbours(cell: Cell) = for {
    x <- proximityOf(cell.x)
    y <- proximityOf(cell.y)
    if !(x == cell.x && y == cell.y)
  } yield Cell(x, y)

  private def proximityOf(n: Int) = n - 1 to n + 1

}

case object Universe {

  val defaultFormatConfig = FormatConfig(livingCell = "1", deadCell = "0")

  def apply(rows: String*): Universe = this(defaultFormatConfig, rows: _*)

  def apply(formatConfig: FormatConfig, rows: String*): Universe = {
    val colSeparator = Pattern.quote(formatConfig.colSeparator)
    val cells = for {
      (row, rowIndex) <- rows.zipWithIndex
      (col, colIndex) <- row.split(colSeparator).zipWithIndex
      if col == formatConfig.livingCell
    } yield Cell(colIndex, rowIndex)

    Universe(cells.toSet)
  }

}
