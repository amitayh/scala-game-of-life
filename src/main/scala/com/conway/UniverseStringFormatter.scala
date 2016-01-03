package com.conway

class UniverseStringFormatter(topLeft: Cell, dimensions: Dimensions, formatConfig: FormatConfig = FormatConfig()) {

  def format(universe: Universe): String = formatRows(universe).mkString(formatConfig.rowSeparator)

  private def formatRows(universe: Universe) = for {
    row <- topLeft.x until topLeft.x + dimensions.height
  } yield formatRow(universe, row)

  private def formatRow(universe: Universe, row: Int) = {
    val cols = topLeft.y until topLeft.y + dimensions.width
    val formattedCells = cols.map(col => formatCell(universe.isAlive(Cell(col, row))))
    formattedCells.mkString(formatConfig.colSeparator)
  }

  private def formatCell(isAlive: Boolean) = if (isAlive) formatConfig.livingCell else formatConfig.deadCell

}
