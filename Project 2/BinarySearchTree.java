// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
 
 
 
//Kha Nguyen
//CS 3345.002
//Project 2
//KPN170030
 
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
 
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<AnyType>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.print(t.element + " | " );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
        BinaryNode<AnyType> parent;
        
        public AnyType getData()
        {
            return element;
        }
        
        public void setData(AnyType d)
        {
            element = d;
        }
        
        public BinaryNode<AnyType> getLeft()
        {
            return left;
        }
        
        public void setLeft(BinaryNode<AnyType> l)
        {
            left = l;
        }
        
        public BinaryNode<AnyType> getRight()
        {
            return right;
        }
        
        public void setRight(BinaryNode<AnyType> r)
        {
            right = r;
        }
        
        public void setParent(BinaryNode<AnyType> p)
        {
            parent = p;
        }
        
        public BinaryNode<AnyType> getParent()
        {
            return parent;
        }
      
    }

    public static class UnderflowException extends RuntimeException
    {
        /**
         * Construct this exception object.
         * @param message the error message.
         */
        public UnderflowException()
        {
            super();
        }
    }

      /** The tree root. */
    private BinaryNode<AnyType> root;
    
    public void setRoot(BinaryNode<AnyType> node)
    {
      root = node;
    }
    
    public BinaryNode<AnyType> getRoot()
    {
      return root;
    }


   // a) nodeCount
   public int nodeCount()
   {
      return nodeCount(root);
   }
   
   private int nodeCount(BinaryNode<AnyType> node)
   {
      if(node==null)
         return 0;
      else 
         return 1 + nodeCount(node.left) + nodeCount(node.right);
         
   }
  
   // b) isFull 
   public boolean isFull()
   {
      return isFull(root);
   }
   
   private boolean isFull(BinaryNode<AnyType> node)
   {
      if(node == null)
         return false;
      else if(node.left == null && node.right == null)
         return true;
      else
         return isFull(node.left) && isFull(node.right);
      
   }
   
   // c) compareStructure
   public boolean compareStructure(BinarySearchTree<AnyType> tree2)
   {
      return compareStructure(this.root, tree2.root);
   }
   
   private boolean compareStructure(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2)
   {
      boolean leftChild, rightChild;
      if(node1.left == null && node2.left == null)
         leftChild = true;
      else if(node1.left != null && node2.left != null)
         return compareStructure(node1.left, node2.left);
      else leftChild = false;
      
      if(node1.right == null && node2.right == null)
         rightChild = true;
      else if(node1.right != null && node2.right != null)
         return compareStructure(node1.right, node2.right);
      else rightChild = false;
      
      return leftChild && rightChild;      
      
   }
   
   // d) equals
   public boolean equals(BinarySearchTree<AnyType> tree2)
   {
      return equals(this.root, tree2.root);
   }
   
   public boolean equals(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2)
   {
      if(node1 == null && node2 == null)
         return true;
      
      if(node1 != null && node2 != null)
         return (node1.element == node2.element && equals(node1.left, node2.left) && equals(node1.right, node2.right));
         
      return false; 
   }

   // e) copy
   public BinarySearchTree<AnyType> copy(BinarySearchTree<AnyType> tree2)
   {
      BinarySearchTree<AnyType> newTree = new BinarySearchTree<AnyType>();
      
      if(root == null)
         return newTree;
      else
         return copy(root, newTree);
   }
   
   private BinarySearchTree<AnyType> copy(BinaryNode<AnyType> node, BinarySearchTree<AnyType> tree2)
   {
      if(node != null)
      {
         tree2.insert(node.getData());
         copy(node.getLeft(), tree2);
         copy(node.getRight(), tree2);
      }   
      
      return tree2;
         
   }   
   


    // f) mirror
    public void mirror()   
    {
      mirror(root);
    }
    
    public void mirror(BinaryNode<AnyType> node)
    {
      if(node != null)
      {
         BinaryNode<AnyType> tmp = node.left;
         node.left = node.right;
         node.right = tmp;
         mirror(node.right);
         mirror(node.left);
      }
      return;
    }
    
    
    // g) isMirror
    
    public boolean isMirror(BinarySearchTree<AnyType> tree2)
    {
      return isMirror(this.root, tree2.root);
    }
    
    private boolean isMirror(BinaryNode<AnyType> a, BinaryNode<AnyType> b)
    {
      if(a == null && b == null)
         return true;
      
      if(a == null || b == null)
         return false;
      
      return a.element == b.element && isMirror(a.left, b.right) && isMirror(a.right, b.left);
         
      
    }
    
    
    // methods to locate node
    public BinaryNode<AnyType> nodeLocate(AnyType val, BinaryNode<AnyType> node)
    {
      int compareResult = val.compareTo(node.element);
      
      if(node.element.equals(val))
         return node;
      else if(node.left.element.equals(val))
         return node.left;
      else if(node.right.element.equals(val))
         return node.right;
      else
      {
         nodeLocate(val, node.left);
         nodeLocate(val, node.right);
      }
      return null;
      
    }
    
    // methods to locate node
    public BinaryNode<AnyType> nodeLocateLeft(AnyType val, BinaryNode<AnyType> node)
    {
      if(node.left.element == val)
         return node;
      else
      {
         nodeLocate(val, node.left);
         nodeLocate(val, node.right);
      }
      return null;
    }
    
    // methods to locate node
    public BinaryNode<AnyType> nodeLocateRight(AnyType val, BinaryNode<AnyType> node)
    {
      if(node.right.element == val)
         return node;
      else
      {
         nodeLocate(val, node.left);
         nodeLocate(val, node.right);
      }
      return null;
    }
    
    /* test
    public void rotateRight(AnyType val)
    {
      BinaryNode<AnyType> nodep = nodeLocateRight(val, root);
      BinaryNode<AnyType> node = nodeLocate(val, root);
      BinaryNode<AnyType> nodey = node.left;
      nodep.right = nodey;
      node.left = nodey.right;
      nodey.right = node;
    }
    
   
    public void rotateLeft(AnyType val)
    {
      BinaryNode<AnyType> nodep = nodeLocateLeft(val, root);
      BinaryNode<AnyType> node = nodeLocate(val, root);
      BinaryNode<AnyType> nodey = node.right;
      nodep.left = nodey;
      node.right = nodey.left;
      nodey.left = node;
    }*/
    
    private BinaryNode<AnyType> find(Comparable x, BinaryNode<AnyType> t)
    {
      if(t==null)
         return null;
      if(x.compareTo(t.element)<0)
         return find(x,t.left);
      else if(x.compareTo(t.element)>0)
         return find(x,t.right);
      else return t;
    
    
    }
    
    public Comparable find(Comparable x)
    {
      return elementAt(find(x,root));
    }
    
    private Comparable elementAt(BinaryNode<AnyType> t)
    {
      return t == null ? null : t.element;
    }
    
    
     // h) rotateRight
    public void rotateRight(BinaryNode<AnyType> x)
    {
      BinaryNode<AnyType> y = x.left;
      if(y == null)
      {
         System.out.println("The tree cannot be right rotated");
         return;
      }
      
      x.setLeft(y.right);
      if(y.right != null)
      {
         y.right.setParent(x);
      }
      
      y.setParent(x.parent);
      if(x.parent == null)
      {
         setRoot(y);
      }
      else if(x==x.parent.left)
      {
         x.parent.setLeft(y);
      }
      else
      {
         x.parent.setRight(y);
      }
      
      y.setRight(x);
      x.setParent(y);
    }



    // g) rotateLeft
    public void rotateLeft(BinaryNode<AnyType> x)
    {
      BinaryNode<AnyType> y = x.right;
      if(y == null)
      {
         System.out.println("The tree cannot be left rotated");
         return;
      }
      
      x.setRight(y.left);
      if(y.left != null)
      {
         y.left.setParent(x);
      }
      
      y.setParent(x.parent);
      if(x.parent == null)
      {
         setRoot(y);
      }
      else if(x==x.parent.left)
      {
         x.parent.setLeft(y);
      }
      else
      {
         x.parent.setRight(y);
      }
      
      y.setLeft(x);
      x.setParent(y);
    }
    
       
    

   // i) printLevels  
   public void printLevels()
    {
        Queue<BinaryNode<AnyType>> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty())
        {
            BinaryNode<AnyType> node = queue.remove();
            if(node == null)
                continue;
            System.out.print(node.element +" ");
            queue.add(node.left);
            queue.add(node.right);
        }
        System.out.println();
    }

    // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
        BinarySearchTree<Integer> t2 = new BinarySearchTree<Integer>();
        BinarySearchTree<Integer> t3 = new BinarySearchTree<Integer>();
        final int NUMS = 40;
        final int GAP  = 33;
        final int GAP2 = 33;
        final int GAP3 = 35;
        
        for(int i = GAP; i != 0; i = (i + GAP) % NUMS )
        {
           t.insert(i);
        }
        
        for( int i = 0; i < NUMS; i+=3 )
        {
            t.remove(i);
        }
        
        for(int i = GAP2; i != 0; i = (i + GAP2) % NUMS )
        {
           t2.insert(i);
        }
        
        for( int i = 0; i < NUMS; i+=3 )
        {
            t2.remove(i);
        }
        
        for(int i = GAP3; i != 0; i = (i + GAP3) % NUMS )
        {
           t3.insert(i);
        }
        
        for( int i = 0; i < NUMS; i+=3 )
        {
            t3.remove(i);
        }
        
        System.out.print("t: "); t.printTree();
        System.out.println();
        System.out.print("t2: "); t2.printTree();
        System.out.println();
        System.out.print("t3: "); t3.printTree();
        System.out.println();
        System.out.println("t node count: " + t.nodeCount()); 
        System.out.println("t is full? " + t.isFull());
        System.out.println("Compare t & t2: " + t.compareStructure(t2)); 
        System.out.println("Equal t & t2: " + t.equals(t2)); 
        
        System.out.print("t mirror: "); 
        t.mirror();
        t.printTree();    
        
   
        System.out.println();
        System.out.print("t.printLevels(): "); t.printLevels();
       
        System.out.println("Is t3 mirror t: " + t.isMirror(t3));
        
   
      
    }
}