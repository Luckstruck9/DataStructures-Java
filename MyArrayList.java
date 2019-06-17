// MyArrayList.java
// By Davis "Lucky" Lai

import java.util.AbstractList;
import java.lang.IndexOutOfBoundsException;

public class MyArrayList<E> extends AbstractList<E> 
{
  private int size;
  private E[] data;
  private int theSize = 0;
  
  MyArrayList(int startSize)
  {
      int listlength = 2;
      while (listlength<startSize)
      {
	  listlength*=2;
      }
      this.size = listlength;
      this.data = (E[]) new Object[size];
  }
  
  MyArrayList()
  {
      this(2);
  }
  
  private void resize()
  {
      int tempsize = (this.data.length)*2;
      E[] newarray = (E[]) new Object[tempsize];
      for (int i=0; i<this.data.length; i++)
      {
	  newarray[i] = this.data[i];
      }
      this.size*=2;
      this.data = newarray;
  }

  public int size()
  {
      return this.theSize;
  }
  
  public void add(int index, E element)
  {
      if (index>size() || index<0)
	  {
	      System.out.println("You entered a number that was not in the list index");
	      throw new IndexOutOfBoundsException("Please enter a number within the index");
	  }
      int newlength = size();
      if (newlength+1>this.size)
      {
    	  this.resize();
      }
      for (int i=(newlength-1); i>=index; i--)
      {
	  this.data[i+1] = this.data[i];
      }
      this.data[index] = element;
      this.theSize++;
  }
  
  public boolean add(E element)
  {
      int newlength = size();
      this.add(newlength, element);
      return true;
  }
  
  public E get(int index)
  {
      if (index>size()-1 || index<0)
      {
	  System.out.println("You entered a number that was not in the list index");
	  throw new IndexOutOfBoundsException("Please enter a number within the index");
      }
      E temp = data[index];
      return temp;
  }
  
  public E set(int index, E element)
  {
      if (index>size()-2 || index<0)
      {
	  System.out.println("You entered a number that was not in the list index");
	  throw new IndexOutOfBoundsException("Please enter a number within the index");
      }
      E temp = data[index];
      data[index] = element;
      return temp;
  }
  
  public E remove(int index)
  {
      if (index>size()-1 || index<0)
      {
	  System.out.println("You entered a number that was not in the list index");
	  throw new IndexOutOfBoundsException("Please enter a number within the index");
      }
      int savedsize = size()-1;
      E temp = data[index];
      if (index == size()-1)
      {
	  data[index] = null;
      }
      int finalindex = 0;
      for (int i=index; i<size()-1; i++)
      {
	  data[i] = data[i+1];
	  finalindex = i+1;
      }
      data[savedsize] = null;
      this.theSize--;
      return temp;
  }
  
  public boolean isEmpty()
  {
      if (size()==0)
      {
	  return true;
      }
      return false;
  }
  
  public void clear()
  {
      int temp = size();
      for (int i=0; i<temp; i++)
      {
	  data[i] = null;
      }
      this.theSize = 0;
  }
}
