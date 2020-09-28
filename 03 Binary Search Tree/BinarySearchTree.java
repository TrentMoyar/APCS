// Name: R2-14
// Date: 2/14/19

import java.util.*;

/*  Make an ArrayList of input strings.  Build the Binary 
 *  Search Trees (using TreeNodes) from the letters in the 
 *  string.   Display it as a sideways tree (take the code 
 *  from TreeLab).  Prompt the user for a target and search 
 *  the BST for it.  Display the tree's minimum and maximum 
 *  values. Print the letters in order from smallest to largest.
 */

public class BinarySearchTree
{
   public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);
      ArrayList<String> str = new ArrayList<String>();
      str.add("MAENIRAC");
      str.add("AMERICAN");
      str.add("AACEIMNR");
      str.add("A");
      str.add("6829301");
   
      for( String s : str )
      {
         System.out.println("String: "  + s);
         TreeNode root = null;
         root = buildTree( root, s );
         System.out.println( display(root, 0) );
         System.out.print("Input target: ");
         String target =  keyboard.next();    //"I"
         boolean itemFound = find(root, target);
         if(itemFound)
            System.out.println("found: " + target);
         else
            System.out.println(target +" not found.");
         
         System.out.println("Min = " + min(root));
         System.out.println("Max = " + max(root));	
      
         System.out.print("In Order: ");
         System.out.println( smallToLarge(root) );
         System.out.println("\n---------------------");
      }
   }
   
   /* @param str string of characters
    */
   public static TreeNode buildTree(TreeNode t, String str)
   {
      String[] temp = str.split("");
      TreeNode root = t;
      for(int i = 1; i < temp.length; i++) {
         insert(root, temp[i]);
      }
      return root;
   }
   
   /* Recursive algorithm to build a BST:  if the node is 
    * null, insert the new node.  Else, if the item is less,
    * set the left node and recur to the left.  Else, if the 
    * item is greater, set the right node and recur to the 
    * right.   
    * @param s one letter to be inserted
    */
   public static TreeNode insert(TreeNode t, String s)
   {  
      TreeNode temp;
      if(t == null) {
         t = new TreeNode(s);
         temp = t;
      }
      else if(s.compareTo(t.getValue() + "") <= 0)
         temp = insert(t.getLeft(), s);
      else
         temp = insert(t.getRight(), s);
      return temp;
   }
   
   /* Copy the code that is in TreeLab  
    */
   public static String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   /* Iterative algorithm:  create a temporary pointer p at 
    * the root. While p is not null, if the p's value equals 
    * the target, return true. If the target is less than the 
    * p's value, go left, otherwise go right. If the target 
    * is not found, return false. Find the target. Recursive 
    * algorithm:  If the tree is empty, return false.  If the 
    * target is less than the current node value, return the 
    * left subtree.  If the target is greater, return the right 
    * subtree.  Otherwise, return true.   
    */    
   public static boolean find(TreeNode t, Comparable x)
   {
      return false;
   }
      
   /*	Starting at the root, return the min value in the BST.   
    *	Use iteration.   Hint:  look at several BSTs. Where are 
    *	the min values always located?  
    */
   public static Object min(TreeNode t)
   {
      return null;
   }
      
   /* Starting at the root, return the max value in the BST.  
    * Use recursion!
    */
   public static Object max(TreeNode t)
   {
      return null;
   }
   
   public static String smallToLarge(TreeNode t)
   {
      return null;
   }
}


/***************************************
 String: MAENIRAC
 		R
 	N
 M
 			I
 		E
 			C
 	A
 		A
 Input target: I
 found: I
 Min = A
 Max = R
 In Order: A A C E I M N R 
 ---------------------
 String: AMERICAN
 		R
 			N
 	M
 			I
 		E
 			C
 A
 	A
 Input target: I
 found: I
 Min = A
 Max = R
 In Order: A A C E I M N R 
 ---------------------
 String: AACEIMNR
 						R
 					N
 				M
 			I
 		E
 	C
 A
 	A
 Input target: i
 i not found.
 Min = A
 Max = R
 In Order: A A C E I M N R 
 ---------------------   
 ************************************/