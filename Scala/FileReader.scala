import scala.io.Source
import java.io.{FileNotFoundException, IOException}

object FileReader extends App {
    val filename = "no-such-file.scala"
    try {
        for (line <- Source.fromFile(filename).getLines) {
            println(line)
        }
    } catch {
        case e: FileNotFoundException => println("Couldn't find that file ["+filename+"]")
        case e: IOException => println("Got an IOException!")
    }
} 
