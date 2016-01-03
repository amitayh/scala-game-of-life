package com.conway.swing

import java.awt.{Color, Graphics2D}

import com.conway.{Cell, Dimensions, Universe}

class UniversePainter(backgroundColor: Color,
                      livingCellsColor: Color,
                      dimensions: Dimensions,
                      cellSize: Int) {

  def paint(g2d: Graphics2D, universe: Universe): Unit = {
    paintBackground(g2d)
    paintCells(g2d, universe)
  }

  private def paintBackground(g2d: Graphics2D): Unit = {
    g2d.setColor(backgroundColor)
    g2d.fillRect(0, 0, dimensions.width * cellSize, dimensions.height * cellSize)
  }

  private def paintCells(g2d: Graphics2D, universe: Universe): Unit = {
    g2d.setColor(livingCellsColor)
    universe.livingCells.foreach(paintCell(g2d))
  }

  private def paintCell(g2d: Graphics2D)(cell: Cell): Unit = {
    g2d.fillRect(cell.x * cellSize, cell.y * cellSize, cellSize, cellSize)
  }

}
