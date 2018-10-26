
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
	public IDnaStrand append(String dna) {
		// TODO Auto-generated method stub
		Node node1 = new Node(dna);
		//O(1) operation 
		
		myLast.next = node1 ;
		myLast = myLast.next;
		myInfo += dna;
		//mySize += dna.length();
		mySize = this.size();
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
		
		
		Node first = myFirst;
		StringBuilder news = new StringBuilder(first.info);
		//int k = 0;
		//built in function for StringBuilder
		//ask not what an object can do you for you
		//but what an object can do for itself -JFK
		news = news.reverse();
		String backwardsdna = news.toString();
		Node ans = new Node(backwardsdna);
		LinkStrand dna = new LinkStrand(ans.info);
		//dna.initialize(ans.info);
		Node node1 = first;
		//if(k == 0) {
			//dna.myFirst = ans;
		//dna.mySize += node1.toString().length();
		node1 = node1.next;
			//k+=1;
		//}
		//else {
		while(node1 != null) {
			//node1 = node1.next;
			StringBuilder news1 = new StringBuilder(node1.info);
			news1 = news1.reverse();
			Node node2 = new Node(news1.toString());
			int length = news1.toString().length();
			
			//System.out.println(dna.mySize);
			dna.myLast = node1.next;
			node2.next = dna.myFirst;
			dna.myFirst = node2;
			dna.mySize = dna.size();
			node1 = node1.next;
		}
		//}
		return dna;
		/*
		LinkStrand dna = new LinkStrand();
		Node first = myFirst;
		Node node1 = null;
		while (first != null) {
			StringBuilder s1 = new StringBuilder(first.info);
			s1 = s1.reverse();
			Node node2 = new Node(s1.toString());
			node2.next = node1;
			node1.next = node2;
			node2 = dna.myLast;
			first = first.next;
		}
		return dna;
		*/
	}

	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return myAppends;
	}

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
