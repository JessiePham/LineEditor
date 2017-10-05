package LineEditor;
/* Author  Antonio Sanchez.
 *  
 * Last modification	: July 2017           
 * ADT for a single Lin ked liste String of any size
   based on an extension to the code of Narasimha Karumanchi 
 *     in  Structures And Algorithms Made In Java
 *     
 
 */



public class DoubleLinkedList
{   private ElementDPtr tail;
	private String value;
	private ElementDPtr head;
	private int length = 0;
	
	public DoubleLinkedList () {
		head = new ElementDPtr("",null,null);
		tail = new ElementDPtr("", head, null);
		head.setNext(tail);
		length = 0;
	}
	public DoubleLinkedList (String v) 
       {value = v; 
       head = null;
       head.setNext(tail);
		length = 0;}
	

// Insert a node at the beginning of the list 
public  void insertAtBegin(String v) {
	ElementDPtr e = new ElementDPtr(v,null,head);
	head = e;
	length ++;
	System.out.println(tail.getPrev().getValue() + " insertB " + length);
}

//Add a new value to the rear of the list.
	public void insertAtEnd(String v) {
		if(length==0) insertAtBegin(v);
		else {ElementDPtr e= new ElementDPtr(v,tail.getPrev(),tail);
		        System.out.println(v + " tail " + tail.getPrev().getValue());
		        if (tail.getPrev().getValue().equals("")) e.setPrev(head);
		    
		      e.getPrev().setNext(e);
		      System.out.println(v + " it " + length);
		      tail.setPrev(e); 
		      length += 1; }
		System.out.println(tail.getPrev().getValue() + " insertE " + length);
	}

	// Add a new value to the list at a given position.
		// All values at that position to the end move over to make room.
		public void insert(String v, int position) {
			// fix the position
			if (position < 0) {
				position = 0;
			}
			if (position > length) {
				position = length;
			}

			// if the list is empty, make it be the only element
			if (head == null) {
				head = new ElementDPtr(v);
				tail = head;
			}
			// if adding at the front of the list...
			else if (position == 0) {
				ElementDPtr temp = new ElementDPtr(v);
				temp.setNext(head);
				head = temp;
			}
			// else find the correct position and insert
			else {
				ElementDPtr temp = head;
				for (int i=1; i<position; i+=1) {
					temp = temp.getNext();
				}
				ElementDPtr newNode = new ElementDPtr(v);
				newNode.setNext(temp.getNext());
				newNode.setPrev(temp);
				newNode.getNext().setPrev(newNode);
				temp.setNext(newNode);
			}
			// the list is now one value longer
			length += 1;
			System.out.println(tail.getPrev().getValue() + " insert " + length);
		} 
			
