package model

case class JobPostingCollection(jobPostings: List[JobPosting]) {
  def getDistinctLocations: Set[String] = {
    jobPostings.foldLeft(Set.empty[String]){ case(acc, posting) => acc + posting.location }
  }
}
