import java.util.*;

public class LinkedList{
	Node head;

	public void init(int low, int high){
	for(int i=low; i<=high; i++){
		if(this.head == null){
			this.head = new Node(i);
		}
		else{
			Node temp = this.head;
			while(temp.next != null)
				temp = temp.next;

			temp.next = new Node(i);
		}
	}		
}

	public void reverse(){
		Node temp = this.head;
		Node prev = null;

		while(temp != null){
			Node tmp = temp.next;
			temp.next = prev;
			prev = temp;
			temp = tmp;
		}
		this.head = prev;
	}

	public void reverse(Node temp){
		if(temp == null)
			return;

		if(temp.next == null){
			this.head = temp;
			return;
		}

		reverse(temp.next);
		temp.next.next = temp;
		temp.next = null;
	}

	public Node merge(LinkedList list){

		if(list.head== null)
			return this.head;
		if(this.head == null)
			return list.head;

		if(list.head == null && this.head == null)
			return null;

		Node ptr1 = this.head;
		Node ptr2 = list.head;
		Node prev1 = null;

		while(ptr1 != null && ptr2 != null){
			if(ptr1.data < ptr2.data){
				prev1 = ptr1;
				ptr1 = ptr1.next;

			}else if(ptr2.data <= ptr1.data){
				Node next = ptr2.next;

				if(prev1 == null){
					this.head = ptr2;
					this.head.next = ptr1;
				}else{
					prev1.next = ptr2;
					ptr2.next = ptr1;
				}

				prev1 = ptr2;
				ptr2 = next;
			}
		}

		if(ptr2 != null){
			ptr1 = new Node();
			ptr1 = ptr2;
			prev1.next = ptr1;
		}

		return this.head;
	}

	public void intersect(LinkedList list){
		if(list.head == null || this.head == null)
			return;

		int length1 = 0, length2 = 0;

		Node temp = list.head;

		while(temp != null){
			length1++;
			temp = temp.next;
		}

		temp = this.head;

		while(temp != null){
			length2++;
			temp = temp.next;
		}

		if(length1 < length2){
			int diff = length2 - length1;
			int count1  = 0;
			temp = list.head;

			while(count1<diff){
				temp = temp.next;
				count1++;
			}

			Node node = this.head;

			while(node != null && temp!= null){
				if(temp == node){
					System.out.println("Node intersects with value : " + node.data + " " + temp.data);
					return;
				}

				node = node.next;
				temp = temp.next;
			}
		} else if(length1 > length2){
			int diff = length1- length2;
			int count2  = 0;
			temp = this.head;

			while(count2<diff){
				temp = temp.next;
				count2++;
			}

			Node node = list.head;

			while(node != null && temp!= null){
				if(temp == node){
					System.out.println("Node intersects with value : " + node.data + " " + temp.data);
					return;
				}

				node = node.next;
				temp = temp.next;
			}
		}else{

			Node node = list.head;
			temp = this.head;

			while(node != null && temp!= null){
				if(temp == node){
					System.out.println("Node intersects with value : " + node.data + " " + temp.data);
					return;
				}

				node = node.next;
				temp = temp.next;
			}
		}

		System.out.println("Lists doesn't interest");

	}

	public void detectCycle(){
		if(this.head == null)
			return;

		Node fast = this.head;
		Node slow = this.head;

		while(fast.next != null && fast.next.next != null && slow != null && slow.next != null){
				
			slow = slow.next;
			fast = fast.next.next;

			if(slow == fast){
				System.out.println("Node loops here : " + slow.data);	
				detectCycle(slow, this.head);
				return;
			}			
		}
	}

	public void detectCycle(Node fast, Node head){
		Node ptr1 = head;
		Node ptr2 = fast;
		System.out.println("HJHJHJ"+fast.next.data+ " " + fast.data);
		/*while(true){
			ptr2 = fast;

			while(ptr2.next != fast && ptr2.next != ptr1){
				ptr2 = ptr2.next;

			}
			
				if(ptr2.next == ptr1)
					break;

				ptr1 = ptr1.next;
		}*/

			ptr2.next = null;

	}

	public void cycle(){
		Node temp = this.head;

		while(temp.next == null){
			temp = temp.next;
		}

		temp.next = this.head;
	}

	public void print(){
		Node temp = this.head;

		while(temp != null){
			System.out.print(temp.data + " ");
			temp = temp.next;
		}

		System.out.println();
	}

	

	public static void main(String[] args){
		LinkedList list = new LinkedList();
		LinkedList alist = new LinkedList();

		list.init(1,5);
		alist.init(5,12);
		System.out.println("Initializing list");
		list.print();
		alist.print();

		list.reverse();
		System.out.println("Reversing list iteratively");
		list.print();

		list.reverse(list.head);
		System.out.println("Reversing list recursively");
		list.print();

		list.intersect(alist);

		list.head = alist.merge(list);
		System.out.println("Merging 2 lists");
		list.print();

		list.cycle();
		list.detectCycle();
		System.out.println("Detecting and removing cycle");
		list.print();
	}
}

class Node{
	Node next;
	int data;

	public Node(){
		this.data = 0;
		this.next = null;
	}

	public Node(int value){
		this.data = value;
		this.next = null;
	}
}