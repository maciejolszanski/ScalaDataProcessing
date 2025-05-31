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

}
