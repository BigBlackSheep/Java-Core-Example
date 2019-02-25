package com.common.example.JavaCore.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: Gz.
 * @Date: 2019/2/25 14:36
 * @Description:J.U.C 包下 CountDownLatch使用样例
 */
public class CountDownLatchExample {

  private static final int size = 200;

  public static void main(String[] args) {
    CountDownLatch countDownLatch = new CountDownLatch(size);
  }

}
