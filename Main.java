package dsaalgobank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

	public static String[] doBatch(int num, int batch_size) {

		int total_nums_in_batch = num / batch_size;
		String[] batch = new String[batch_size];
		int num_start = 3;

		for (int i = 0; i < batch_size; i++) {
			int end = total_nums_in_batch + num_start;
			batch[i] = num_start+","+end;
//			System.out.println("[" + num_start + "," + end + ")");
			num_start+=total_nums_in_batch;
		}

		return batch;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long t1 = System.currentTimeMillis();
		int MAX_INT = 100000000;
		ExecutorService exec = Executors.newFixedThreadPool(10);
//		exec.execute(conc);
//		Thread t = new Thread(conc);
//		t.start();
//		System.out.println("Thread id " + t.getId());
//		System.out.println("Thread id " + t.getState());
		String[] batch = Main.doBatch(100000000, 10);
		AtomicInteger TotalPrimeCount = new AtomicInteger(0);
		AtomicInteger InitialNum = new AtomicInteger(2);
		for(int i=0;i<batch.length;i++) {
//			String[] str = batch[i].split(","); 
//			int start = Integer.parseInt(str[0]);
//			int end = Integer.parseInt(str[1]);
//			System.out.println("Thread "+i+" Starting...");
//			ConcurrencyOptimizer conc = new ConcurrencyOptimizer(start,end,TotalPrimeCount);
//			exec.submit(conc);
			ConcurrencyFairOptimizer concf = new ConcurrencyFairOptimizer(TotalPrimeCount, InitialNum);
			exec.submit(concf);
		}
		
		while(!(TotalPrimeCount.get()==5761454)){		}
		long t2 = System.currentTimeMillis();
		System.out.println("Final Total Time Taken " + (t2 - t1) + "\n");
		System.out.println("Thread Executed sucessfully " + TotalPrimeCount.get());
		
		
		exec.shutdown();
		
		
	}

}
