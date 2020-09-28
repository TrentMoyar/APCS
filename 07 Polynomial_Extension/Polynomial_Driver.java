 // Name: R2-14  
 // Date: 3/14/2019

import java.util.*;
import java.text.*;

public class Polynomial_Driver
{
   public static void main(String[] args)
   {
      Polynomial poly = new Polynomial();    // 2x^3 + -4x + 2
      poly.makeTerm(1, -4.0);
      poly.makeTerm(3, 2.0);
      poly.makeTerm(0, 2.0);
      System.out.println("Map:  " + poly.getMap());
      System.out.println("Integ:  " + poly.getInteg());
      System.out.println("String:  " + poly.toString());
      double evaluateAt = 2.0;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
      
      System.out.println("-----------");
      Polynomial poly2 = new Polynomial();  // 2x^4 + x^2 + -5x + -3
      poly2.makeTerm(1, -5.0);
      poly2.makeTerm(4, 2.0);
      poly2.makeTerm(0, -3.0);
      poly2.makeTerm(2, 1.0);
      System.out.println("Map:  " + poly2.getMap()); 
      System.out.println("String:  " +poly2.toString());
      evaluateAt = -10.5;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
      
      
      System.out.println("-----------");
      System.out.println("Sum: " + poly.add(poly2));
      System.out.println("Product:  " + poly.multiply(poly2));
      
      /*  Another case:   (x+1)(x-1) -->  x^2 + -1    */
      System.out.println("===========================");
      Polynomial poly3 = new Polynomial();   // (x+1)
      poly3.makeTerm(1, 1.0);
      poly3.makeTerm(0, 1.0);
      System.out.println("Map:  " + poly3.getMap());
      System.out.println("String:  " + poly3.toString());
         
      Polynomial poly4 = new Polynomial();    // (x-1)
      poly4.makeTerm(1, 1.0);
      poly4.makeTerm(0, -1.0);
      System.out.println("Map:  " + poly4.getMap());
      System.out.println("String:  " + poly4.toString());
      System.out.println("Product:  " + poly4.multiply(poly3));   // x^2 + -1 
      
         /*  testing the one-arg constructor  */
      System.out.println("==========================="); 
      Polynomial poly5 = new Polynomial("2x^3 + 4x^2 + 6x^1 + -3");
      System.out.println("Map:  " + poly5.getMap());  
      System.out.println(poly5);
   
   }
}
interface PolynomialInterface
{
   public void makeTerm(Integer exp, Double coef);
   public Map<Integer, Double> getMap();
   public double evaluateAt(double x);
   public Polynomial add(Polynomial other);
   public Polynomial multiply(Polynomial other);
   public String toString();
}

class Polynomial implements PolynomialInterface
{
   public Map<Integer, Double> polyMap;
   public Polynomial() {
      polyMap = new TreeMap<>();
   }
   
