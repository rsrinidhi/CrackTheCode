import java.util.*;


public class Cache{

	int size;
	dNode head;
	HashMap<Integer, dNode> map = new HashMap<Integer, dNode>();

	public int getSize(){
		dNode temp = this.head;
		int size = 0;

		while(temp != null){
			size++;
			temp = temp.next;
 		}

 		return size;

	}

	public dNode createList(int pageNumber){
		dNode node = new dNode(pageNumber);

		if(this.head == null){
			this.head = new dNode(pageNumber);
			return this.head;
		}

		dNode next = this.head;
		dNode temp = new dNode(pageNumber);
		temp.next = next;
		next.prev = temp;
		this.head = temp;

		return temp;
	}

	public void deleteTail(){
		dNode temp = this.head;
		dNode previous = temp;

		while(temp.next != null){
			previous = temp;
			temp = temp.next;
		}

		previous.next = null;
	}

	public void moveToHead(int pageNumber){
		if(this.head.data == pageNumber)
			return;

		dNode node = new dNode(pageNumber);
		dNode temp = this.head;

		while(temp.next != null && temp.next.data != pageNumber){
			temp = temp.next;
		}

		temp.next = temp.next.next;
		temp = this.head;
		this.head = node;
		this.head.next = temp;
	}


	public void accessCache(int pageNumber){
		if(this.map.get(pageNumber) != null){
			this.moveToHead(pageNumber);
	
		}else{
			if(this.size == this.getSize()){
				this.deleteTail();
				map.put(pageNumber, this.createList(pageNumber));
			
			}else{
				map.put(pageNumber, this.createList(pageNumber));
			}
		}
	}

	public void printCache(){
		dNode temp = this.head;

		while(temp != null){
			System.out.print (temp.data + " ");
			temp = temp.next;
		}

		System.out.println();
	}

	public static void main(String[] args){
		Cache cache = new Cache();
		cache.size = 4;

		cache.accessCache(4);
		cache.printCache();
		cache.accessCache(2);
		cache.printCache();
		cache.accessCache(1);
		cache.printCache();
		cache.accessCache(1);
		cache.printCache();
		cache.accessCache(4);
		cache.printCache();
		cache.accessCache(3);
		cache.printCache();
		cache.accessCache(7);
		cache.printCache();
		cache.accessCache(8);
		cache.printCache();
		cache.accessCache(3);
		cache.printCache();

	}
}

class dNode{
	dNode next;
	dNode prev;
	int data;

	public dNode(int number){
		this.data = number;
		this.next = null;
		this.prev = null;
	}
}