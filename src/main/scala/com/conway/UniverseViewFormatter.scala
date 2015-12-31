package com.conway

case class Dimensions(width: Int, height: Int)

case class FormatConfig(livingCell: String = "*",
                        deadCell: String = " ",
                        colSeparator: String = " ",
                        rowSeparator: String = "\n")

class UniverseViewFormatter(topLeft: Cell, dimensions: Dimensions, formatConfig: FormatConfig = FormatConfig()) {

  def format(universe: Universe): String = formatRows(universe).mkString(formatConfig.rowSeparator)

  private def formatRows(universe: Universe) = for {
    row <- topLeft.x until topLeft.x + dimensions.height
  } yield formatRow(universe, row)

  private def formatRow(universe: Universe, row: Int) = {
    val areAlive = for {
      col <- topLeft.y until topLeft.y + dimensions.width
    } yield universe.isAlive(Cell(col, row))
    areAlive.map(formatCell).mkString(formatConfig.colSeparator)
  }

  private def formatCell(isAlive: Boolean) = if (isAlive) formatConfig.livingCell else formatConfig.deadCell

}
