package com.conway

object BooleanFunction {
  implicit class BooleanFunctionOps[T](f: T => Boolean) {
    def or(g: T => Boolean): T => Boolean = (x: T) => f(x) || g(x)
    def and(g: T => Boolean): T => Boolean = (x: T) => f(x) && g(x)
  }
}
