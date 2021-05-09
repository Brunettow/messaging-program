
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package elements;
import java.util.LinkedList;
import java.util.Queue;
import java.io.PrintStream;
import java.util.Iterator;

public class Server {
	
	private long capacity;//capacity of the server
	private long currentSize;//Total number of the characters of messages’ bodies
	private Queue<Message> msgs= new LinkedList<Message>();//A queue where non received messages are stored.
	Iterator<Message> itr = msgs.iterator();
	
	/**
	 * Creates a server with given capacity
	 * 
	 * @param capacity capacity of the server
	 */
	
	public Server(long capacity) {
		this.capacity=capacity;
	}
	
	/**
	 * Checks server load, throws a warning message when it is needed
	 * 
	 * @param printer prints warning messages
	 */

	public void checkServerLoad (PrintStream printer) {
	
		if(this.currentSize*100/capacity>=100){
			printer.println("Server is full. Deleting all messages...");
			this.flush();	
		} else if(this.currentSize*100/this.capacity>=80) {
			printer.println("Warning! Server is 80% full.");
		} else if (this.currentSize*100/capacity>=50) {
			printer.println("Warning! Server is 50% full.");
		}
	}
	
	/**
	 * returns server load
	 * 
	 * @return server load
	 */
	
	public long getLoad() {
		return this.currentSize*100/this.capacity;
	}

	/**
	 * returns current size of the server
	 * @return current size
	 */
	
	public long currentSize() {
	
		return this.currentSize;
	}
	
	/**
	 * Cleans the server, deletes all the messages that are stored
	 */
	
	public void flush() {
		this.msgs.clear();
		this.currentSize=0;
	}
	
	/**
	 * takes message, adds it to the queue
	 * 
	 * @param message given message
	 */
	
	public void getMessage (Message message) {   

		this.msgs.add(message);
		this.currentSize += message.getBody().length();
		
	}
	
	/**
	 * Checks and updates the capacity
	 * 
	 * @param size size of the capacity
	 */
	
	public void updateCapacity() {

		int size = 0;
		for(Message m: msgs) {
			size += m.getBody().length();		
		}
		this.currentSize=size;
	
	}
	
	/**
	 * Returns the queue where the messages are being stored
	 * 
	 * @return where messages are stored
	 */
	
	public Queue<Message> getMsgs() {	
		return this.msgs;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

