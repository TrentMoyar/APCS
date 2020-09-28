import java.util.*;
public class Driver {
   public static void main(String[] args){
      String[] temp = {"go", "go", "go", "go", "g};
      List<String> L = Arrays.asList(temp);
      String val = "go";
      String newVal = "stop";
      for(ListIterator i = L.listIterator(); i.hasNext(); i.next()) {
         if(val.equals(i.next()))
            i.set(newVal);
      }
   }
}