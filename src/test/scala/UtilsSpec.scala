import org.scalatest.funsuite.AnyFunSuite
import java.io.FileNotFoundException
import util.Utils

class UtilsSpec extends AnyFunSuite {
  test("test should read a CSV file") {
    val file_content = Utils.readCSV("src/test/resources/test.csv")

    val expected_content = List(
      "columnName1,columnName2",
      "value1,value2",
      "value3,value4"
    )

    assert(file_content == expected_content)
  }

  test("test incorrect path") {
    intercept[FileNotFoundException] {
      Utils.readCSV("non/existing/file.csv")
    }
  }
}
