
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package executable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.Scanner;

import elements.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		/** Takes input file and scans it with Scanner named input. Does necessary operations and prints the output via printStream called output.
		 * 
		 * Has an ArrayList names users. Via given information, users are implemented and added to this arrayList. A server is implemented with
		 * given capacity. A for loop takes number of operations as a parameter and via taking the type of the operation, executes them by using switch.
		 * There are 10 types of operations in the switch. Case 0, sends a message to a specific user, case 1 make user receive the messages from server,
		 * case 2 help user to read certain amount of his/her messages, case 21 reads all the messages form a specific sender, case 22 reads a specific message
		 * with a specific id, case 3 makes users friend with each other, case 4 removes a friend from user's friend list, case 5 flushes the server, case 6
		 * prints the current size of the server,  case 7 prints the last message a user has read
		 * 
		 * @author BengisuTakkin
		 * @throws fileNotFoundException
		 * @param time counter of the time while operations are being made 
		 * @param numbOfUsers number of users that will be implemented and added to users arrayList
		 * @param  numbOfQueries number of operations that will be executed
		 * @param capacity given capacity of the server
		 * @param type type of the operation that will be executed 
		 */
		
		Scanner input = new Scanner(new File(args[0]));
		PrintStream output = new PrintStream(new File(args[1]));
		
		int time = 0;  //counter of the time passed
		Scanner firstLine = new Scanner(input.nextLine());
		
		int numbOfUsers = firstLine.nextInt();  //number of the users
		int numbOfQueries = firstLine.nextInt();  //number of the queries
		int capacity = firstLine.nextInt();  //capacity of the server
			 
		Server server = new Server(capacity);  //implementing the server

		ArrayList<User> users = new ArrayList<User>(); 
		
		for(int j=0; j<numbOfUsers ; j++) {  //implementing users and adding to the arrayList
			users.add(new User(j));
		}
	
		
		for(int i=0; i<numbOfQueries ; i++) {
			
			Scanner line = new Scanner(input.nextLine());  //takes input line by line
			int type = line.nextInt();  //type of the operation

			switch(type) {
			
			case 0: //sending message
			
				int senderID = line.nextInt();  //sender id
				int receiverID = line.nextInt();  //receiver id
				String body = line.nextLine().substring(1);  //message body
				
				long firstLoad = server.getLoad();  //taking server load
				users.get(senderID).sendMessage(users.get(receiverID), body, time, server);  //sending message
				long lastLoad = server.getLoad();  //taking server load
				
				if(!((firstLoad>=50&&firstLoad<80&&lastLoad>=50&&lastLoad<80)||(firstLoad>=80&&firstLoad<100&&lastLoad>=80&&lastLoad<100))){  //checking whether the capacity changed enough to throw a warning message
					server.checkServerLoad(output);  //checks server load
				}
				time++;
				
				break;
				
			case 1: //receive messages:
				
				User receiver = users.get(line.nextInt());  //receiver
	
				long beforeReceive = server.getLoad();  //checking server load
				receiver.getInbox().receiveMessages(server, time);  //receiving message
				long afterReceive = server.getLoad();
				
				if(!((beforeReceive>=50&&beforeReceive<80&&afterReceive>=50&&afterReceive<80)||(beforeReceive>=80&&beforeReceive<100&&afterReceive>=80&&afterReceive<100))){  //checking whether the capacity changed enough to throw a warning message
					server.checkServerLoad(output);  //checking server load
				}
				time++;
				 	
				break;
				
			case 2:  //read a certain amount of messages
				
				int receiverID2 = line.nextInt(); //receiver id
				int numbOfMess = line.nextInt();  //number of messages that will be read
				
				int returnedTime = users.get(receiverID2).getInbox().readMessages(numbOfMess, time); //reads messages, sets the time 
				if(returnedTime==time) { //if no messages has read
					time++;  
				} else {
					time = returnedTime;
				}
							
				break;
						
			case 21:  //read all the messages from a sender
				
				int receiverID21 = line.nextInt();  //receiver id
				int senderID21 = line.nextInt();  //sender id
				
				int receivedTime = users.get(receiverID21).getInbox().readMessages(users.get(senderID21), time);  //read messages, sets time
				
				if(receivedTime==time) {  //if no messages has read
					time++;
				}else {
					time=receivedTime;
				}
				
				break;
				
			case 22:  //read a specific message
				
				int receiverID22 = line.nextInt(); //receiver id
				int messageID22 = line.nextInt();  //message id
				
				users.get(receiverID22).getInbox().readMessage(messageID22, time);  //finds the message (if it is exist) than reads it
				time++;
				
				break;
				
			case 3: //add friend
				
				User user1 = users.get(line.nextInt());  //user who adds a friend
				User user2 = users.get(line.nextInt());  //user who will be added as a friend
				user1.addFriend(user2);  //adds friend
			
				time++;
				
				break;
				
			case 4:  //remove friend
				
				int ID1_4 = line.nextInt(); //id of whom will delete a friend
				int ID2_4 = line.nextInt(); //id of whom will be deleted from the friend list
				
				users.get(ID1_4).removeFriend(users.get(ID2_4)); //removes friend
				
				time++;
				
				break;
				
			case 5:  //flushes server
				
				server.flush();  //cleans the server
				time++;
				
				
				break;
				
			case 6:  //prints the current size of the server
				
				long currentSize = server.currentSize();  //current size of the server
				output.println("Current load of the server is "+currentSize+" characters.");
				time++;
				
				break;
				
			case 61:  //print the last message a user has read
				
				int userID = line.nextInt();  //user id
				Message lastMessage = users.get(userID).getLastMessage();  //last message that user read
				
				if(lastMessage!=null) {  //if the message exists, prints it
				output.println("\tFrom: "+lastMessage.getSender().getID()+" to: "+lastMessage.getReceiver().getID()+"\n\tReceived: "+lastMessage.getTimeReceived()+" Read: "+lastMessage.getTimeRead()+"\n\t"+lastMessage.getBody());
				}
				time++;
					
				break;
				
			}
		}		
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

