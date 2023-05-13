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
			System.out.println("[" + num_start + "," + end + ")");
			num_start+=total_nums_in_batch;
		}

		return batch;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long t1 = System.currentTimeMillis();
		int MAX_INT = 100000000;
		int concurrent_threads_count = 10;
		
		/*First use case*/
		//		Thread t = new Thread(new ConcurrencyOptimizer());
		//		t.start();
		//		System.out.println("Thread id " + t.getId());
		//		System.out.println("Thread id " + t.getState());
		/*<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
		
		/*Second use case*/
		// String[] batch = Main.doBatch(100000000, concurrent_threads_count);
		// AtomicInteger TotalPrimeCount = new AtomicInteger(0);
		// for(int i=0;i<batch.length;i++) {
		// 	String[] str = batch[i].split(","); 
		// 	int batch_start = Integer.parseInt(str[0]);
		// 	int batch_end = Integer.parseInt(str[1]);
		// 	System.out.println("Thread "+i+" Starting...");
		// 	ConcurrencyOptimizer conc = new ConcurrencyOptimizer(batch_start,batch_end,TotalPrimeCount);
		// 	exec.submit(conc);
		// }
		/*<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

		/*Third use case*/
		AtomicInteger TotalPrimeCount = new AtomicInteger(0);
		AtomicInteger InitialNum = new AtomicInteger(2);
		for(int i=0;i<concurrent_threads_count;i++) {
			ConcurrencyFairOptimizer concf = new ConcurrencyFairOptimizer(TotalPrimeCount, InitialNum);
			exec.submit(concf);
		}
		/*<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
		
		while(!(TotalPrimeCount.get()==5761454)){		}/**Wait for program to complete */
		long t2 = System.currentTimeMillis();
		System.out.println("Final Total Time Taken " + (t2 - t1) + "\n");
		System.out.println("Thread Executed sucessfully " + TotalPrimeCount.get());
		exec.shutdown();/**Shut down all the concurrent processes running */
	}

}
