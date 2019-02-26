package com.common.example.JavaCore.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: Gz.
 * @Date: 2019/2/25 14:36
 * @Description:J.U.C 包下 CountDownLatch使用样例
 */
@Slf4j
public class SemaphoreExample3 {

  private static final int threadCount = 20;

  public static void main(String[] args) throws InterruptedException {


    ExecutorService executorService = Executors.newCachedThreadPool();

    //并发数量
    final Semaphore semaphore = new Semaphore(3);



    for(int i=0; i<threadCount;i++){
      final int threadNum = i;
      executorService.execute(()->{
        try {
          //只执行5秒内的 其他的全丢弃
          if(semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)){

            test(threadNum);
            semaphore.release();//释放一个许可
          } 
        } catch (InterruptedException e) {
          log.error("exception",e);
        }finally {
        }
      });
    }
    log.info("finish");
    executorService.shutdown();

  }


  private static void test(int threadNum) throws InterruptedException {
    log.info("{}",threadNum);
    Thread.sleep(3000);
  }



}
