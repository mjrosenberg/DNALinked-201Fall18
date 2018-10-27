
import java.util.LinkedList;
//Adrian Lopez and Max Rosenberg
//Partners
public class LinkStrand implements IDnaStrand{
	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}
//myFirst references first node, and myLast references last node.
	private Node myFirst,myLast;
	private int myAppends;
	private long mySize;
	private String myInfo;
	private int myIndex;
	private int myLocalIndex;//myCount;
	private Node myCurrent;
	
	public LinkStrand() {
		this("");
	}
	public LinkStrand(String s) {
		initialize(s);
	}
	
	@Override
	public long size() {
		// TODO Auto-generated method stub
		return mySize;
		//return myInfo.length();
	}
	//Initializes each of the instance variables for a given linkstrand
	@Override
	public void initialize(String source) {
		Node news = new Node(source);
		myFirst = news;
		myLast = news;
		myInfo = new String(source);
		myAppends = 0;
		mySize = source.length();
		myIndex = 0;
		myLocalIndex = 0;
		myCurrent = myFirst;
	}

	@Override
	public IDnaStrand getInstance(String source) {
		// TODO Auto-generated method stub
		return new LinkStrand(source);
	}
	//Possible Source of Error
	/*
	 * Node news = new Node(dna)
	 * myLast.next=news
	 * myLast=myLast.next
	 */
	@Override
	//goes through the nodes and adds the characters to the linkstrand's info and the length of 
	//the string to the size and returns the mutated linkstrand
	public IDnaStrand append(String dna) {
		// TODO Auto-generated method stub
		Node node1 = new Node(dna);
		//O(1) operation 
		
		myLast.next = node1 ;
		myLast = myLast.next;
		myInfo += dna;
		mySize += dna.length();
		//mySize = this.size();
		//Adds one to the value for myAppends every
		//time it is call
		myAppends += 1;
		//returns current Link
		return this;
	}
	//reverses the first string and creates a new linkstrand with the reversed string intializing it.
	//then it goes through the other nodes and reverses it and adds them to the front of the 
	//linkstrand making sure to update variables
	@Override
	public IDnaStrand reverse() {
		
		Node first = myFirst;
		StringBuilder news = new StringBuilder(first.info);
		//ask not what an object can do you for you
		//but what an object can do for itself -JFK
		news = news.reverse();
		String backwardsdna = news.toString();
		Node ans = new Node(backwardsdna);
		StringBuilder totchars = new StringBuilder(news);
		LinkStrand dna = new LinkStrand();//ans.info);
		dna.initialize(ans.info);
		Node node1 = first;
		
		node1 = node1.next;
			
		while(node1 != null) {
			
			totchars.append(node1.info);
			StringBuilder news1 = new StringBuilder(node1.info);
			news1 = news1.reverse();
			Node node2 = new Node(news1.toString());
			
			dna.myLast = node1.next;
			node2.next = dna.myFirst;
			dna.myFirst = node2;
			node1 = node1.next;
		}
		//}
		dna.mySize = totchars.toString().length();
		return dna;
		
	}

	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return myAppends;
	}
	//checks tio make sure the index is within the bounds and then increments the local index and 
	//index by one until the index is found or the local index exceeds the node's length, in which
	//case the local index resets
	@Override
	public char charAt(int index) {
		if (myIndex > index) {
			myCurrent = myFirst;
			myIndex = 0;
			myLocalIndex = 0;
		}	
		if (index < 0 || index >= this.size()) {
				throw new IndexOutOfBoundsException();
			}
			
		else if(index >= 0 && index < this.size())  {
			while (myIndex != index && myCurrent != null) {
				myLocalIndex += 1;
				myIndex += 1;
				if (myLocalIndex >= myCurrent.info.length()) {
					if (myCurrent.next != null) {
					myLocalIndex = 0;
					myCurrent = myCurrent.next;
					}
					else {
						break;
					}
				}
				}
		}
		return myCurrent.info.charAt(myLocalIndex);
	}
		
	//takes the node and makes it into a string using a string builder to append
	public String toString() {
		StringBuilder info = new StringBuilder();
		Node current = myFirst;
		while(current!=null) {
		info.append(current.info);
		current=current.next;
		}
		return info.toString();
	}

}
