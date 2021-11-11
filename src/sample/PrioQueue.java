package sample;

import java.util.LinkedList;


public class PrioQueue {

	QueueMember root = null;
	
	public class QueueMember {
		
		private Node node;
		private double key;
		private QueueMember next = null;
		
		public QueueMember(Node inputNode, double inputKey) {
			
			node = inputNode;
			key = inputKey;
		}
		
		public Node getQueueNode() {
			return this.node;
		}
		
		public double getQueueKey() {
			return this.key;
		}
		
		public void setQueueKey(double newKey) {
			this.key = newKey;
		}
		
		public QueueMember getNext() {
			return this.next;
		}
		
		public void setNext(QueueMember newNext) {
			this.next = newNext;
		}
		
		
	}

	public void insert(Node currentNode, double key) {
		if (currentNode == null) {
			throw new RuntimeException ("Node is null.");
		}
		
		QueueMember in = new QueueMember(currentNode,key);
		
		if (root == null) {
			
			root = in;
		}
		
		else {
			
			QueueMember temp = root;
			
			if (in.getQueueKey() < temp.getQueueKey()) {
				in.setNext(temp);
				root = in;
			}
			
			while (in.getQueueKey() >= temp.getQueueKey() ) {
				if (temp.getNext() != null && in.getQueueKey() >= temp.getNext().getQueueKey()) {
					temp = temp.getNext();
				}
				else {
					in.setNext(temp.getNext());
					temp.setNext(in);
					break;
				}
			}
			
		}
	}

	public Node removeMin() {

		if(root == null) {
			throw new NullPointerException("Priority Queue is empty.");
		}
		QueueMember min = root;
		root = root.getNext();
		
		return min.getQueueNode();
	}
	
}
