let fib = function (n) {
  if (n <= 1) {
    return n;
  }

  return fib(n - 2) + fib(n - 1);
};

let doFib = (n) =>
  new Promise((resolve) => {
    let t1 = Date.now();
    let value = fib(n);
    // console.log("");
    let t2 = Date.now();
    console.log(`total time taken ${t2 - t1}`);
    resolve(value);
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

  // console.log(values);
};

main().catch(console.error);

/**
 * total time taken 793
total time taken 815
total time taken 802
total time taken 799
total time taken 790
[ 102334155, 102334155, 102334155, 102334155, 102334155 ]
overall time taken 4007
 */
