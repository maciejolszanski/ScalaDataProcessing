package util

import model.{JobPosting, JobPostingCollection}
import scala.io.Source
import java.time.LocalDateTime

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
