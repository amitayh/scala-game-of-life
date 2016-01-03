package com.conway.swing

import java.awt.{Color, Graphics2D}

import com.conway.{Dimensions, Universe}
import org.specs2.mock.Mockito
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.Scope

class UniversePainterTest extends SpecificationWithJUnit with Mockito {

  trait Context extends Scope {
    val backgroundColor = Color.WHITE
    val livingCellsColor = Color.BLACK
    val dimensions = Dimensions(60, 45)
    val cellSize = 5

    val g2d = mock[Graphics2D]
    val painter = new UniversePainter(backgroundColor, livingCellsColor, dimensions, cellSize)
  }

  "Universe painter" should {
    "paint background" in new Context {
      val universe = Universe()

      painter.paint(g2d, universe)

      got {
        one(g2d).setColor(backgroundColor)
        one(g2d).fillRect(0, 0, dimensions.width * cellSize, dimensions.height * cellSize)
      }
    }

    "paint living cells" in new Context {
      val universe = Universe(
        "1 0",
        "0 1")

      painter.paint(g2d, universe)

      got {
        one(g2d).setColor(livingCellsColor)
        one(g2d).fillRect(0, 0, 5, 5)
        one(g2d).fillRect(5, 5, 5, 5)
      }
    }
  }

}
