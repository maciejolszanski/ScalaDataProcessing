package util

import scala.io.Source

object Utils {
  def readCSV(path: String): List[String] = {
    val file = Source.fromFile(path)

    try {
      file.getLines().toList
    } finally {
      file.close()
    }
  }
}
