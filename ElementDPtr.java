package LineEditor;
/* Author  Antonio Sanchez.
 *  
 * Last modification	: July 2017           
 * ADT for an element of a two dimensional Array of int,double or String of any size
   based on an extension to the code of Narasimha Karumanchi 
 *     in  Structures And Algorithms Made In Java
 
 */


public class ElementDPtr
{   private ElementDPtr previous;
	private String value;
	private ElementDPtr next;
	public ElementDPtr () 
	       { value = ""; next = null; }
    public ElementDPtr(String v ) 
    {  next = null; value = v; }
    public ElementDPtr(String v, ElementDPtr prev, ElementDPtr n) {
		value = v;
		previous = prev;
		next = n;
	}
    
	public void setValue(String v)		
	{   value =  v; }
	public String getValue()		
	{ return value; }
	// Returns the node  previous to this one.
	public ElementDPtr getPrev(){ return previous; }
	// Sets the node previous to this one.
	public void setPrev (ElementDPtr n){previous = n; }
	// Returns the node that follows this one.
	public ElementDPtr getNext(){ return next; }
    // Sets the node that follows this one.
    public void setNext (ElementDPtr n){next = n; }
	
}
