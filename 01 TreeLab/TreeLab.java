// Name: R2-14
// Date: 2/5/2019

import java.util.*;

public class TreeLab
{
   public static TreeNode root = null;
   //public static String s = "XCOMPUTERSCIENCE";
   //public static String s = "XThomasJeffersonHighSchool"; 
   //public static String s = "XAComputerScienceTreeHasItsRootAtTheTop";
   public static String s = "XA";   //comment out lines 44-46 below
   //public static String s = "XAF";  //comment out lines 44-46 below
   //public static String s = "XAFP";  //comment out lines 44-46 below
   //public static String s = "XDFZM";  //comment out lines 44-46 below 
   
   public static void main(String[] args)
   {
      root = buildTree( root, s );
      System.out.print( display( root, 0) );
   
      System.out.print("\nPreorder: " + preorderTraverse(root));
      System.out.print("\nInorder: " + inorderTraverse(root));
      System.out.print("\nPostorder: " + postorderTraverse(root));
   
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Only children = " + countOnlys(root));
      System.out.println("Grandparents = " + countGrandParents(root));
   
      System.out.println("\nHeight of tree = " + height(root));
      System.out.println("Longest path = " + longestPath(root));
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
   
      System.out.println("\nBy Level: ");
      System.out.println(displayLevelOrder(root));
   }
 
   public static TreeNode buildTree(TreeNode root, String s)
   {
      root = new TreeNode("" + s.charAt(1), null, null);
      for(int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, 
            (int)(1 + Math.log(pos) / Math.log(2)));
      //insert(root, "A", 17, 5); 
      //insert(root, "B", 18, 5); 
      //insert(root, "C", 37, 6); //B's right child
      return root;
   }

   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
      {
         if((pos & (1 << k)) == 0)
            p = p.getLeft();
         else
            p = p.getRight();
      }
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   }
   
   private static String display(TreeNode t, int level)
   {
      // turn your head towards left shoulder visualize tree
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
   
   public static String preorderTraverse(TreeNode t)
   { 
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(t.getLeft());   //recurse left
      toReturn += preorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
   public static String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft());
      toReturn += t.getValue() + " ";
      toReturn += inorderTraverse(t.getRight());
      return toReturn;
   }
   
   public static String postorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += postorderTraverse(t.getLeft());
      toReturn += postorderTraverse(t.getRight());
      toReturn += t.getValue() + " ";
      return toReturn;
   }
   
   public static int countNodes(TreeNode t)
   {
      if(t == null)
         return 0;
      return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
   }
   
   public static int countLeaves(TreeNode t)
   {
      if(t == null)
         return 0;
      if(t.getRight() == null && t.getLeft() == null)
         return 1;
      return countLeaves(t.getRight()) + countLeaves(t.getLeft());
   }
   
   /*  there are clever ways and hard ways to count grandparents  */ 
   public static int countGrandParents(TreeNode t)
   {
      if(t == null)
         return 0;
      int temp = 0;
      if(height(t) >= 2)
         temp++;
      if(t.getRight() != null)
         temp += countGrandParents(t.getRight());
      if(t.getLeft() != null)
         temp += countGrandParents(t.getLeft());
      return temp;
   }
   
   public static int countOnlys(TreeNode t)
   {
      if(t == null)
         return 0;
      if(t.getLeft() == null ^ t.getRight() == null)
         return 1 + countOnlys(t.getLeft()) + countOnlys(t.getRight());
      return countOnlys(t.getLeft()) + countOnlys(t.getRight());
   }
   
   /* Returns the max of the heights on both sides of the tree
	 */
   public static int height(TreeNode t)
   {
      if(t == null)
         return -1;
      int temp1 = height(t.getLeft()) + 1;
      int temp2 = height(t.getRight()) + 1;
      return temp1 > temp2 ? temp1 : temp2;
         
   }
   
   /* Returns the max of sum of heights on both sides of tree
	 */   
   public static int longestPath(TreeNode t)
   {
      //Comparable temp = t;
      int path = 0;
      if(t.getRight() != null)
         path += height(t.getRight()) + 1;
      if(t.getLeft() != null)
         path += height(t.getLeft()) + 1;
      return path;
            
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
   @SuppressWarnings("unchecked")
   public static Object min(TreeNode t)
   {
      if(t.getRight() == null && t.getLeft() == null) {
         return t.getValue();
      }
      Comparable left;
      Comparable right;
      Comparable root =  (Comparable)t.getValue();
      Comparable temp;
      
      if(t.getRight() == null ^ t.getLeft() == null) {
         temp = (Comparable)(t.getRight() == null ? min(t.getLeft()) : min(t.getRight()));
         if(temp.compareTo(root) > 0)
            return t.getValue();
         return (Object)temp;
      }
      left = (Comparable)min(t.getLeft());
      right = (Comparable)min(t.getRight());
      if(right.compareTo(left) < 0) {
         temp = right;
      } 
      else {
         temp = left;
      }
      return temp.compareTo(root) > 0 ? t.getValue() : (Object)temp;
      
      
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
   @SuppressWarnings("unchecked")
   public static Object max(TreeNode t)
   {
      if(t == null)
         return null;
      if(t.getRight() == null && t.getLeft() == null)
         return t.getValue();
      Comparable temp = (Comparable)t.getValue();
      Comparable left;
      Comparable right;
      if(max(t.getRight()) == null) {
         left = max(t.getLeft());
         return left.compareTo(temp) ?
   /*
      if(t.getRight() == null && t.getLeft() == null) {
         return t.getValue();
      }
      Comparable left;
      Comparable right;
      Comparable root =  (Comparable)t.getValue();
      Comparable temp;
      
      if(t.getRight() == null ^ t.getLeft() == null) {
         temp = (Comparable)(t.getRight() == null ? max(t.getLeft()) : max(t.getRight()));
         if(temp.compareTo(root) < 0)
            return t.getValue();
         return (Object)temp;
      }
      left = (Comparable)max(t.getLeft());
      right = (Comparable)max(t.getRight());
      if(right.compareTo(left) > 0) {
         temp = right;
      } 
      else {
         temp = left;
      }
      return temp.compareTo(root) < 0 ? t.getValue() : (Object)temp;
      */
   }
      
   /* This method is not recursive.  Use a local queue
    * to store the children of the current TreeNode.
    */
   public static String displayLevelOrder(TreeNode t)
   {
      Queue<TreeNode> q = new LinkedList<>();
      String ret = t.getValue() + "";
      q.add(t.getLeft());
      q.add(t.getRight());
      TreeNode temp;
      while(!q.isEmpty()) {
         temp = q.remove();
         ret += temp.getValue();
         if(temp.getLeft() != null)
            q.add(temp.getLeft());
         if(temp.getRight() != null)
            q.add(temp.getRight());
      }
      return ret;
   }
}

/***************************************************
    ----jGRASP exec: java TreeLab
 		  	E
 		E
 			C
 	M
 			N
 		T
 			E
 C
 			I
 		U
 			C
 	O
 			S
 					C
 				B
 		P
 				A
 			R
 
 Preorder: C O P R A S B C U C I M T E N E C E 
 Inorder: R A P B C S O C U I C E T N M C E E 
 Postorder: A R C B S P C I U O E N T C E E M C 
 
 Nodes = 18
 Leaves = 8
 Only children = 3
 Grandparents = 5
 
 Height of tree = 5
 Longest path = 8
 Min = A
 Max = U
 
 By Level: 
 COMPUTERSCIENCEABC    
 *******************************************************/
