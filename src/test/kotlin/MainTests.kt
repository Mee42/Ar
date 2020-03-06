package dev.mee42

import io.kotest.core.spec.style.*
import io.kotest.matchers.*
import io.kotest.matchers.string.*

class MainTests : StringSpec({nd 
  "length should return size of string" {
    "hello".length shouldBe 5
  }
  "startsWith should test for a prefix" {
    "world" should startWith("wor")
  }
})