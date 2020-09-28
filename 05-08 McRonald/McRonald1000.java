// Name: R2-14
// Date: 1/24/2018

import java.util.*;

public class McRonald1000 {
   public static final int TIME = 1080; // 18 hrs * 60 min
   public static final int DAYS = 1000;
   
   public static void main(String[] args) {
      int longestWait = 0;
      int longestQueue = 0;
      int queueSize = 0;
      int maxWait = 0;
      int minutes = 0;
      int totalCustomers = 0;
      int largestDay = 0;
      int temp;
      Queue<Customer> queue = new LinkedList<>();
      Queue<Integer> waits = new LinkedList<>();
      for(int i = 0; i < DAYS; i++) {
         minutes = 0;
         queue.clear();
         queueSize = 0;
         while (minutes < 1080 || !queue.isEmpty()) {
            if (Math.random() < 0.2 && minutes < TIME) {
               queue.add(new Customer(minutes));
            }
            if (!queue.isEmpty()) {
               if(queue.peek().wait > 0) {
                  queue.peek().wait--;
               } 
               else {
                  waits.add(queue.remove().totalWait(minutes - 1));
                  queueSize++;
               }
            }
            if (queue.size() > longestQueue) {
               longestQueue = queue.size();
            }
            minutes++;
         }
         if(queueSize > largestDay)
            largestDay = queueSize;
         totalCustomers += queueSize;
      }
      while (!waits.isEmpty()) {
            temp = waits.remove();
            maxWait += temp;
            if (longestWait < temp)
               longestWait = temp;
      }
      System.out.println("Total customers served = " + totalCustomers);
      System.out.println("Average wait time = " + (double) maxWait / totalCustomers);
      System.out.println("Longest wait time = " + longestWait);
      System.out.println("Longest queue = " + longestQueue);
      System.out.println("Average served per day = " + (double)totalCustomers / DAYS);
      System.out.println("Largest day = " + largestDay);
   }

   // public static void display(Queue<Integer> q, int min) //if you are storing arrival times
   public static void display(Queue<Customer> q, int min) // if you have a Customer class
   {
      System.out.println(min + ": " + q);
   }
}


class Customer // if you want a Customer class
{
   private int myTime;
   public int wait;

   public Customer(int time) {
      myTime = time;
      wait = (int) (Math.random() * 6 + 2);
   }

   public int getTime() {
      return myTime;
   }

   public String toString() {
      return "" + myTime;
   }

   public int totalWait(int f) {
      return f - myTime;
   }
}
