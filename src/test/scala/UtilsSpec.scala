import model.JobPosting
import org.scalatest.funsuite.AnyFunSuite

import java.time.LocalDateTime
import java.io.FileNotFoundException
import util.Utils

class UtilsSpec extends AnyFunSuite {
  test("test should read a CSV file omitting header when header=true") {
    val file_content = Utils.readCSV("src/test/resources/test.csv", true)

    val expected_content = List(
      "value1,value2",
      "value3,value4"
    )

    assert(file_content == expected_content)
  }
  test("test should read a CSV file when header=false") {
    val file_content = Utils.readCSV("src/test/resources/test.csv", false)

    val expected_content = List(
      "columnName1,columnName2",
      "value1,value2",
      "value3,value4"
    )

    assert(file_content == expected_content)
  }

  test("test incorrect path") {
    intercept[FileNotFoundException] {
      Utils.readCSV("non/existing/file.csv", true)
    }
  }

  test("test parsing JobPosting from list of elems") {
    val input = List(
      "https://jobs.example.com/job/1;Data Engineer;DataCorp;Responsible for building data pipelines.;15000-20000;Python,SQL,Scala;Warszawa, Polska;false;2025-05-10T09:00:00",
      "https://jobs.example.com/job/2;Backend Developer;TechSolutions;Develop backend services.;14000-18000;Java,Kotlin,Spring;Kraków, Polska;true;2025-05-14T10:30:00"
    )
    val expectedOutput = List(
      JobPosting("https://jobs.example.com/job/1", "Data Engineer", "DataCorp", "Responsible for building data pipelines.", "15000-20000", List("Python", "SQL", "Scala"), "Warszawa, Polska", false, LocalDateTime.of(2025, 5, 10, 9, 0, 0)),
      JobPosting("https://jobs.example.com/job/2", "Backend Developer", "TechSolutions", "Develop backend services.", "14000-18000", List("Java", "Kotlin", "Spring"), "Kraków, Polska", true, LocalDateTime.of(2025, 5, 14, 10, 30, 0))
    )

    val actualOutput = Utils.parseJobPostings(input, ';')
  }
}
