package com.conway

import com.conway.BooleanFunction._
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.Scope

class BooleanFunctionTest extends SpecificationWithJUnit {

  "Boolean function ops" should {
    trait Context extends Scope {
      val startsWithHello = (str: String) => str.startsWith("Hello")
      val endsWithWorld = (str: String) => str.endsWith("World")
    }

    "compose boolean functions with 'or'" in new Context {
      val composed = startsWithHello or endsWithWorld

      composed("Hello John") must beTrue
      composed("Good Morning World") must beTrue
      composed("Good Morning John") must beFalse
    }

    "compose boolean functions with 'and'" in new Context {
      val composed = startsWithHello and endsWithWorld

      composed("Hello World") must beTrue
      composed("Hello John") must beFalse
      composed("Good Morning World") must beFalse
    }
  }

}
