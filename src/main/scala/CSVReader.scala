import java.io.FileNotFoundException
import scala.io.Source

class CSVReader(path: String, separator: Char) {

  def readRaw(): List[String] = {
    val file = Source.fromFile(path)
    
    try {
      file.getLines().toList
    } finally {
      file.close()
    }
  }
}
