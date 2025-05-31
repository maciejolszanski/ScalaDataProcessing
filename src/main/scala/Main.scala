import util.Utils

object Main {
  def main(args: Array[String]): Unit = {
    val path = "data/job_postings_structured.csv"
    val fileContent = Utils.readCSV(path, header = true)
    val jobPostings = Utils.parseJobPostings(fileContent, separator = ';')
    val locations = jobPostings.getDistinctLocations
    val avgSalaries = jobPostings.getAvgSalaryRangePerTitle
    val skillsNeeded = jobPostings.getSkillsPerTitle

    println(Utils.generateNChars(50, '-', ""))
    println("File with job postings was read successfully!")
    println(s"Job Postings loaded: ${jobPostings.amount}") //TODO: Display how many errors have been found - records not loaded due to Nulls etc.
    println(Utils.generateNChars(50, '-', ""))
    println(s"Jobs Locations: ${locations.mkString(", ")}")
    println(Utils.generateNChars(50, '-', ""))
    println("Average Salaries per Job Title:")
    Utils.displayMapPretty(avgSalaries, 2)
    println(Utils.generateNChars(50, '-', ""))
    println("All Skills specified per Job Title:")
    Utils.displayMapPretty(skillsNeeded, 2)
  }
}
