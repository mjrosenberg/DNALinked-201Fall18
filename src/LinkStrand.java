
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
	private int myIndex,myCount;
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
		
		return myInfo.length();
	}
	//Potentially StringBuilder.
	//Work on this later.
	@Override
	public void initialize(String source) {
		Node news = new Node(source);
		myFirst = news;
		myLast = news;
		myInfo = new String(source);
		myAppends=0;
		mySize=source.length();
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
	public IDnaStrand append(String dna) {
		// TODO Auto-generated method stub
		Node node1 = new Node(dna);
		//O(1) operation 
		
		myLast.next = node1 ;
		myLast = myLast.next;
		mySize += dna.length();
		//Adds one to the value for myAppends every
		//time it is call
		myAppends += 1;
		//returns current Link
		return this;
	}
	// Recursion 
	// Find another solution but this is one
	@Override
	public IDnaStrand reverse() {
		// TODO Auto-generated method stub
		LinkStrand dna = new LinkStrand();
		Node first = myFirst;
		StringBuilder news = new StringBuilder(first.info);
		
		//built in function for StringBuilder
		//ask not what an object can do you for you
		//but what an object can do for itself -JFK
		news = news.reverse();
		String backwardsdna = news.toString();
		Node ans = new Node(backwardsdna);
		dna.initialize(ans.info);
		Node node1 = first;
		while(node1.next != null) {
			node1 = node1.next;
			StringBuilder news1 = new StringBuilder(node1.info);
			news1 = news1.reverse();
			Node node2 = new Node(news1.toString());
			dna.mySize += news1.toString().length();
			dna.myLast = node1;
			node2.next = dna.myFirst;
			dna.myFirst = node2;
			//node1 = node1.next;
		}
		return dna;
	}

	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return myAppends;
	}

	@Override
	public char charAt(int index) {
		Node list = myFirst;
		myCount = 0;
		myIndex = 0;
		//maybe less than
		if(index>=0 && index<mySize) {
			if (myCount > index) {
				myCount = 0;
				list = myFirst;
				myIndex = 0;
			}
			while (myCount != index) {
				myCount++;
				myIndex++;
				if (myIndex >= list.info.length()) {
					myIndex = 0;
					list = list.next;
				}
			}
	           return list.info.charAt(myIndex);
	        
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}
	//This some validd ass toString
	//Might have some error
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
