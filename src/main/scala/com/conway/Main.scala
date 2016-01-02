package com.conway

import scala.annotation.tailrec

object Main {

  def main(args: Array[String]) {
    // Glider example
    val formatter = new UniverseViewFormatter(Cell(0, 0), Dimensions(10, 10))
    val universe = Universe(
      "0 1 0",
      "0 0 1",
      "1 1 1")

    formatUniverse(formatter, universe)
  }

  @tailrec
  private def formatUniverse(formatter: UniverseViewFormatter, universe: Universe): Unit = {
    println(formatter.format(universe))
    println("---------------------")
    Thread.sleep(200)
    formatUniverse(formatter, universe.nextGeneration)
  }

}
