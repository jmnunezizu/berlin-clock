package io.jmnunezizu.berlinclock.scala

import org.specs2.Specification
import io.jmnunezizu.berlinclock.scala.Lamps._

class BerlinClockSpec extends Specification { def is = s2"""

     Berlin Clock Spec

     should display nicely                                               $e1
     should have a top lamp that blinks every 2 seconds                  $e2

     the first row of lamps should
       have 4 lamps                                                      $e3
       light a red lamp every 5 hours                                    $e4

     the second row of lamps should
       have 4 lamps                                                      $e5
       light a red lamp for every remaining hour                         $e6

     the third row of lamps should
       have 11 lamps                                                     $e7
       light a yellow lamp every 5 minutes and a red lamp every quarter  $e8

     the fourth row of lamps should
       have 4 lamps                                                      $e9
       light a yellow lamp for every remaining minute                    $e10
                                                                         """

  def e1 = {
    BerlinClock(0, 0, 0).display === "Y OOOO OOOO OOOOOOOOOOO OOOO"
    BerlinClock(11, 53, 1).display === "O RROO ROOO YYRYYRYYRYO YYYO"
  }

  def e2 = {
    BerlinClock(0, 0, 0).topLamp === Yellow
    BerlinClock(0, 0, 1).topLamp === Off
  }

  def e3 = BerlinClock(0, 0, 0).firstRow.length === 4

  def e4 = {
    BerlinClock(0, 0, 0).firstRow === Seq(Off, Off, Off, Off)
    BerlinClock(5, 0, 0).firstRow === Seq(Red, Off, Off, Off)
    BerlinClock(10, 0, 0).firstRow === Seq(Red, Red, Off, Off)
    BerlinClock(15, 0, 0).firstRow === Seq(Red, Red, Red, Off)
    BerlinClock(20, 0, 0).firstRow === Seq(Red, Red, Red, Red)
  }

  def e5 = BerlinClock(0, 0, 0).secondRow.length === 4

  def e6 = {
    BerlinClock(0, 0, 0).secondRow === Seq(Off, Off, Off, Off)
    BerlinClock(1, 0, 0).secondRow === Seq(Red, Off, Off, Off)
    BerlinClock(2, 0, 0).secondRow === Seq(Red, Red, Off, Off)
    BerlinClock(3, 0, 0).secondRow === Seq(Red, Red, Red, Off)
    BerlinClock(4, 0, 0).secondRow === Seq(Red, Red, Red, Red)
  }

  def e7 = BerlinClock(0, 0, 0).thirdRow.length === 11

  def e8 = {
    BerlinClock(0, 0, 0).thirdRow === Seq(Off, Off, Off, Off, Off, Off, Off, Off, Off, Off, Off)
    BerlinClock(0, 5, 0).thirdRow === Seq(Yellow, Off, Off, Off, Off, Off, Off, Off, Off, Off, Off)
    BerlinClock(0, 10, 0).thirdRow === Seq(Yellow, Yellow, Off, Off, Off, Off, Off, Off, Off, Off, Off)
    BerlinClock(0, 15, 0).thirdRow === Seq(Yellow, Yellow, Red, Off, Off, Off, Off, Off, Off, Off, Off)
    BerlinClock(0, 20, 0).thirdRow === Seq(Yellow, Yellow, Red, Yellow, Off, Off, Off, Off, Off, Off, Off)
    BerlinClock(0, 25, 0).thirdRow === Seq(Yellow, Yellow, Red, Yellow, Yellow, Off, Off, Off, Off, Off, Off)
    BerlinClock(0, 30, 0).thirdRow === Seq(Yellow, Yellow, Red, Yellow, Yellow, Red, Off, Off, Off, Off, Off)
    BerlinClock(0, 35, 0).thirdRow === Seq(Yellow, Yellow, Red, Yellow, Yellow, Red, Yellow, Off, Off, Off, Off)
    BerlinClock(0, 40, 0).thirdRow === Seq(Yellow, Yellow, Red, Yellow, Yellow, Red, Yellow, Yellow, Off, Off, Off)
    BerlinClock(0, 45, 0).thirdRow === Seq(Yellow, Yellow, Red, Yellow, Yellow, Red, Yellow, Yellow, Red, Off, Off)
    BerlinClock(0, 50, 0).thirdRow === Seq(Yellow, Yellow, Red, Yellow, Yellow, Red, Yellow, Yellow, Red, Yellow, Off)
    BerlinClock(0, 55, 0).thirdRow === Seq(Yellow, Yellow, Red, Yellow, Yellow, Red, Yellow, Yellow, Red, Yellow, Yellow)
  }

  def e9 = BerlinClock(0, 0, 0).fourthRow.length === 4

  def e10 = {
    BerlinClock(0, 0, 0).fourthRow === Seq(Off, Off, Off, Off)
    BerlinClock(0, 1, 0).fourthRow === Seq(Yellow, Off, Off, Off)
    BerlinClock(0, 2, 0).fourthRow === Seq(Yellow, Yellow, Off, Off)
    BerlinClock(0, 3, 0).fourthRow === Seq(Yellow, Yellow, Yellow, Off)
    BerlinClock(0, 4, 0).fourthRow === Seq(Yellow, Yellow, Yellow, Yellow)
  }

}
