import model.{JobPosting, JobPostingCollection}
import org.scalatest.funsuite.AnyFunSuite

import java.time.LocalDateTime

class JobPostingCollectionSpec extends AnyFunSuite {

  test("getting distinct locations - no duplicates"){
    val input = JobPostingCollection(
      List(
        JobPosting(
          sourceURL = "",
          title = "",
          company = "",
          description = "",
          salaryRange = "",
          skills = List(""),
          location = "Warsaw, Poland",
          isRemote = true,
          datePublished = LocalDateTime.of(2025, 1, 1, 1, 1, 1, 1)
        ),
        JobPosting(
          sourceURL = "",
          title = "",
          company = "",
          description = "",
          salaryRange = "",
          skills = List(""),
          location = "Kraków, Poland",
          isRemote = true,
          datePublished = LocalDateTime.of(2025, 1, 1, 1, 1, 1, 1)
        ),
        JobPosting(
          sourceURL = "",
          title = "",
          company = "",
          description = "",
          salaryRange = "",
          skills = List(""),
          location = "Warsaw, Poland",
          isRemote = true,
          datePublished = LocalDateTime.of(2025, 1, 1, 1, 1, 1)
        )
      )
    )
    val expectedOutput = Set("Warsaw, Poland", "Kraków, Poland")
    val actualOutput = input.getDistinctLocations

    assert(expectedOutput == actualOutput)
  }

  test("getting avg min and max salary") {
    val input = JobPostingCollection(
      List(
        JobPosting(
          sourceURL = "",
          title = "jobTitle1",
          company = "",
          description = "",
          salaryRange = "100-200",
          skills = List(""),
          location = "",
          isRemote = true,
          datePublished = LocalDateTime.of(2025, 1, 1, 1, 1, 1, 1)
        ),
        JobPosting(
          sourceURL = "",
          title = "jobTitle1",
          company = "",
          description = "",
          salaryRange = "200-400",
          skills = List(""),
          location = "",
          isRemote = true,
          datePublished = LocalDateTime.of(2025, 1, 1, 1, 1, 1, 1)
        ),
        JobPosting(
          sourceURL = "",
          title = "jobTitle2",
          company = "",
          description = "",
          salaryRange = "400 - 1200",
          skills = List(""),
          location = "",
          isRemote = true,
          datePublished = LocalDateTime.of(2025, 1, 1, 1, 1, 1)
        )
      )
    )
    val expectedOutput = Map(
      "jobTitle1" -> (150.0, 300.0),
      "jobTitle2" -> (400.0, 1200.0)
    )
    val actualOutput = input.getAvgSalaryRangePerTitle

    assert(expectedOutput == actualOutput)
  }
}
