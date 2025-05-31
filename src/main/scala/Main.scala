import util.Utils

object Main {
  def main(args: Array[String]): Unit = {
    val path        = "data/job_postings_structured.csv"
    val fileContent = Utils.readCSV(path, header = true)
    val jobPostings = Utils.parseJobPostings(fileContent, separator = ';')
  }
}
