
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package elements;
import boxes.*;
import java.util.ArrayList;
import java.util.Iterator;
public class User {
	
	private int id;
	private Inbox inbox = new Inbox(this);
	private Outbox outbox = new Outbox(this);
	private ArrayList<User> friends = new ArrayList<User>(); 
	
		
		/**
		 * creates a user with given id
		 * 
		 * @param ID id of the user
		 */
	
		public User (int ID) {
			this.id = ID;
		}
				
		/**
		 * add user which given as a parameter to the friend list
		 * 
		 * @param user user that will be added as a friend
		 */
		
		public void addFriend(User user) {
			
			if(!this.isFriendWith(user)) {
			friends.add(user); 
			user.addFriend(this);
			}
		}
		
		/**
		 * Remove the given user as a parameter from the friend list
		 * 
		 * @param User user that will be deleted from the friend list
		 */
		
		public void removeFriend (User User) {
			
			if(this.isFriendWith(User)) {
			friends.remove(User);
			User.removeFriend(this);
			}
		}
		
		/**
		 * checks whether this and other user which given as a parameter are friends or not
		 * 
		 * @param other user who will be checked
		 * @return whether they are friends or not
		 */
		
		public boolean isFriendWith (User other) {
			
			if(friends.contains(other)) {
				return true;
			} else {
				return false;
			}
		}
		
		/**
		 * sends a message to a specific user with given parameters
		 * 
		 * @param receiver receiver of the message
		 * @param body body of the message
		 * @param time time when the message has sent
		 * @param server server which is used while sending message
		 */
		
		public void sendMessage (User receiver, String body, int time, Server server) {
			
			Message message = new Message(this, receiver, body, server, time);
			this.outbox.addMessage(message);
			server.getMessage(message);  
		}
		
		/**
		 * returns the last message this read
		 * 
		 * @return last message user read
		 */
		public Message getLastMessage () {	
			
			Message m=null;
			Iterator<Message> itr = this.inbox.getRead().iterator();
			while(itr.hasNext()) {
				m=itr.next();		
			}
			return m;
		}
		
		/**
		 * returns user id
		 * 
		 * @return this id
		 */
		
		public int getID() {
			return this.id;
		}
		
		/**
		 * returns in box of this
		 * 
		 * @return in box
		 */
		
		public Inbox getInbox() {
			return this.inbox;
		}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

