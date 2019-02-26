package com.common.example.JavaCore.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Auther: Gz.
 * @Date: 2019/2/25 14:36
 * @Description:J.U.C 包下 信号量使用样例
 *
 */
@Slf4j
public class SemaphoreExample {

  private static final int threadCount = 20;

  public static void main(String[] args) throws InterruptedException {


    ExecutorService executorService = Executors.newCachedThreadPool();

    // 信号量初始化 并发数量 即最大并发就是3
    final Semaphore semaphore = new Semaphore(3);

    /**
     * 如果超过并发的数量只能在外面等待
     * 有释放一个许可的时候 才能再获取下一个许可
     */
    for(int i=0; i<threadCount;i++){
      final int threadNum = i;
      executorService.execute(()->{
        try {
          semaphore.acquire();//获取一个许可
          test(threadNum);
          semaphore.release();//释放一个许可
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
