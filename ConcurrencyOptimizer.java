package dsaalgobank;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrencyOptimizer implements Runnable {

	int start_num;
	int end_num;
	AtomicInteger TotalPrimeCount;

	public ConcurrencyOptimizer() {
	}


	public ConcurrencyOptimizer(int start_num, int end_num,AtomicInteger TotalCount) {
		super();
		this.start_num = start_num;
		this.end_num = end_num;
		TotalPrimeCount = TotalCount;
	}

	public boolean isPrime(int num) {
		if (num == 0 || num == 1) {
			return false;
		}

		long sqrt = Math.round(Math.sqrt(num));
		for (int i = 2; i <= sqrt; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	public void run() {
		long t1 = System.currentTimeMillis();
		for (int i = start_num; i <= end_num; i++) {
			if (isPrime(i)) {
				TotalPrimeCount.incrementAndGet();
			}
		}
		long t2 = System.currentTimeMillis();
		System.out.println("["+start_num+","+end_num+")");
		System.out.println("Thread Executed sucessfully " + TotalPrimeCount.get());
		System.out.println("Total Time Taken " + (t2 - t1)+"\n");
	}

	public static void main(String[] args) {
		int num = 27;
		System.out.println(new ConcurrencyOptimizer(0,0,null).isPrime(num));
	}

	/**Log For Current Program
	 * Thread 0 Starting...
	 * Thread 1 Starting...
	 * Thread 2 Starting...
	 * Thread 3 Starting...
	 * Thread 4 Starting...
	 * Thread 5 Starting...
	 * Thread 6 Starting...
	 * Thread 7 Starting...
	 * Thread 8 Starting...
	 * Thread 9 Starting...
	 *
	 * [3,10000003)
	 * Thread Executed sucessfully 3168668
	 * Total Time Taken 8549
	 *
	 * [20000003,30000003)
	 * Thread Executed sucessfully 4038403
	 * Total Time Taken 11501
	 *
	 * [30000003,40000003)
	 * Thread Executed sucessfully 4464007
	 * Total Time Taken 13058
	 *
	 * [10000003,20000003)
	 * Thread Executed sucessfully 4497740
	 * Total Time Taken 13185
	 *
	 * [40000003,50000003)
	 * Thread Executed sucessfully 4950230
	 * Total Time Taken 15215
	 *
	 * [60000003,70000003)
	 * Thread Executed sucessfully 5160594
	 * Total Time Taken 16231
	 *
	 * [50000003,60000003)
	 * Thread Executed sucessfully 5509465
	 * Total Time Taken 18636
	 *
	 * [70000003,80000003)
	 * Thread Executed sucessfully 5620443
	 * Total Time Taken 19582
	 *
	 * [80000003,90000003)
	 * Thread Executed sucessfully 5717205
	 * Total Time Taken 20767
	 *
	 * [90000003,100000003)
	 * Thread Executed sucessfully 5761456
	 * Final Total Time Taken 21741
	 *
	 * Thread Executed sucessfully 5761456
	 * Total Time Taken 21702
	 *
	 * */
}
