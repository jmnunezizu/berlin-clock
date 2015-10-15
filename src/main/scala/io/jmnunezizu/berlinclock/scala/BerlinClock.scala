package io.jmnunezizu.berlinclock.scala

case class BerlinClock(hours: Int, minutes: Int, seconds: Int) {

  import Lamps._
  
  private val FirstRowLamps = 4
  private val SecondRowLamps = 4
  private val ThirdRowLamps = 11
  private val FourthRowLamps = 4

  def topLamp = {
    if (seconds % 2 == 0) Yellow
    else Off
  }

  def firstRow = row(FirstRowLamps, onLamps(hours / 5, Red))
  def secondRow = row(SecondRowLamps, onLamps(hours % 5, Red))

  def thirdRow = {
    val totalYellowLamps = minutes / 5
    row(ThirdRowLamps, (n: Int) => n match {
      case _ if (n + 1) % 3 == 0  && n < totalYellowLamps => Red
      case _ if n < totalYellowLamps => Yellow
      case _ => Off
    })
  }

  def fourthRow = row(FourthRowLamps, onLamps(minutes % 5, Yellow))

  def display = {
    s"${topLamp.id} ${rowToString(firstRow)} ${rowToString(secondRow)} ${rowToString(thirdRow)} ${rowToString(fourthRow)}"
  }

  private def rowToString(lampRow: Seq[Lamp]): String = lampRow.map(_.id).mkString

  private def row(upperBound: Int, fn: (Int) => Lamp): Seq[Lamp] = {
    for {
      n <- 0 until upperBound
    } yield fn(n)
  }

  private def onLamps(total: Int, lamp: Lamp): (Int) => Lamp = {
    (n: Int) => if (n < total) lamp else Off
  }

}

object BerlinClock {

  private val Separator = ":"

  def unapply(s: String): Option[BerlinClock] = {
    s.split(Separator) match {
      case Array(hours, minutes, seconds) => Some(new BerlinClock(hours.toInt, minutes.toInt, seconds.toInt))
      case _ => None
    }
  }

}

object Lamps {
  sealed abstract class Lamp(val id: String)

  case object Yellow extends Lamp("Y")
  case object Red extends Lamp("R")
  case object Off extends Lamp("O")

  val lamps = Set(Yellow, Red, Off)
}