package util

import model.{JobPosting, JobPostingCollection}

import java.time.LocalDateTime
import scala.io.Source

object Utils {
  def readCSV(path: String, header: Boolean): List[String] = {
    val file = Source.fromFile(path)

    try {
      val allLines = file.getLines().toList
      val lines    = if header then allLines.tail else allLines
      lines
    } finally {
      file.close()
    }
  }

  def parseJobPostings(
      postingsText: List[String],
      separator: Char
  ): JobPostingCollection = {
    val postings = postingsText.map(_.split(separator).createJobPosting)
    JobPostingCollection(postings)
  }

  def displayMapPretty[K, V](
      mapToDisplay: Map[K, V],
      indentation: Int
  ): Unit = {
    mapToDisplay.foreach { case (key, value) =>
      println(s"${generateNChars(indentation, ' ', "")}$key:")
      value match
        case l: List[_] =>
          println(s"${generateNChars(indentation + 1, ' ', "")}${l.mkString(", ")}")
        case s: Set[_] =>
          println(s"${generateNChars(indentation + 1, ' ', "")}${s.mkString(", ")}")
        case (tupleVal1, tupleVal2) =>
          println(s"${generateNChars(indentation + 1, ' ', "")}$tupleVal1 - $tupleVal2")
        case m: Map[_, _] => displayMapPretty(m, indentation + 1)
        case other => println(s"${generateNChars(indentation + 1, ' ', "")}$other")
    }
  }

  @annotation.tailrec
  def generateNChars(n: Int, character: Char, acc: String): String = {
    if n <= 0 then acc
    else generateNChars(n - 1, character, acc :+ character)
  }

  extension (postingElems: Array[String]) {
    private def createJobPosting: JobPosting = {
      val sourceURL     = postingElems(0)
      val title         = postingElems(1)
      val company       = postingElems(2)
      val description   = postingElems(3)
      val salaryRange   = postingElems(4)
      val skills        = postingElems(5).split(',').toList
      val location      = postingElems(6)
      val isRemote      = postingElems(7).toBoolean
      val datePublished = LocalDateTime.parse(postingElems(8))

      JobPosting(
        sourceURL,
        title,
        company,
        description,
        salaryRange,
        skills,
        location,
        isRemote,
        datePublished
      )
    }
  }
}
