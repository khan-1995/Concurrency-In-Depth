## Concurrency-In-Depth
This repository explains the best use of concurrency and multi-threding by using a problem of finding prime numbers in between 0 and $10^8$.

----
## Use Case Scenario

1. In the [first use](./Main.java) case we try to calculate all possible prime numbers between 0 and $10^8$ , the overall process takes upto 3 to 4 Mins approx.

2. In [second scenario](./ConcurrencyOptimizer.java) we go ahead and implement multi threading by dividing the whole $10^8$ numbers into batches 10 batches and use 10 threads to process each batch.
Now in this case the overall time taken by all threads is 23 Seconds but wvery thread was not utilized in a fai manner ( i.e when one thread does the heavy work the other were free and in ideal state ), Thus this problem of fair use of threads is been overcome in third scenario.

3. In the [third scenario](./ConcurrencyFairOptimizer.java) we go ahead fine tune the multi threading part in such a way that each thread can take a number and start the process from there the start number is always incremented and updated as an  atomic integre value. Now this gives us some interesting results , each every thread has been used efficiently by thr program and almost all threads complete the task in equal amount of time. The overall time taken by this program is approx 19 Seconds, Which is almost 17% faster compared to previous approach. 
