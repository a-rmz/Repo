package managers;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Manager <Type> {
	
	// **** LOCAL LIST ****
	List<Type> l = Collections.synchronizedList(new LinkedList<Type>());
	
	
	// ----------------- **** METHODS **** ----------------- 
	
	/**
	 *  Adds the object to the TODO LinkedList.
	 * @param o
	 */
	public void add(Type o) {
		l.add(o);
	}
	
	/**
	 *  Removes the object from the TODO LinkedList.
	 * @param o
	 */
	public void remove(Type o) {
		l.remove(o);
	}
	
	/**
	 * @ToOverride Destroys the element.
	 * @param o
	 * @return boolean true if success.
	 */
	public boolean destroy(Type o) {
		return true;
	}

	/**
	 *  Returns the LinkedList.
	 * @return
	 */
	public List<Type> returnManager() {
		return l;
	}
	
}
