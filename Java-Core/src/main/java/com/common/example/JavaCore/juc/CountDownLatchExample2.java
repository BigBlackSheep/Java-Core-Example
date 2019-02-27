package com.common.example.JavaCore.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: Gz.
 * @Date: 2019/2/25 14:36
 * @Description:J.U.C 包下 CountDownLatch使用样例
 */
@Slf4j
public class CountDownLatchExample2 {

  private static final int threadCount = 200;

  public static void main(String[] args) throws InterruptedException {


    ExecutorService executorService = Executors.newCachedThreadPool();

    final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

    //循环
    for(int i=0; i<threadCount;i++){
      //给一个线程名称(生产中最好给一个有意义的名称)
      final int threadNum = i;
      //线程池调用
      executorService.execute(()->{
        try {
          //业务逻辑方法
          test(threadNum);
          log.info("count:{}",countDownLatch.getCount());
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
    //等待10毫秒时间 不管其他线程是否执行完毕 本线程继续向下执行,
    countDownLatch.await(10, TimeUnit.MILLISECONDS);
    log.info("finish");
    executorService.shutdown();

  }


  private static void test(int threadNum) throws InterruptedException {
    Thread.sleep(100);
    log.info("{}",threadNum);
    Thread.sleep(100);
  }



}
