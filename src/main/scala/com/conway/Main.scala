package com.conway

object Main {

  def main(args: Array[String]) {
    val formatter = new UniverseViewFormatter(Cell(-10, -10), Dimensions(10, 10))
    var universe = Universe(Cell(0, 0), Cell(1, 0), Cell(2, 0), Cell(0, 1), Cell(1, 2))
    while (true) {
      println(formatter.format(universe))
      println("-----------------------------------------------")
      universe = universe.nextGeneration
      Thread.sleep(200)
    }
  }

}
