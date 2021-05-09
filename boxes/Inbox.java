
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package boxes;

import java.util.Stack;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import elements.*;

public class Inbox extends Box {

	public Stack<Message> unread = new Stack<Message>();  //unread messages stored here
	Queue<Message> read = new LinkedList<Message>();   //read messages stored here
	
		/**
		 * Creates an in box with given owner, where received messages are stored
		 * 
		 * @param owner owner of the box
		 */
	
		public Inbox(User owner) {
			super(owner);
			
		}
		
		/**
		 * Receives messages from the server, adds to the ​ unread​ stack. This method also changes timeStampReceived​ with the parameter time​
		 * 
		 * @param server server where messages are being taken
		 * @param time when messages are received
		 */
		
		public void receiveMessages (Server server, int time) {		
			User owner = this.owner; //owner of the box
			
			Iterator<Message> receiveMsgs = server.getMsgs().iterator(); 
			Message m; 
			while(receiveMsgs.hasNext()) {
				m=receiveMsgs.next();
				
				if(m.getReceiver()==owner) {
					if(owner.isFriendWith(m.getSender()))	{
						receiveMsgs.remove();
						server.updateCapacity();
						m.setTimeStampReceived(time);
						this.unread.add(m);
					}
				}
			}
		}
		
		/**
		 *  reads a certain amount of messages from the ​ unread stack. 
		 *	if the ​ number parameter is 0, then it reads all the messages in unread. if the
		 *	number of messages in unread​ is less than number, still it will read all the messages.
		 *
		 * @param num number of the messages that will be read
		 * @param time when messages are read
		 * @return current time
		 */
		
		public int readMessages (int num, int time) {
		
			Message m;
			if(!this.unread.isEmpty()) {
			
				if(num>this.unread.size() || num==0) {
					
					while(!this.unread.empty()) {
						m = this.unread.pop();
						this.read.add(m);
						m.setTimeStampRead(time);
						time++;
					}
					
				} else {
					
					for(int i=0 ; i<num ; i++) {
					
						m=this.unread.pop();
						this.read.add(m);
						m.setTimeStampRead(time);
						time++;
					
					}
				}	
			}
			
			return time;
		}
		
		/**
		 * reads all the messages with a specific sender
		 * 
		 * @param sender sender of messages
		 * @param time time when the messages read
		 * @return current time
		 */
		public int readMessages (User sender, int time) {  //time, This method is for reading a specified sender’s messages.
			
			Stack<Message> holder = new Stack<Message>();
			Message m = null;
			
			while(!this.unread.isEmpty()) {
				
				m=this.unread.pop();
				
				if(m.getSender()==sender) {
					this.read.add(m);
					m.setTimeStampRead(time);
					time++;
				} else {
					holder.add(m);
				}
			}
			
			while(!holder.isEmpty()) {	
			this.unread.push(holder.pop());
			}
			return time;
		}
		/**
		 * reads a specific message in the in box if it exists
		 * 
		 * @param msgls id of the specific message
		 * @param time time when the message will be read
		 */
		public void readMessage(int msgls, int time) {  //This method is for reading message with specific ID. metodları falan öğren tekrar gel, time kullanmadınn
			
			Stack<Message> holder = new Stack<Message>();
			Message m;

			while(!this.unread.isEmpty()) {
				m=this.unread.pop();
				if(m.getId()==msgls) {
					this.read.add(m);
					m.setTimeStampRead(time);
					break; 
				} else {
					holder.add(m);
				}
			}
			
			while(!holder.isEmpty()) {
				this.unread.push(holder.pop());
			}
		}
		
		/**
		 * returns the storage where read messages are being stored
		 *  
		 * @return messages that has read
		 */
		
		public Queue<Message> getRead() {
			
			return this.read;
		}
	
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

