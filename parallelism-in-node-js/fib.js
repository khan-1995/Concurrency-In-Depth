const {parentPort,workerData} = require('worker_threads');


let fib = function (n) {
    if (n <= 1) {
      return n;
    }
  
    return fib(n - 2) + fib(n - 1);
  };

const result =  fib(workerData.n);

parentPort.postMessage(result);