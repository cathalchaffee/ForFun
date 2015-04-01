import java.util.Random;

public class RandoPong
{
  public static void main(String[] args)
  {
    Random r = new Random();
    String person = "KECLHAGN";
    int count = 0;
    int i = 0;
    while(i<person.length())
    {
      int rando = r.nextInt(person.length());
      if(count<2)
        System.out.println(person.charAt(rando));
      else
      {
        System.out.println("\n" + person.charAt(rando));
        count = 0;
      }
      person = person.substring(0, rando)+person.substring(rando+1);
    }
    System.out.println("Let the games begin!");
  }
}
