import java.util.*;

// Node for each student in queue
class Node{
	//data of each student
	String name;
	float cgpa;
	int token;
	Node next,previous;
	public Node(String n,float c, int t){
		this.name = n;
		this.cgpa=c;
		this.token=t;
	}
}

public class Queue{


	//head node of queue
	Node head = null;
	//count of number of student present in queue at a given point of time
	int count=0;


	public static void main(String args[]){
		Queue q1 = new Queue();
		q1.create();	//Create a new queue
		q1.yetToBeServed();	//print name of students yet to be served
	}

	// function to create the queue
	public void create(){
		Scanner sc = new Scanner(System.in);
		//Number of Events
		System.out.println("Enter the total numer of events:");
		int n = sc.nextInt();
		String s;
		String name;
		float cgpa;
		int token;
		Node new_node;
		for(int i=0;i<n;i++){
				// s string holds what kind of event it is: SERVED or ENTER
				System.out.println("Kind of event it is: SERVED or ENTER: ");
				s=sc.next();
				if(s.equals("SERVED")){
					// Queue is inserted in descending order 
					//so to serve someone just delete the first node of queue
					if(head!=null)
					{
						head=head.next;
						if(head!=null)
						head.previous=null;
						//one node is deleted so decrease the count by one
						this.count--;
					}
				}
				//Insert the next node if operatio is enter
				else if(s.equals("ENTER")){
					System.out.println("Enter the Name, CGPA and Token : ");
					name = sc.next();
					cgpa = sc.nextFloat();
					token=sc.nextInt();
					//create a new node
					new_node = new Node(name,cgpa,token);
					//insert operation
					this.insert(new_node);
					//one node is inserted so increase the count by one
					this.count++;
				}
		}
	}
	// function to insert a node in sorted order
	private void insert(Node n){
		Node temp=this.head;
		//if queue is empty
		if(temp==null){
			this.head=n;
			n.next=null;
			n.previous=null;
		}
		//if queue already have some nodes
		else{
			while(temp!=null)
			{
				// if cgpa is greater or equal then 
				// node will be inserted otherwise
				// go for next node untill the next node cgpa is lesser 
				// or untill reached end of the queue
				if(n.cgpa>=temp.cgpa){

					// If cgpa is equal check for campare names lexographically
					if(n.cgpa==temp.cgpa){
						if(n.name.compareTo(temp.name)>=0){
							//if name are equal compare tokens
							if(n.name.compareTo(temp.name)==0){
								// tokens are unique so it cam mot be equal
								if(n.token>temp.token){
									if(temp==head)
									{
										head=n;
										head.next=temp;
										temp.previous=n;
									}
									else{
										n.next=temp;
										n.previous=temp.previous;
										(temp.previous).next=n;
										temp.previous=n;
									}
								}
								else{
									n.next=temp.next;
									n.previous=temp;
									temp.next=n;
								}
							}
							//name in new nade is geater lexographically
							else{
								if(temp==head)
								{
									head=n;
									head.next=temp;
									temp.previous=n;
								}
								else{
									n.next=temp;
									n.previous=temp.previous;
									(temp.previous).next=n;
									temp.previous=n;
								}
																	
							}

						}
						//name in new node is samller lexographically
						else{
							n.next=temp.next;
							n.previous=temp;
							temp.next=n;
						}
					}
					//cgpa of new node is greater
					else{
						if(temp==head)
								{
									head=n;
									head.next=temp;
									temp.previous=n;
								}
								else{
									n.next=temp;
									n.previous=temp.previous;
									(temp.previous).next=n;
									temp.previous=n;
								}
					}
					break;
				}
				//cgpa of new node is smaller
				else{
					//last node of queue
					if(temp.next==null)
					{
						temp.next=n;
						n.previous=temp;
						n.next=null;
						break;
					}
					// if not last node of queue then go for next
					temp=temp.next;
				}
			}
		}
	}

	//function to print names that are yet to be served
	public void yetToBeServed(){
		Node temp=this.head;

		System.out.println("Yet to be served:");
		//if queue is empty
		if(this.count==0){
			System.out.println("EMPTY");
		}
		else{
			while(temp!=null)
			{
				System.out.println(temp.name);
				temp=temp.next;
			}
		}
	}
}