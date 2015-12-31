package com.conway

object Main {

  def main(args: Array[String]) {
    // Glider example
    val formatter = new UniverseViewFormatter(Cell(0, 0), Dimensions(10, 10))
    var universe = Universe(Cell(1, 0), Cell(2, 1), Cell(0, 2), Cell(1, 2), Cell(2, 2))
    while (true) {
      println(formatter.format(universe))
      println("---------------------")
      universe = universe.nextGeneration
      Thread.sleep(200)
    }
  }

}
