// Name: R2-14
// Date: 11/26/18

import java.util.*;
import java.io.*;

public class Josephus
{
   private static String WINNER = "Josephus";
   
   public static void main(String[] args) throws FileNotFoundException
   {
      /* run numberCircle first with J_numbers.txt  */
      Scanner sc = new Scanner(System.in);
      System.out.print("How many names (2-20)? ");
      int n = Integer.parseInt(sc.next());
      System.out.print("How many names to count off each time?"  );
      int countOff = Integer.parseInt(sc.next());
      ListNode winningPos = numberCircle(n, countOff, "J_numbers.txt");
      System.out.println(winningPos.getValue() + " wins!");  
     
      /* run josephusCircle next with J_names.txt  */
      System.out.println("\n ****  Now start all over. **** \n");
      System.out.print("Where should "+WINNER+" stand?  ");
      int winPos = Integer.parseInt(sc.next());        
      ListNode theWinner = josephusCircle(n, countOff, "J_names.txt", winPos);
      System.out.println(theWinner.getValue() + " wins!");  
   }
   
   public static ListNode numberCircle(int n, int countOff, String filename) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      p = countingOff(p, countOff, n);
      return p;
   }
   
   public static ListNode josephusCircle(int n, int countOff, String filename, int winPos) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      replaceAt(p, WINNER, winPos);
      p = countingOff(p, countOff, n);
      return p;
   }

   /* reads the names, calls insert(), builds the linked list.
	 */
   public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException
   {
      Scanner in = new Scanner(f);
      ListNode temp = new ListNode(in.next(), null);
      temp.setNext(temp);
      for(int x = 1; x < n; x++) {
         temp = insert(temp, in.next());
      }
      return temp;
   }
   
   /* helper method to build the list.  Creates the node, then
    * inserts it in the circular linked list.
	 */
   public static ListNode insert(ListNode p, Object obj)
   {
      p.setNext(new ListNode(obj, p.getNext()));
      
      return p.getNext();
   }
   
   /* Runs a Josephus game, counting off and removing each name. Prints after each removal.
      Ends with one remaining name, who is the winner. 
	 */
   public static ListNode countingOff(ListNode p, int count, int n)
   {
      ListNode temp = p;
      print(temp);
      for(int x = 1; x < n; x++) {
         temp = remove(temp, count);
         print(temp);
      }
   
      return temp;
   }
   
   /* removes the node after counting off count-1 nodes.
	 */
   public static ListNode remove(ListNode p, int count)
   {
      ListNode temp = p;
      for(int x = 0; x < count; x++) {
         temp = temp.getNext();
      }
      ListNode temp2 = temp;
      while(temp2.getNext() != temp) {
         temp2 = temp2.getNext();
      }
      temp2.setNext(temp2.getNext().getNext());
      
      return temp2;
   }
   
   /* prints the circular linked list.
	 */
   public static void print(ListNode p)
   {
      ListNode temp = p.getNext();
      while(true){
         System.out.print(temp.getValue());
         temp = temp.getNext();
         if(temp != p.getNext())
            System.out.print(" ");
         else
            break;
      }
      System.out.println();
   }
	
   /* replaces the value (the string) at the winning node.
	 */
   public static void replaceAt(ListNode p, Object obj, int pos)
   {
      ListNode temp = p;
      for(int x = 1; x < pos; x++) {
         temp = temp.getNext();
      }
      temp.setNext(new ListNode(obj, temp.getNext().getNext()));
   }
}

