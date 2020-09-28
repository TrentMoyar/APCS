// Name: Trent Moyar
// Date: 9/18/2018
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class Cemetery
{
   public static void main (String [] args)
   {
      File file = new File("cemetery.txt");
      int numEntries = countEntries(file);
      Person[] cemetery = readIntoArray(file, numEntries); 
      int min = locateMinAgePerson(cemetery);
      int max = locateMaxAgePerson(cemetery); 
      //for testing only
      //for (int i = 0; i < cemetery.length; i++) 
         //System.out.println(cemetery[i]);
      System.out.println("In the St. Mary Magdelene Old Fish Cemetery --> ");
      System.out.println("Name of youngest person: " + cemetery[min].getName());
      System.out.println("Age of youngest person: " + cemetery[min].getAge());    
      System.out.println("Name of oldest person: " + cemetery[max].getName());
      System.out.println("Age of oldest person: " + cemetery[max].getAge());     
   }
   
   /* Counts and returns the number of entries in File f.
      Uses a try-catch block.   
      @param f -- the file object
   */
   public static int countEntries(File f)
   {
      int sum = 0;
      try {
         Scanner inFile = new Scanner(f);
         while(inFile.hasNextLine()) {
            inFile.nextLine();
            sum++;
         }
         inFile.close();
      } 
      catch (FileNotFoundException e) {
         System.out.println("IO Error");
      }
      return sum;
   }

   /* Reads the data.
      Fills the array with Person objects.
      Uses a try-catch block.   
      @param f -- the file object 
      @param num -- the number of lines in the File f  
   */
   public static Person[] readIntoArray (File f, int num)
   {
      String line;
      Person[] people = new Person[num];
      try {
         Scanner inFile = new Scanner(f);
         for(int k = 0; k < num; k++) {
            line = inFile.nextLine();
            people[k] = makeObjects(line);
         }
         inFile.close();
      } 
      catch (FileNotFoundException e) {
         System.out.println("IO Error");
      }
      return people;
   }
   
   /* A helper method that instantiates one Person object.
      @param entry -- one line of the input file.
   */
   private static Person makeObjects(String entry)
   {
      int x,y, index = 0;
      String name = "", age = "", date = "";
      StringTokenizer tok = new StringTokenizer(entry);
      String[] words = new String[tok.countTokens()];
      for(x = 0; x < words.length; x++) {
         words[x] = tok.nextToken();
      }
      boolean checked = false;
      for(x = 0; x < words.length; x++) {
         if(Character.isUpperCase(words[x].charAt(words[x].length()-1)) && x != words.length-1) {
            for(y = 0; y <= x; y++) {
               name = name + words[y] + " ";
            }
            name = name.trim();
            index = x+1;
            checked = true;
         } 
         else if(checked) {
            for(y = index; y < 3 + index; y++) {
               date = date + words[y] + " ";
            }
            date = date.trim();
            age = words[index + 3];
            checked = false;
         }
      }
      return new Person(name, age, date);
   }  
   
   /* Finds and returns the location (the index) of the Person
      who is the youngest.
      @param arr -- an array of Person objects.
   */
   public static int locateMinAgePerson(Person[] arr)
   {
      int pos = 0;
      for(int x = 0; x < arr.length; x++) {
         if(arr[x].getAge() < arr[pos].getAge())
            pos = x;
      }
      return pos;
   }   
   
   /* Finds and returns the location (the index) of the Person
      who is the oldest.
      @param arr -- an array of Person objects.
   */
   public static int locateMaxAgePerson(Person[] arr)
   {
      int pos = 0;
      for(int x = 0; x < arr.length; x++) {
         if(arr[x].getAge() > arr[pos].getAge())
            pos = x;
      }
      return pos;
   }        
} 

class Person
{
   /* private fields  */
   private DecimalFormat df = new DecimalFormat("0.0000");
   private double age;
   private String name, date;
   
   /* a three-arg constructor  */
   public Person(String n, String a, String d)
   {
      name = n;
      age = calculateAge(a);
      date = d;
   }
   /* any necessary accessor methods */
   
   public double calculateAge(String a)
   {
      String temp = a;
      double age;
      switch (temp.charAt(temp.length() - 1)) {
         case 'd':  age = Double.parseDouble(temp.substring(0, temp.length()-1)) /365.0;
            break;
         case 'w':  age = Double.parseDouble(temp.substring(0, temp.length()-1))*7/365;
            break;
         default: age = Double.parseDouble(temp);
            break;
      }
      return age;
   }
   
   public double getAge() {
      return Double.parseDouble(df.format(age));
   }
   
   public String getName() {
      return name;
   }
   
   public String toString() {
      return name + " was " + age + " year(s) old when he/she died on " + date;
   }
}