package com.example.waseemraza.assignemnt_2_st;
import java.util.*;

public class BST <T extends Comparable<T>> implements Iterable<T>
{
   public static void main(String[] args)
   {
      Integer[] a = {1,5,2,7,4};
      BST<Integer> bst = new BST<Integer>();
      for(Integer n : a) bst.insert(n);

      bst.preOrderTraversal();
      System.out.println();

      //testing comparator
      //build a mirror BST with a rule:  Left > Parent > Right
      //code for the comparator at the bottom of the file
      bst = new BST<Integer>();        //new MyComp1()
      for(Integer n : a) bst.insert(n);

      bst.preOrderTraversal();
      System.out.println();
      bst.inOrderTraversal();
      System.out.println();

      for(Integer n : bst) System.out.print(n);
      System.out.println();

      System.out.println(bst);

      bst.preOrderTraversal();
      System.out.println();
      bst.inOrderTraversal();
      System.out.println();

      //testing diameter
      System.out.println("diameter = " + bst.diameter());
   }


   public Node<T> root;
   public Comparator<T> comparator;

   public BST()
   {
      root = null;
      comparator = null;
   }

   public BST(Comparator<T> comp)
   {
      root = null;
      comparator = comp;
   }

   private int compare(T x, T y)
   {
      if(comparator == null) return x.compareTo(y);
      else
      return comparator.compare(x,y);
   }

/*****************************************************
*
*            INSERT
*
******************************************************/
   public void insert(T data)
   {
      root = insert(root, data);
   }
   private Node<T> insert(Node<T> p, T toInsert)
   {
      if (p == null)
         return new Node<T>(toInsert);

      if (compare(toInsert, p.data) == 0)
      	return p;

      if (compare(toInsert, p.data) < 0)
         p.left = insert(p.left, toInsert);
      else
         p.right = insert(p.right, toInsert);

      return p;
   }

/*****************************************************
*
*            SEARCH
*
******************************************************/
   public boolean search(T toSearch)
   {
      return search(root, toSearch);
   }
   private boolean search(Node<T> p, T toSearch)
   {
      if (p == null)
         return false;
      else
      if (compare(toSearch, p.data) == 0)
      	return true;
      else
      if (compare(toSearch, p.data) < 0)
         return search(p.left, toSearch);
      else
         return search(p.right, toSearch);
   }

/*****************************************************
*
*            DELETE
*
******************************************************/

   public void delete(T toDelete)
   {
      root = delete(root, toDelete);
   }
   private Node<T> delete(Node<T> p, T toDelete)
   {
      if (p == null)  throw new RuntimeException("cannot delete.");
      else
      if (compare(toDelete, p.data) < 0)
      p.left = delete (p.left, toDelete);
      else
      if (compare(toDelete, p.data)  > 0)
      p.right = delete (p.right, toDelete);
      else
      {
         if (p.left == null) return p.right;
         else
         if (p.right == null) return p.left;
         else
         {
         // get data from the rightmost node in the left subtree
            p.data = retrieveData(p.left);
         // delete the rightmost node in the left subtree
            p.left =  delete(p.left, p.data) ;
         }
      }
      return p;
   }
   private T retrieveData(Node<T> p)
   {
      while (p.right != null) p = p.right;

      return p.data;
   }

/*************************************************
 *
 *            toString
 *
 **************************************************/

   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      for(T data : this) sb.append(data.toString());

      return sb.toString();
   }

/*************************************************
 *
 *            TRAVERSAL
 *
 **************************************************/

   public void preOrderTraversal()
   {
      preOrderHelper(root);
   }
   private void preOrderHelper(Node r)
   {
      if (r != null)
      {
         System.out.print(r+" ");
         preOrderHelper(r.left);
         preOrderHelper(r.right);
      }
   }

   public void inOrderTraversal()
   {
      inOrderHelper(root);
   }
   private void inOrderHelper(Node r)
   {
      if (r != null)
      {
         inOrderHelper(r.left);
         System.out.print(r+" ");
         inOrderHelper(r.right);
      }
   }

/*************************************************
 *
 *            CLONE
 *
 **************************************************/

   public BST<T> clone()
   {
      BST<T> twin = null;

      if(comparator == null)
         twin = new BST<T>();
      else
         twin = new BST<T>(comparator);

      twin.root = cloneHelper(root);
      return twin;
   }
   private Node<T> cloneHelper(Node<T> p)
   {
      if(p == null)
         return null;
      else
         return new Node<T>(p.data, cloneHelper(p.left), cloneHelper(p.right));
   }

/*************************************************
 *
 *            MISC
 *
 **************************************************/

   public int height()
   {
      return height(root);
   }
   private int height(Node<T> p)
   {
      if(p == null) return -1;
      else
      return 1 + Math.max( height(p.left), height(p.right));
   }

   public int countLeaves()
   {
      return countLeaves(root);
   }
   private int countLeaves(Node<T> p)
   {
      if(p == null) return 0;
      else
      if(p.left == null && p.right == null) return 1;
      else
      return countLeaves(p.left) + countLeaves(p.right);
   }

   //The diameter of a tree is the number of nodes
   //on the longest path between two leaves in the tree.
   public int diameter()
   {
      return diameter(root);
   }
   private int diameter(Node<T> p)
   {
      if(p==null) return 0;

      //the path goes through the root
      int len1 = height(p.left) + height(p.right) +3;

      //the path does not pass the root
      int len2 = Math.max(diameter(p.left), diameter(p.right));

      return Math.max(len1, len2);
   }


/*****************************************************
*
*            TREE ITERATOR
*
******************************************************/

   public Iterator<T> iterator()
   {
      return new MyIterator();
   }
   //pre-order
   private class MyIterator implements Iterator<T>
   {
      Stack<Node<T>> stk = new Stack<Node<T>>();

      public MyIterator()
      {
         if(root != null) stk.push(root);
      }
      public boolean hasNext()
      {
         return !stk.isEmpty();
      }

      public T next()
      {
         Node<T> cur = stk.peek();
         if(cur.left != null)
         {
            stk.push(cur.left);
         }
         else
         {
            Node<T> tmp = stk.pop();
            while( tmp.right == null )
            {
               if(stk.isEmpty()) return cur.data;
               tmp = stk.pop();
            }
            stk.push(tmp.right);
         }

         return cur.data;
      }//end of next()
   }//end of MyIterator

}//end of BST