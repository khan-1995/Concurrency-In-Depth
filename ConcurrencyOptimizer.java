package dsaalgobank;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrencyOptimizer implements Runnable {

	int start_num;
	int end_num;
	AtomicInteger TotalPrimeCount;
//	AtomicInteger CompletedProcessCount;
//	
//	public int getCompletedProcessCount() {
//		return CompletedProcessCount.get();
//	}

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

}
