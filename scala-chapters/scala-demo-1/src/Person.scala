

class Person(val fname: String, var lname: String) {
  def  greet:String = "Hello "+fname;
  def  greet(surname: String):String = "Hello "+fname +surname;
  def  greet(seq: Int):String = "Hello "+fname +seq;
  
  //auxiliary constructor
  def this(name: String) {
    this(name,"");
  }
}