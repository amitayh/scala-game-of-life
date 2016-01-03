package com.conway.swing

import java.awt.{Graphics2D, Graphics, Color}
import javax.swing.{JPanel, JFrame}

import com.conway.{Universe, Dimensions}

class UniverseFrame(dimensions: Dimensions) extends JFrame {

  val updateDelay = 100
  val cellSize = 5
  val painter = new UniversePainter(Color.WHITE, Color.BLACK, dimensions, cellSize)

  var universe: Universe = _

  setTitle("Conway's Game of Life")
  setLocationRelativeTo(null)
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  setSize(dimensions.width * cellSize, dimensions.height * cellSize)
  add(new JPanel {
    override def paintComponent(g: Graphics): Unit = {
      painter.paint(g.asInstanceOf[Graphics2D], universe)
    }
  })

  def start(initialUniverse: Universe): Unit = {
    paintUniverse(initialUniverse)
    new Thread(new Runnable {
      override def run(): Unit = {
        updateLoop()
      }
    }).start()
  }

  private def paintUniverse(universe: Universe): Unit = {
    this.universe = universe
    repaint()
  }

  private def updateLoop(): Unit = {
    while (true) {
      Thread.sleep(updateDelay)
      paintUniverse(universe.nextGeneration)
    }
  }

}
