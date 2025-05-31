package model

case class JobPostingCollection(jobPostings: List[JobPosting]) {
  val amount: Int = jobPostings.length
  
  def getDistinctLocations: Set[String] = {
    jobPostings.foldLeft(Set.empty[String]) { case (acc, posting) =>
      acc + posting.location
    }
  }

  def getAvgSalaryRangePerTitle: Map[String, (Double, Double)] = {
    jobPostings
      .groupBy(_.title)
      .map { case (title, postings) =>
        title -> postings
          .map(_.getSalariesMinMax)
          .foldLeft(0, (0.0, 0.0)) {
            case ((count, (sumMin, sumMax)), (salaryMin, salaryMax)) =>
              (count + 1, (sumMin + salaryMin, sumMax + salaryMax))
          }
      }
      .map { case (title, (count, (sumMin, sumMax))) =>
        title -> (sumMin / count, sumMax / count)
      }
  }

  def getSkillsPerTitle: Map[String, Set[String]] = {
    jobPostings
      .groupBy(_.title)
      .view
      .mapValues { postings =>
        postings.foldLeft(Set.empty[String]) { case (acc, posting) =>
          acc ++ posting.skills.toSet
        }
      }
      .to(Map)
  }
}
