
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package boxes;

import elements.User;
import elements.Message;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
 
/*Even if a message is never added to the server’s queue due to capacity overload,
this message should be added to the ​ sent​ queue in the user’s out box.*/

public class Outbox extends Box {

	private Queue<Message> sent = new LinkedList<Message>();
	private Iterator<Message> itr = sent.iterator();
	
		public Outbox(User owner) {
			super(owner);	
		}
		
		public void addMessage (Message sent) {		
			this.sent.add(sent);		
		}
		
		public Message getMessage() {
			return itr.next();  
		}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

