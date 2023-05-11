package dsaalgobank;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrencyFairOptimizer implements Runnable {

	int MAX_INT = 100000000;
	AtomicInteger TotalPrimeCount;
	AtomicInteger CurrentNum;

	public ConcurrencyFairOptimizer(AtomicInteger totalPrimeCount, AtomicInteger currentNum) {
		super();
		TotalPrimeCount = totalPrimeCount;
		CurrentNum = currentNum;
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
		TotalPrimeCount.incrementAndGet();
		return true;
	}

	public void run() {
		long t1 = System.currentTimeMillis();
		int num = CurrentNum.incrementAndGet();
		System.out.println(" Current Num " + num);
		while (num < MAX_INT) {
			isPrime(num);
			num = CurrentNum.incrementAndGet();
		}
		long t2 = System.currentTimeMillis();
//		System.out.println("Thread Executed sucessfully " + TotalPrimeCount.get());
		System.out.println("Total Time Taken " + (t2 - t1) + "\n");
	}

	public static void main(String[] args) {
//		int num = 27;
//		System.out.println(new ConcurrencyFairOptimizer(0,0,null).isPrime(num));
	}

}
