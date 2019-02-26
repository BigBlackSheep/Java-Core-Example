package com.common.example.JavaCore.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: Gz.
 * @Date: 2019/2/25 14:36
 * @Description:J.U.C 包下 CountDownLatch使用样例
 */
@Slf4j
public class CountDownLatchExample {

  //
  private static final int threadCount = 200;

  public static void main(String[] args) throws InterruptedException {


    ExecutorService executorService = Executors.newCachedThreadPool();

    final CountDownLatch countDownLatch = new CountDownLatch(threadCount);


    for(int i=0; i<threadCount;i++){
      final int threadNum = i;
      executorService.execute(()->{
        try {
          test(threadNum);
          log.info("count:{}",countDownLatch.getCount());
        } catch (InterruptedException e) {
          log.error("exception",e);
        }finally {
          countDownLatch.countDown();
        }
      });
    }
    countDownLatch.await();
    log.info("finish");
    executorService.shutdown();

  }


  private static void test(int threadNum) throws InterruptedException {
    Thread.sleep(100);
    log.info("{}",threadNum);
    Thread.sleep(100);
  }



}
