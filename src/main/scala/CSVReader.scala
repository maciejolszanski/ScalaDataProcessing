import scala.io.Source

class CSVReader(path: String, separator: Char) {

  def readRaw(): List[String] = {
    val file = Source.fromFile(path)
    file.getLines().toList
  }
}
