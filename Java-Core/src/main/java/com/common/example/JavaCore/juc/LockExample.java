package com.common.example.JavaCore.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: Gz.
 * @Date: 2019/2/25 14:36
 * @Description:J.U.C 包下 ReetrantLock使用样例
 */
@Slf4j
public class LockExample {

  private static final int threadCount = 200;

  //并发数
  private static final int clietnTotal = 5000;

  //初始化锁
  private final static  Lock lock = new ReentrantLock();

  //初始化计数
  private static int count = 0;

  public static void main(String[] args) throws InterruptedException {

    ExecutorService executorService = Executors.newCachedThreadPool();

    final CountDownLatch countDownLatch = new CountDownLatch(clietnTotal);

    final Semaphore semaphore = new Semaphore(threadCount);
    //循环
    for(int i=0; i<clietnTotal;i++){
      //线程池调用
      executorService.execute(()->{
        try {
          //业务逻辑方法
          semaphore.acquire();
          add();
          semaphore.release();
        } catch (InterruptedException e) {
          log.error("exception",e);
        }finally {
          //执行完业务逻辑一定要执行 countDown方法 来-1
          //如果未调用则无法执行 下方的await()方法
          //内部使用CAS算法 调用CPU级别指令 安全高效
          countDownLatch.countDown();
        }
      });
    }
    countDownLatch.await();
    log.info("count={}",count);
    executorService.shutdown();

  }

  private static void add()  {
    lock.lock();
    try {
      count++;
    }finally {
      lock.unlock();
    }
  }



}