		// Find the position of the head value that is equal to a given value.
		// The equals method us used to determine equality.
public int getPosition(String v) {
			// go looking for the data
			ElementDPtr temp = head;
			int pos = 0;
			while (temp != null) {
				if (temp.getValue().equals(v)) {
					// return the position if found
					return pos;
				}
				pos += 1;
				temp = temp.getNext();
			}
			// else return -1
			return Integer.MIN_VALUE;
		} 
	
//Remove the value at a given position.
	// If the position is less than 0, remove the value at position 0.
	// If the position is greater than 0, remove the value at the last position.
	public void remove(int position) {
		// fix position
		if (position < 0) {
			position = 0;
		}
		
		if (position >= length) {
			position = length-1;
		}
		
		// if nothing in the list, do nothing
		if (head == null)
			return;
		
		// if removing the head element...
		if (position == 0) {
			head = head.getNext();
			if (head == null)
				tail = null;
		}
		// else advance to the correct position and remove
		else {
			ElementDPtr temp = head;
			for (int i=1; i<position; i+=1) {
				temp = temp.getNext();
				System.out.println(" Value "+ temp.getValue());
			 }
			
			System.out.println(" ValueX "+ temp.getNext().getValue() + "  l " + length );
			ElementDPtr save = temp.getNext().getNext();
			save.setPrev(temp);
			temp.setNext(save);
			System.out.println(" Value after "+ save.getValue() + "  l " + length );
			
			
		}
		// reduce the length of the list
		length -= 1;
		System.out.println(tail.getPrev().getValue() + " remove " + length);
	}
	
	
	//Get the value at a given position.
		// If the position is less than 0, remove the value at position 0.
		// If the position is greater than 0, remove the value at the last position.
		public String get(int position) {
			String vS= "NONE";
			// fix position
			if (position < 0) {
				position = 0;
			}
			
			if (position >= length) {
				position = length-1;
			}
			
			// if nothing in the list, do nothing
			if (head == null) return vS;
			
			// if removing the head element...
			if (position == 0) return head.getValue();
				
			// else advance to the correct position and remove
			else {
				ElementDPtr temp = head;
				for (int i=1; i<position; i+=1) {
					temp = temp.getNext();
					System.out.println(" Value "+ temp.getValue());
				}
				temp = temp.getNext();
				System.out.println(" ValueX "+ temp.getValue());
				vS = temp.getValue();
			}
			return vS;
		}
		
		
		// Remove a node matching the specified node from the list.  
		// Use equals() instead of == to test for a matched node.
		public void removeMatched(String v) {
			ElementDPtr p = head;
			System.out.println(" Value 1 "+ v);
			if (head == null) 
				return;
			if (p.getValue().equals(v)) { 
				System.out.println(" Value 2 "+ p.getValue());
				head = head.getNext(); 
				if (head == null)
					tail = null;
				length--;
				return;
			}
			else { p  = head.getNext(); 
			      System.out.println(" Value 3 "+ p.getValue());
			      while(p != null) {
				  if (p.getValue().equals(v)) {
					System.out.println(" Value "+ p.getValue()
					+ p.getPrev().toString() );
					p.getPrev().setNext(p.getNext());
					p.getNext().setPrev(p.getPrev());
					
					length--;
					return;
				 } 
				else p = p.getNext();
			}
			}
		}
		
		// Return the first node in the list 
		public String getHead() {
			String vS = "NONE";
			if (length != 0) vS = head.getValue();
		return vS;
		}
		// Remove the head value from the list. If the list is empty, do nothing.
		public void removeHead() {
			if (length != 0)
			{    
		    ElementDPtr save = head.getNext();
		    head = head.getNext();
			head.setPrev(null);
			head.setNext(save.getNext());
			length -= 1;
			System.out.println(tail.getPrev().getValue() + " removeB " + length);
			
			}
			
		} 
		// Return the last node in the list 
				public String getEnd() {
				
					String vS = "NONE";
					System.out.println(vS + " l " + length);
					if (length != 0) vS = tail.getPrev().getValue();
					System.out.println(vS + " l2 " + length);
				return vS;
				}
		
		
		
		// Remove the tail value from the list. If the list is empty, do nothing.
		public void removeEnd() {
			if (length != 0)
				{ 
			     ElementDPtr save = tail.getPrev();
			     tail.setPrev(save.getPrev());
			     save.getPrev().setNext(tail);
			     length -= 1;
			     System.out.println(tail.getPrev().getValue() + " removeE " + length);
				}
			
		} 





//  display the current element
public void display(ElementDPtr e, int i) {
	System.out.println( i + " display element   " +   e.getValue()+ " Length " + length ) ;
	}
//Return the current length of the list.
	public int getLength() {
		return length;
	}

	
	// Return a string representation of this collection, in the form ["str1","str2",...].
		public String toString() {
			String result = "";
			if (head == null) {
				return result+"\n";
			}
			result = result + head.getValue();
		    ElementDPtr temp = head.getNext();
			while (temp != null) {
				result = result + "\n" + temp.getValue();
				temp = temp.getNext();
			}
			return result + "";
		}

// to be implemented
		// get the current value
		public String getCurrent() {
			String result="";
			return result ;
		}
		// search from the current to the head 
		public String findBackward() {
			String result="";
			return result ;
		}
		// search from the current to the tail 
		public String findForward() {
			String result="";
			return result ;
		}
	
		// insert after a value 
				public String insertAfter(String v) {
					String result="";
					return result ;
				}
		// insert before a value 
				public String insertBefore(String v) {
					String result="";
					return result ;
				}
				
				
		
}
