
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package elements;

public class Message {
	
	static int numOfMessages = 0;  //number of total messages in the program
	private int id;
	private String body;
	private User sender;
	private User receiver;
	private int timeStampSent​ ;
	private int timeStampRead​ ;
	private int timeStampReceived​ ;
	private Server server;
	
	/**
	 * Creates a message
	 * 
	 * @param ID ID of the message
	 * @param sender sender
	 * @param receiver receiver
	 * @param body message body
	 * @param time time when message sent
	 * @param server server that is used
	 */
	
	public Message(User sender, User receiver, String body, Server server, int time) { //time?
		
		this.id = numOfMessages; //?
		this.sender = sender;
		this.receiver = receiver;
		this.body = body;
		this.server = server;
		this.numOfMessages++;
		this.timeStampSent​=time;
	}
	
	/**
	 * returns id of the message
	 * 
	 * @return this id
	 */
	
	public int getId() {
		
		return this.id;
	}
	
	/**
	 * returns message body as a string
	 * 
	 * @return this body
	 */
	
	public String getBody() {
		
		return this.body;
	}
	
	/**
	 * compares this message and other message o, if this message is longer than the other message, returns a positive number.
	 * Else if the other message is longer, returns a negative number. Returns 0 if both messages have the same number of characters.
	 * 	
	 * @param o message which will be compared with
	 * @return  if this message is longer than the other message, returns a positive number. Else if the other message is longer, returns a negative number. Returns 0 if both messages have the same number of characters.
	 */
		
	public int compareTo (Message o) {
			
		if(this.body.length()>o.body.length()) {
			return 1;
		} else if (this.body.length()<o.body.length()) {
			return -1;
		} else {
			return 0;
		}
	}		  		
	
	/**
	 *	Checks whether two messages are the same
	 *
	 *@return whether they are equal or not as a boolean
	 */
	
	public boolean equals(Object o) { 
		
		if(o instanceof Message){
			return this.getId()==((Message)o).getId();
		} else {
			return false;
		}
	}
	
	/**
	 * Sets time message sent
	 * 
	 * @param timeStampSent time when the message sent
	 */
	
	public void setTimeStampSend (int timeStampSent) {
		
		this.timeStampSent​ = timeStampSent;
	}
	/**
	 * Sets time message read
	 * 
	 * @param timeStampRead time when the message read
	 */
	
	public void setTimeStampRead (int timeStampRead) {
		
		this.timeStampRead​ = timeStampRead;
	}
	
	/**
	 * Sets time message received
	 * 
	 * @param timeStampReceived time when the message received
	 */
	
	public void setTimeStampReceived (int timeStampReceived) {
		
		this.timeStampReceived​ = timeStampReceived;
	}
	
	/**
	 * Returns last message a user has read as a string with additional info: receiver, sender, time stamp received and read, message body
	 * 
	 * @return last message that user of this message read with necessary info  
	 */
	
	public String toString () {  		 
	
		String info = " From: "+this.sender.getID()+"\tTo:"+this.receiver.getID()+"\n\tReceiver: "+this.timeStampReceived​+" Read: "+this.timeStampRead​+"\n\t"+this.body;	
		return info;
	}
	
	/**
	 * returns sender of the message
	 * 
	 * @return this sender
	 */
	public User getSender() {
		
		return this.sender;
	}
	/**
	 * returns receiver of the message
	 * 
	 * @return this receiver
	 */
	public User getReceiver() {
		
		return this.receiver;
	}
	
	/**
	 * returns server
	 * 
	 * @return this server
	 */

	public Server getServer() {
		return server;
	}
	
	/**
	 * returns when this message read
	 * 
	 * @return timeStampRead
	 */
		
	public int getTimeRead() {
		return this.timeStampRead​;
	}
	/**
	 * returns when this message read
	 * 
	 * @return timeStampReceived
	 */
	public int getTimeReceived() {
		return this.timeStampReceived​;
	}
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

