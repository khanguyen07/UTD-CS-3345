//Kha Nguyen
//Project 1 
//CS 3345.002
//KPN170030

import java.util.NoSuchElementException;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class GenLinkedList<T>
{
   private Node<T> head;
   private int size;
   
   public GenLinkedList()
   {
      head = null;
      size = 0;
   }
   
   public int size()
   {
      return size;
   }
   
   /* test method
   public void add(T value)
   {
      head = addAtEnd(head, value);
      size++;
   }
   
   
   private Node<T> addAtEnd(Node<T> node, T value)
   {
      if (node == null)
      {
         return new Node<T>(value, null);
      }
      else if (node.getNext() == null)
      {
         node.setNext(new Node<T>(value, null));
      }
      else
      {
         addAtEnd(node.getNext(), value);
      }
      return node;
   }*/
   
   // a.addFront: add to front of list
   public void addFront(T value)
   {
      if(head == null)
      {
         head = new Node<T>(value, null);
      }
      else
      {
         Node<T> node = new Node<T>(value, null);
         node.next = head;
         head = node;
      }
      size++;
   }
   
   // b.addEnd: add to end of list
   public void addEnd(T value)
   {
      if(head == null)
      {
         head = new Node<T>(value, null);
      }
      else 
      {
         Node<T> node = head;
         while(node.getNext()!=null)
         {
            node = node.getNext();
         }
         node.setNext(new Node<T>(value, null));
      }
      size++;
   }
   
  // c.removeFront: remove from front of list
   public void removeFront()
   {
      if(head == null)
      {
         throw new NoSuchElementException();
      }
      Node<T> tempNode = head;
      head = head.next;
      size--;
   }
   
   // d.removeEnd: remove front end of list
   public void removeEnd()
   {
      if(head == null)
      {
         throw new NoSuchElementException();
      }
      else
      {
         Node<T> n1 = null;
         Node<T> n2 = head;
         
            while(n2.getNext() != null)
            {
               n1 = n2;
               n2 = n2.getNext();
            }
         n1.next = null;
      }
      size --;  
   }
   
   // e.set: set the element at position
   public void set(T value, int position)
   {
      if(position < 0 || position > size)
         throw new ArrayIndexOutOfBoundsException("Index " + position + "; size " + size); 
      Node<T> tempNode = head;
      for(int k = 0; k<position; k++)
      {
         tempNode = tempNode.next;
      }
      tempNode.value = value;
      
   }
   
   // f.get: get element at position
   public T get(int position)
   {
      if(position < 0 || position > size)
         throw new IndexOutOfBoundsException("Index " + position + "; size " + size);
      Node<T> tempNode = head;
      for(int k = 0; k<position; k++)
      {
         tempNode = tempNode.next;
      }
      return tempNode.value;
   }
   
   // g.swap: swap 2 elements in list
   public void swap(int position1, int position2)
   {
      if(position1 < 0 || position1 > size || position2 < -1 || position2 > size)
         throw new IndexOutOfBoundsException("Index 1: " + position1 + "Index 2: " + position2 + "; size " + size);
      if(position1 == position2)
      {
        System.out.println("Same index");
      }
      
      Node<T> node1 = head;
      Node<T> node2 = head;
     
      for(int k = 0; k<position1; k++)
      {
         node1 = node1.next;
      }
      
      for(int k2 = 0; k2<position2; k2++)
      {
         node2 = node2.next;
      }
     
      T data = node1.value;
      node1.value = node2.value;
      node2.value = data;

   }
   
   //h.shift: shift the list by an integer
   public void shift(int number)
   {
      if(number == 0 || number > size || head == null || head.next == null)
      {
         return;
      }
         
      else if(number < 0)
      {  
         Node<T> oldHead = head;
         head = head.next;
         getEndNode().next = oldHead;
         oldHead.next = null;
         shift(number + 1);
      }
      
      else
      {
         Node<T> oldHead = head;
         Node<T> prev = getSecondToEndNode();
         head = getEndNode();
         head.next = oldHead;
         prev.next = null;
         shift(number - 1);
      }
      
   }
   
   // i. removeMatching: remove matching value in list
   public void removeMatching(T value)
   {      
      if(head == null)
      {
         return;
      }
      
      Node<T> prev = head;
      Node<T> curr = head;
      
      while(curr.next != null)
      {
         if(curr.value == value)
         {
            if(curr == head)
            {
               head = curr.next;
               curr = head;
            }
            else
            {
               prev.next = curr.next;
               curr = prev.next;
            }
         }
         else
         {
            prev = curr;
            curr = curr.next;
         }
      }
      
      size--;
   }
   
   // j. erase : remove number of elements from position
   public void erase(int position, int numberOfElements)
   {
      if(position < 0 || position > size || (position+numberOfElements)>size || numberOfElements<=0 || numberOfElements > size)
      {
       System.out.println("Please input number within size.");
       return;
      }
      
      Node<T> p = head;
      Node<T> prev = null;
     
      while(position-- != 0)
      {
         prev = p;
         p = p.next;
         
      }     
      
      Node<T> temp = prev;
      while(numberOfElements-- != 0)
      {
         if(temp == null)
            break;
         temp = temp.next;
         size--;
        
      }
      if(temp == null)
      {
         prev.next=null;
         
      }
      else prev.next = temp.next;
   }
   
   // i.insertList insert a list from a position
   public void insertList(int position, List<T> list)
   {
      if(position < 0 || position > size)
         throw new IndexOutOfBoundsException("For index, " + position);
      
      if(list.isEmpty())
         return;
      if(head == null)
      {
         Node<T> node = new Node<T>(list.get(0));
         head = node;
         for(int k = 1; k < list.size(); k++)
         {
            node.next = new Node<T>(list.get(k));
            node = node.next;
         }
      }
      else if (position == 0)
      {
         Node<T> node = new Node<T>(list.get(0));
         Node<T> oldHead = head;
         head = node;
         for(int k = 1; k < list.size(); k++)
         {
            node.next = new Node<T>(list.get(k));
            node = node.next;
         }
         node.next = oldHead;
      }
      else
      {
         Node<T> begin = getNode(position);
         Node<T> oldNext = begin.next;
         for(T item : list)
         {
            Node<T> node = new Node(item);
            begin.next = node;
            begin = node;
         }
         begin.next = oldNext;
      }
      
   }
   
   // miscellaneous methods including toString to print out list and getNode to assist with the above methods
   
   public String toString()
   {
      return toString(head);
   }
      
   private String toString(Node<T> node)
   {
      if(node == null)
      {
         return "";
      }
      else 
      {
         return node.getValue() + " | " + toString(node.getNext());
      }
   }
   
   private Node<T> getNode(int index) throws IndexOutOfBoundsException
   {
      return getNodeAfter(index, head);
      
   }
   
   private Node<T> getNodeAfter(int index, Node<T> start)
   {
      if(index<0 || start == null)
      {
         throw new IndexOutOfBoundsException("Index: " + index);
      }
      
      int pos = 0;
      while(index != pos)
      {
         if(start.next == null)
            throw new IndexOutOfBoundsException("Index: " + index);
         start = start.next;
         pos++;
      }
      return start;
   }
   
   private Node<T> getSecondToEndNode()
   {
      Node<T> prev = head;
      while(prev != null && prev.next!= null && prev.next.next != null)
         prev=prev.next;
      return prev;
   }
   
   private Node<T> getEndNode()
   {
      Node<T> end = head;
      while(end != null && end.next != null)
         end = end.next;
      return end;
   }
   
   public static void main(String[] args)
   {
      GenLinkedList<String> list1 = new GenLinkedList<String>();
      
      //I chose the first index of the list to start from 0
      //So first element has index of 0, 2nd element has index of 1, and so on....
      
      list1.addEnd("hello");
      list1.addEnd("world");
      list1.addFront("Hello");
      list1.addFront("Kha");
      list1.addFront("Kha");
      list1.addFront("Kha");
      list1.addFront("Alice");
      list1.addEnd("hi");
      list1.addEnd("Kha");
      list1.addEnd("foo");
      list1.addEnd("world");
      
      System.out.println("list 1 = " + list1);
      
      list1.removeFront();     
      System.out.println("After remove front");
      System.out.println("list 1 = " + list1);  
      
      list1.removeEnd(); 
      System.out.println("After remove end");
      System.out.println("list 1 = " + list1);
      
      list1.set("Test",3);
      System.out.println("After set node 3 = Test");
      System.out.println("list 1 = " + list1);
      list1.set("Test 2",2);
      System.out.println("After set node 2 = Test2");
      System.out.println("list 1 = " + list1);
      
      int pos = 5;
      System.out.println("Value at position " + pos + " is " + list1.get(pos));
      int pos2 = 3;
      System.out.println("Value at position " + pos2 + " is " + list1.get(pos2));
      
      list1.swap(2,4);
      System.out.println("After swap 2 & 4");
      System.out.println("list 1 = " + list1);
      
      list1.swap(1,5);
      System.out.println("After swap 1 & 5");
      System.out.println("list 1 = " + list1);
      
      list1.removeMatching("hello");
      System.out.println("After remove hello");
      System.out.println("list 1 = " + list1);
      
      list1.removeMatching("Kha");
      System.out.println("After remove Kha");
      System.out.println("list 1 = " + list1);
      
      ArrayList<String> arrayList = new ArrayList<String>();
      arrayList.add("Item 1");
      arrayList.add("Item 2");
      arrayList.add("Item 3");
      
      list1.insertList(1,arrayList);
      System.out.println("After insert list from position 1  ");
      System.out.println("list 1 = " + list1);
      
      list1.shift(3);
      System.out.println("After shift forward by 3 ");
      System.out.println("list 1 = " + list1);
      
      list1.shift(-5);
      System.out.println("After shift backward by 5 ");
      System.out.println("list 1 = " + list1);
      
      list1.erase(1,5);
      System.out.println("After erase 5 elements from position 1  ");
      System.out.println("list 1 = " + list1);
   }
}


// Node class
class Node<T>
{
   T value;
   Node<T> next;
   
   public Node(T value2, Node<T> next2)
   {
      value = value2;
      next = next2;
   }
   
   public Node(T value)
   {
      this(value,null);
   }
   
   public String toString()
   {
      return value.toString();
   }
   
   public T getValue()
   {
      return value;
   }
   
   public Node<T> getNext()
   {
      return next;
   }
   
   public void setValue(T value2)
   {
      value = value2;
   }
   
   public void setNext(Node<T> next2)
   {
      next = next2;
   }
   
   
}