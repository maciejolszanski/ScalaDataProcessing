import org.scalatest.funsuite.AnyFunSuite

class CSVReaderSpec extends AnyFunSuite {
  test("test should read a CSV file") {
    val reader = CSVReader("src/test/resources/test_file.csv", ',')
    val file_content = reader.readRaw()

    val expected_content = List(
      "date,product,quantity,unit_price",
      "2025-05-01,Widget A,4,19.99",
      "2025-05-01,Widget B,2,29.99"
    )

    assert(file_content == expected_content)
  }
}
