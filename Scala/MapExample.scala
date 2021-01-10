/**
MapExample  is a Example code to Understand Map Operations in Scala

How to Run:
    1. Run 'scalac ScalaMapExample.scala'
    2. Run scala MapExample

Output:
    Capital of Karnataka is : Bangalore
    Capital of Tamil Nadu is : Chennai
    State xyz not present in Map, Please add xyz into Map
    Values in statesCapitalMap : MapLike(Bangalore, Chennai, Trivendrum)
    Check if statesCapitalMap is empty : false
    Check if numbers is empty : true
*/


object MapExample {
   def main(args: Array[String]) {
      val statesCapitalMap = Map("Karnataka" -> "Bangalore", "Tamil Nadu" -> "Chennai", "Kerala" -> "Trivendrum")
      val countryCapitalMap = Map("India" -> "Bangalore", "Nepal" -> "Chennai", "Singapore" -> "Trivendrum")

      val numbers: Map[Int, Int] = Map()

      println( "Capital of Karnataka is : " + statesCapitalMap("Karnataka") )
      println( "Capital of Tamil Nadu is : " + statesCapitalMap("Tamil Nadu" ) )
      //This has to be chcked for previous 2 cases as well
      if (statesCapitalMap.contains("xyz")){
        println( "Capital of xyz is : " + statesCapitalMap("xyz" ) )
      }else{
          println("State xyz not present in Map, Please add xyz into Map")
      }

      println( "Values in statesCapitalMap : " + statesCapitalMap.values )
      println( "Check if statesCapitalMap is empty : " + statesCapitalMap.isEmpty )
      println( "Check if numbers is empty : " + numbers.isEmpty )

   }
}
