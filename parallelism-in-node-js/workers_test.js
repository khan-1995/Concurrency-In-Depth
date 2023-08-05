const { Worker } = require("worker_threads");

let doFib = (n) =>
  new Promise((resolve, reject) => {
    let t1 = Date.now();
    /** Start worker */
    const worker = new Worker("./fib.js", {
      workerData: {
        n,buffer
      },
    });

    //Listen message from worker
    worker.once("message", (data) => {
      console.log(`worker Id ${worker.threadId} done: ${Date.now() - t1}`);
      resolve(data);
    });

    //Listen for error from worker
    worker.once("error", (err) => reject(err));
  });

let main = async function () {
  let t1 = Date.now();
  await Promise.all([
    doFib(40),
    doFib(40),
    doFib(40),
    doFib(40),
    doFib(40),
  ]).then((data) => {
    console.log(data);
  });
  console.log(`overall time taken ${Date.now() - t1}`);
};

main().catch(console.error);
/**
   * worker Id 4 done: 1301
worker Id 3 done: 1307
worker Id 1 done: 1382
worker Id 5 done: 1374
worker Id 2 done: 1402
[ 102334155, 102334155, 102334155, 102334155, 102334155 ]
overall time taken 1411
   */
