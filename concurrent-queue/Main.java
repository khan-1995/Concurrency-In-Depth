package concurencymisc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int totalSize = 100;
		ExecutorService exec = Executors.newFixedThreadPool(totalSize);
		MyQueue q = new MyQueue(totalSize);
		
		for(int i=0;i<totalSize;i++) {
			exec.submit(q);
		}
		
		try {
			while(q.trueCount.get() < totalSize) {}
			exec.shutdown();
			System.out.println("True Count :: "+q.trueCount.get());
			System.out.println("Size :: "+q.size);
		} catch (Exception e) {
			System.err.println(e);
			// TODO: handle exception
		}		
		
	}

}
