object HelloWorld {
  def main(args: Array[String]) {
    val str = "Hello World";
    var countries = Array("Asia","America","Europe","Antartica");
    var fruits = new Array[String](3);
    println(str);
    for(country <- countries) {
      println(country);
    }
    println(square(5));
    println(sum(5,5));
    
    val person = new Person("Abhi","Bhowmick");
    println(person.fname);
    println(person.greet);
    println(person.greet(" Bhowmick"));
    println(person.greet(11));
    
    //following is not valid, as param declared with 'val'
    //person.fname = "Abhi";
    
    //following is not valid, as param declared with 'var'
    person.lname = "Bhowmick123";
  }
  
  //input less function
  def hello = "Hello World";
  
  //can also be written like below
  def HelloWorld() = "Hello World";
  
  // single input param
  def square(i: Int) = {i*i};
  
  //double input param
  def sum(i: Int, j: Int) = {i + i};
  
  //Assign function as Variable
  val doubler = (i:Int) =>{i*2}
}