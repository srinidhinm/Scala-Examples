import scala.io.Source
import java.io.{FileNotFoundException, IOException}

/**
    This is a Scala Example Program to Read a File and print its contents and also handle error cases
    Case 1:
        a. Ensure You Create a File names 'Cities.txt' in the working Directory
        b. Contents of the File Should be Displayed on Console
    Case 2:
        a. Ensure 'Invalid.txt' doesn't exists in working directory
        b. A Error Message 'Couldn't find that file [Invalid.txt]' should be displayed on the console

    How to Run:
        1. Create a Cities.txt file with following Contents
            Bangalore
            Mysore
            Chennai
        2. Compile using command 'scalac FileReader.scala'
        3. Run 'scala FileReader Cities.txt'
            Output:
                Bangalore
                Mysore
                Chennai
        4. Run 'scala FileReader Invalid.txt'
            Output:
                'Couldn't find that file [Invalid.txt]'
*/

class FileReader extends App {
    def readandPrintFile(fileName:String) ={
        try {
            val validFile = Source.fromFile(fileName);
            for (line <- validFile.getLines) {
                println(line)
            }
        } catch {
            case e: FileNotFoundException => println("Couldn't find that file ["+fileName+"]")
            case e: IOException => println("Got an IOException!")
        }
    }
}

object Reader{
    def main(args: Array[String]){
        val fileName =  new FileReader();
        fileName.readandPrintFile(args(0))
    }
}
    
