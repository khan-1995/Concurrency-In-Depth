package concurencymisc;

import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue implements Runnable{

	int front;
	int rear;
	int size;
	int[] queue;
	AtomicInteger trueCount;

	public MyQueue(int capacity) {
		super();
		this.front = 0;
		this.rear = 0;
		this.size = 0;
		this.queue = new int[capacity];
		this.trueCount = new AtomicInteger(0);
	}

	public void enqueue(int d) {
		synchronized (queue) {
			queue[rear] = d;
			rear += 1;
			size++;			
		}
	}

	public int dequeue() {
		if (size == 0) {
			System.out.println("can not dequeue from an empty queue");
			return -1;
		}

		int item = queue[front];
		size--;
		front++;
		return item;
	}
	
	public void run() {
		int num = (int) Math.round(Math.random() * 10);
		enqueue(num);
		if(trueCount.get()%10000==0) {
			System.out.println("Current iter num :: "+trueCount.get());
		}
		trueCount.getAndIncrement();
	}

	public static void main(String[] args) {
		MyQueue q = new MyQueue(10);

		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);

		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println("Size : " + q.size);
	}
}
