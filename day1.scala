// scala day1.scala day1.txt
import scala.util.matching.Regex
import scala.util.Try

val wordsToNumsMap = Map(
  "one"   -> "1e",
  "two"   -> "2o",
  "three" -> "3e",
  "four"  -> "4r",
  "five"  -> "5e",
  "six"   -> "6x",
  "seven" -> "7n",
  "eight" -> "8t",
  "nine"  -> "9e"
)
val wordsToNumsRegex: Regex = wordsToNumsMap.keys.mkString("|").r

def wordsToNums(line: String): String =
  wordsToNumsRegex.replaceAllIn(line, m => wordsToNumsMap(m.matched))

def firstAndLastDigit(line: String): Int =
  val digits = line.filter(_.isDigit)
  val first  = digits.headOption.getOrElse('0')
  val last   = digits.lastOption.getOrElse(first)
  return s"${first}${last}".toInt

def linesFromFile(filename: String): Array[String] =
  val source = scala.io.Source.fromFile(filename)
  try source.mkString.split("\n")
  finally source.close()

@main def main(filename: String) =
  val lines = linesFromFile(filename)
  println(s"day1 part1: ${lines.map(firstAndLastDigit).sum}")

  println(
    s"day1 part2: ${lines.map(wordsToNums).map(wordsToNums).map(firstAndLastDigit).sum}"
  )
