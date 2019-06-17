// MyLinkedList.java
// By Lucky Lai and Charles Cui

import java.util.*;

public class MyLinkedList<T> extends AbstractList<T>  {
    Node Head;
    Node Tail;
    int size;
    int modcount;
    
    protected class Node 
    {
        T data;
        Node next;
        Node prev;
    }
    
    public MyLinkedList () 
    {
	this.Head = new Node();
	this.Tail = new Node();
	this.Head.next = Tail;
	this.Tail.prev = Head;
        this.size = 0;
        this.modcount=0;
    }
    
    private Node getNth(int index)
    {
	Node temp = this.Head;
	for (int i = 0; i <= index; i++) {
	    temp=temp.next;
	}
	return temp;
    }
    
    public boolean add(T data)
    {
    	add(this.size, data);
    	return true;
    }
    
    public void add(int index, T data)
    {
    	if (data==null)
    	{
    	    throw new NullPointerException();
    	}
    	if (index > size)
    	{
    	    throw new IndexOutOfBoundsException();
    	}
    	Node temp = Head;
    	for (int i=0; i<index; i++)
    	{
    	    temp = temp.next;
    	}
    	Node addme = new Node();
    	addme.data = data;
    	Node afterbuffer = temp.next;
    	temp.next = addme;
    	addme.next = afterbuffer;
    	addme.prev = temp;
    	afterbuffer.prev = addme;
    	this.size++;
    	modcount++;
    }
    
    public T get (int index)
    {
	if (index >= size)
	{
	    throw new IndexOutOfBoundsException();
	}
	Node temp = getNth(index);
	return temp.data;
    }
    
    public T set (int index, T data)
    {
	if (index >= size)
	{
	    throw new IndexOutOfBoundsException();
	}
	if (data==null)
	{
	    throw new NullPointerException();
	}
	Node temp = Head;
	for (int i=0; i<=index; i++)
	{
	    temp = temp.next;
	}
	T databuffer = temp.data;
	temp.data = data;
	modcount++;
	return databuffer;
    }
    
    public T remove(int index)
    {
	Node temp = Head;
	for (int i=0; i<=index; i++)
	{
	    temp = temp.next;
	}
	temp.prev.next = temp.next;
	temp.next.prev = temp.prev;
	this.size--;
	modcount++;
	return temp.data;
    }
    
    public void clear()
    {
	Tail.prev = Head;
	Head.next = Tail;
	this.size = 0;
	modcount++;
    }
    
    public boolean isEmpty()
    {
	if (Tail.prev==Head && Head.next==Tail && size==0)
	{
	    return true;
	}
	return false;
    }

    public int size() 
    {
	return this.size;
    }
    
    @Override
    public ListIterator<T> listIterator()
    {
		MyLinkedListIterator newlistiterator = new MyLinkedListIterator();
		return newlistiterator;
    }
    
    @Override
    public Iterator<T> iterator()
    {
		MyLinkedListIterator newiterator = new MyLinkedListIterator();
		return newiterator;
    }
    
    class MyLinkedListIterator implements ListIterator<T> 
    {
	Node current = Head;
	int currentindex = -1;
	Boolean nextvprev;
	int modcount = 0;
	
	@Override
	public void add(T item) 
	{
	    MyLinkedList.this.add(currentindex+1, item);
	    this.nextvprev = null;
	    modcount++;
	}

	@Override
	public boolean hasNext() 
	{
	    if (current.next==Tail)
	    {
		return false;
	    }
	    return true;
	}

	@Override
	public boolean hasPrevious() {
	    if (current!=Head && current.data!=null)
	    {
		return true;
	    }
	    return false;
	}

	@Override
	public T next() {
	    current = current.next;
	    this.currentindex++;
	    this.nextvprev = true;
	    return current.data;
	}

	@Override
	public int nextIndex() {
	    if (this.currentindex==size)
	    {
		return size;
	    }
	    return this.currentindex+1;
	}

	@Override
	public T previous() {
	    if (current==Head)
	    {
			throw new NoSuchElementException();
	    }
	    T buffer = current.data;
	    current = current.prev;
	    this.nextvprev = false;
	    this.currentindex--;
	    return buffer;
	}

	@Override
	public int previousIndex() {
	    return this.currentindex;
	}

	@Override
	public void remove() 
	{
	    current = current.prev;
	    if (this.nextvprev == null)
	    {
			throw new IllegalStateException();
	    }
	    else if (this.nextvprev == true)
	    {
			MyLinkedList.this.remove(currentindex);
	    }
	    else
	    {
			MyLinkedList.this.remove(currentindex+1);
	    }
	    this.nextvprev = null;
	    currentindex--;
	    modcount++;
	}

	@Override
	public void set(T x)  
	{
	    if (this.nextvprev==null)
	    {
		throw new IllegalStateException();
	    }
	    else if (this.nextvprev==true)
	    {
		MyLinkedList.this.set(currentindex, x);
	    }
	    else
	    {
		MyLinkedList.this.set(currentindex+1, x);
	    }
	    modcount++;
	}
    }
}

