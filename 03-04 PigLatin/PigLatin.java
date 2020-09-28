//name: R2-14     
//date:  9-6-18


import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
   }
   
   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if (s.equals("-1")) 
            System.exit(0);
         String p = pig(s);
         System.out.println( p );
      }		
   }
   public static String pig(String s)
   {
      String vowels = "aeiouAEIOU";
      String vowelsY = "aeiouyAEIOUY";
      String justLet = "";
      boolean uppercase = false;
      for(int x = 0; x < s.length(); x++)
      {
         char ch = s.charAt(x);
         if(Character.isLetterOrDigit(ch) || ch == '\'' || ch == '-')
         {
            justLet = justLet + ch;
         }
      }
      if(Character.isUpperCase(justLet.charAt(0)))
      {
         String firstchar = Character.toString(justLet.charAt(0));
         justLet = justLet.replaceFirst(firstchar, firstchar.toLowerCase());
         uppercase = true;
      }
      for(int y = 0; y < vowels.length(); y++)
      {
         if(justLet.charAt(0) == vowels.charAt(y))
         {
            justLet = justLet + "way";
            return combine(s, justLet, uppercase);
         }
      }
      for(int k = 1; k < justLet.length(); k++)
      {
         for(int i = 0; i < vowelsY.length(); i++)
         {
            if(vowelsY.charAt(i) == justLet.charAt(k))
            {
               return(combine(s, slide(justLet, k), uppercase));
            }
         }
      }
      return "****NO VOWEL****";
   }
   public static String slide(String str, int k)
   {
      String temp = "";
      if(Character.toString(str.charAt(k)).equalsIgnoreCase("u"))
      {
         if(Character.toString(str.charAt(k - 1)).equalsIgnoreCase("q"))
         {
            for(int x = k + 1; x < str.length(); x++)
            {
               temp = temp + str.charAt(x);
            }
            for(int y = 0; y <= k; y++)
            {
               temp = temp + str.charAt(y);
            }
            temp = temp + "ay";
            return temp;
         }
      }
      for(int x = k; x < str.length(); x++)
      {
         temp = temp + str.charAt(x);
      }
      for(int y = 0; y < k; y++)
      {
         temp = temp + str.charAt(y);
      }
      temp = temp + "ay";
      return temp;
   }
   public static String combine(String big, String sml, boolean upper)
   {
      int index = 0;
      
      //Reverse code
      String reversed = "";
      for(int x = 0; x < sml.length(); x++)
      {
         reversed += sml.charAt(sml.length() - 1 - x);  
      }
      sml = reversed;
      String firstchar = Character.toString(sml.charAt(0));
      if(upper)
         sml = sml.replaceFirst(firstchar, firstchar.toUpperCase());
      
      for(int x = 0; x < big.length(); x++)
      {
         char ch = big.charAt(x);
         if(Character.isLetterOrDigit(ch) || ch == '\'' || ch == '-')
         {
            String first = big.substring(0, x);
            //big = big.replace(big.charAt(x), sml.charAt(index));
            //index++;
            for(int y = big.length() - 1; y >= 0; y--)
            {
               char ch2 = big.charAt(y);
               if(Character.isLetterOrDigit(ch2) || ch == '\'' || ch == '-')
               {
                  String last = big.substring(y + 1);
                  return first + sml + last;
               }
            }
         }
      } 
      return "";
   }

   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  precondition:  both Strings include .txt
*  postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	
      /*  Enter your code here.  Try to preserve lines and paragraphs. ***/
      while(infile.hasNextLine())
      {
         String line = infile.nextLine();
         String[] temp = line.split(" ");
         for(int x = 0; x < temp.length; x++)
         {
            if(!temp[x].trim().isEmpty())
            {
               if(x != temp.length - 1)
                  outfile.print(pig(temp[x]) + " ");
               else
                  outfile.println(pig(temp[x].trim()));
            }
            else if(x == 0)
               outfile.println();
         }
      }
      
   
   
   
      outfile.close();
      infile.close();
   }
}
