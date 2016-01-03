package com.conway

import java.awt.EventQueue

import com.conway.swing.UniverseFrame

object Main {

  // Glider gun!
  val initialUniverse = Universe(FormatConfig(colSeparator = ""),
    "                                      ",
    "                         *            ",
    "                       * *            ",
    "             **      **            ** ",
    "            *   *    **            ** ",
    " **        *     *   **               ",
    " **        *   * **    * *            ",
    "           *     *       *            ",
    "            *   *                     ",
    "             **                       ",
    "                                      ")

  def main(args: Array[String]) {
    EventQueue.invokeLater(new Runnable() {
      override def run(): Unit = {
        val frame = new UniverseFrame(Dimensions(60, 45))
        frame.start(initialUniverse)
        frame.setVisible(true)
      }
    })
  }

}
