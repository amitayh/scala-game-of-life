package com.conway.swing

import java.awt.{Color, Graphics, Graphics2D}
import java.util.concurrent.LinkedBlockingDeque
import javax.swing.{JFrame, JPanel}

import com.conway.{Dimensions, Universe}

class UniverseFrame(dimensions: Dimensions) extends JFrame {

  val updateDelay = 100
  val cellSize = 5
  val calculateAhead = 2
  val painter = new UniversePainter(Color.WHITE, Color.BLACK, dimensions, cellSize)
  val nextUniverses = new LinkedBlockingDeque[Universe](calculateAhead)
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
    new Thread(new Runnable {
      override def run(): Unit = {
        var nextUniverse = initialUniverse
        while (true) {
          nextUniverses.put(nextUniverse)
          nextUniverse = nextUniverse.nextGeneration
        }
      }
    }).start()

    new Thread(new Runnable {
      override def run(): Unit = {
        while (true) {
          universe = nextUniverses.take()
          repaint()
          Thread.sleep(updateDelay)
        }
      }
    }).start()
  }

}
