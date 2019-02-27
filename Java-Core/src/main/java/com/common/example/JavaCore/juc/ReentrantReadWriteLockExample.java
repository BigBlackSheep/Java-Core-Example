package com.common.example.JavaCore.juc;

import lombok.extern.slf4j.Slf4j;

import java.io.StringReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther: Gz.
 * @Date: 2019/2/25 14:36
 * @Description:J.U.C 包下 ReetrantLock使用样例
 */
@Slf4j
public class ReentrantReadWriteLockExample {


  //初始化锁

  private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  private final static Lock readLock = lock.readLock();

  private final static Lock writeLock = lock.writeLock();

  private final Map<String,Data> map = new TreeMap<>();

  public Data get(String key){
    readLock.lock();
    try {
      return map.get(key);
    }finally {
      readLock.unlock();
    }
  }
  public Set<String> getAllKeys(){
    readLock.lock();
    try {
      return map.keySet();
    }finally {
      readLock.unlock();
    }
  }
  public Data put(String key,Data value) {
    writeLock.lock();
    try {
      return map.put(key,value);
  }finally {
      writeLock.unlock();
    }
  }
  class Data{

  }


}
