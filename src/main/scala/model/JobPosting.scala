package model

import java.time.LocalDateTime

case class JobPosting(
    sourceURL: String,
    title: String,
    company: String,
    description: String,
    salaryRange: String,
    skills: List[String],
    location: String,
    isRemote: Boolean,
    datePublished: LocalDateTime
) {
  def getSalariesMinMax: (Double, Double) = {
    salaryRange.split('-').map(_.toDouble) match
      case Array(x, y) => (x, y)
  }
}
