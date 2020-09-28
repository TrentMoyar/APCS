// Name: R2-14
// Date: 1/16/2019

import java.util.*;

public class Infix {
   public static void main(String[] args) {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /* build your list of Infix expressions here */
      List<String> infixExp = new ArrayList<>();
      infixExp.add("3 + 4 * 5");
      infixExp.add("3 * 4 + 5");
      infixExp.add("( -5 + 15 ) - 6 / 3");
      infixExp.add("( 3 + 4 ) * ( 5 + 6 )");
      infixExp.add("( 3 * ( 4 + 5 ) - 2 ) / 5");
      infixExp.add("8 + -1 * 2 - 9 / 3");
      infixExp.add("3 * ( 4 * 5 + 6 )");



      for (String infix : infixExp) {
         String pf = infixToPostfix(infix);
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + Postfix.eval(pf)); // Postfix must
                                                                                  // work!
      }
   }

   public static String infixToPostfix(String infix) {
      List<String> infixParts = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      /* enter your code here */
      Stack<String> stk = new Stack<>();

      String comp = "";
      String temp;
      for (int x = 0; x < infixParts.size(); x++) {
         temp = infixParts.get(x);
         if (Character.isDigit(temp.charAt(temp.length() - 1))) {
            comp = comp + " " + temp;
         } else if (temp.equals("(")) {
            stk.push(temp);
         } else if (temp.equals(")")) {
            while (!stk.peek().equals("(")) {
               comp = comp + " " + stk.pop();
            }
            stk.pop();
         } else {
            while (true) {
               if (stk.empty()) {
                  stk.push(temp);
                  break;
               } else if (stk.peek().equals("(")) {
                  stk.push(temp);
                  break;
               } else if (!isLower(temp.charAt(0), stk.peek().charAt(0))) {
                  stk.push(temp);
                  break;
               } else {
                  comp = comp + " " + stk.pop();
               }
            }
         }
      }
      while (!stk.empty()) {
         comp = comp + " " + stk.pop();
      }
      return comp.trim();
   }

   // returns true if c1 has lower or equal precedence than c2
   public static boolean isLower(char c1, char c2) {
      if (c1 == '+' || c1 == '-')
         return true;
      else if (c2 == '+' || c2 == '-')
         return false;
      return true;
   }
}

/********************************************

 Infix  	-->	Postfix		-->	Evaluate
 3 + 4 * 5			3 4 5 * +			23
 3 * 4 + 5			3 4 * 5 +			17
 ( -5 + 15 ) - 6 / 3			-5 15 + 6 3 / -			8
 ( 3 + 4 ) * ( 5 + 6 )			3 4 + 5 6 + *			77
 ( 3 * ( 4 + 5 ) - 2 ) / 5			3 4 5 + * 2 - 5 /			5
 8 + -1 * 2 - 9 / 3			8 -1 2 * + 9 3 / -			3
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78
 
***********************************************/