   public Polynomial(String inp) {
      polyMap = new TreeMap<>();
      String[] terms = inp.split("[ +]");
      String[] temp;
      for(String s : terms) {
         if(!s.equals("")) {
            temp = s.split("x");
            if(s.indexOf("x") == -1)
               makeTerm(0, Double.parseDouble(s));
            else if(!temp[0].equals("")) {
               makeTerm(Integer.parseInt(temp[1].substring(1)), Double.parseDouble(temp[0]));
            }
            else
               makeTerm(Integer.parseInt(temp[1].substring(1)), 1.0);
         }
         
      }
   }
   public void makeTerm(Integer exp, Double coef) {
      if(coef != 0)
         polyMap.put(exp, coef);
   }
   public Map<Integer, Double> getMap() {
      return polyMap;
   }
   public double evaluateAt(double x) {
      Set<Integer> tempSet = polyMap.keySet();
      double toReturn = 0;
      for(int s : tempSet) {
         toReturn += Math.pow(x, s) * polyMap.get(s);
      }
      return toReturn;
   }
   public Polynomial add(Polynomial other) {
      Polynomial nu = new Polynomial();
      Set<Integer> myEx = polyMap.keySet();
      Set<Integer> otherEx = other.polyMap.keySet();
      for(int x : myEx) {
         if(otherEx.contains(x)) {
            nu.makeTerm(x, polyMap.get(x) + other.polyMap.get(x));
         } 
         else {
            nu.makeTerm(x, polyMap.get(x));
         }
      }
      for(int x : otherEx) {
         if(!myEx.contains(x)) {
            nu.makeTerm(x, other.polyMap.get(x));
         }
      }
      return nu;
   }
   public Polynomial multiply(Polynomial other) {
      Polynomial temp;
      Polynomial toReturn = new Polynomial();
      Set<Integer> myEx = polyMap.keySet();
      Set<Integer> otherEx = other.polyMap.keySet();
      for(int x : myEx) {
         temp = new Polynomial();
         for(int y : otherEx) {
            temp.makeTerm(x + y, polyMap.get(x) * other.polyMap.get(y));
         }
         toReturn = toReturn.add(temp);
      }
      return toReturn;
   }
   public String toString() {
      DecimalFormat dem = new DecimalFormat("###0.##########");
      String toReturn = "";
      Set<Integer> tempEx = polyMap.keySet();
      for(int s : tempEx) {
         if(s == 0) {
            toReturn = toReturn + dem.format(polyMap.get(s));
         } 
         else if(s == 1 && Math.abs(polyMap.get(s)- 1) > 0.0000000001) {
            toReturn = dem.format(polyMap.get(s)) + "x + " + toReturn;
         } 
         else if(Math.abs(polyMap.get(s)- 1) < 0.0000000001 && s != 1) {
            toReturn = "x^" + s + " + " + toReturn;
         } 
         else if(s == 1) {
            toReturn = "x + " + toReturn;
         } 
         else {
            toReturn = dem.format(polyMap.get(s)) + "x^" + s + " + " + toReturn;
         } 
      }
      toReturn = toReturn.trim();
      if(toReturn.charAt(toReturn.length()-1) == '+')
         toReturn = toReturn.substring(0, toReturn.length()-2);
      return toReturn;
   }
   public Polynomial getDeriv() {
      Polynomial deriv = new Polynomial();
      Set<Integer> keys = polyMap.keySet();
      for(Integer i : keys) {
         if(i > 0) {
            deriv.makeTerm(i - 1, i*polyMap.get(i));
         }
      }
      return deriv;
   }
   public Polynomial getInteg() {
      Polynomial integ = new Polynomial();
      Set<Integer> keys = polyMap.keySet();
      for(Integer i : keys) {
         integ.makeTerm(i + 1, (1.0/(i + 1))*polyMap.get(i));
      }
      return integ;
   }
   public Polynomial taylor() {
      double value = evaluateAt(0);
      Polynomial toReturn = new Polynomial();
      for(int x = 0; x < 10; x++) {
         toReturn.add(
      }
   }
   public int factorial(int y) {
      if(y <= 1)
         return 1;
      return y*factorial(y-1);
   }
//    public List<Integer> calcRoots() {
//       Polynomial deriv = calcDeriv();
//       List<Integer> roots = new LinkedList<>();
//       double newx, func, der;
//       double oldx = -100
//       double accuracy = 0.000000001;
//       double accuracy = 0.000000001;
//       for(int x = 0; x < 20; x++) {
//          func = evaluateAt(old);
//          der = deriv.evaluateAt(old);
//          
//          if(Math.abs(der) < 0
//       }
//    }
}


/***************************************  
  ----jGRASP exec: java Polynomial_teacher
 Map:  {0=2, 1=-4, 3=2}
 String:  2x^3 + -4x + 2
 Evaluated at 2.0: 10.0
 -----------
 Map:  {0=-3, 1=-5, 2=1, 4=2}
 String:  2x^4 + x^2 + -5x + -3
 Evaluated at -10.5: -2271.25
 -----------
 Sum: 2x^4 + 2x^3 + x^2 + -9x + -1
 Product:  4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6
 
  ----jGRASP: operation complete.
 ********************************************/