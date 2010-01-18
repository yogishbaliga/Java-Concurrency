package org.baliga.java.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class CachedThreads {

  static public void main(String[] args) {
    ExecutorService cachePool = Executors.newCachedThreadPool();

    Future<?> task = cachePool.submit( new Runnable() { public void run() {
                System.out.println( "Hello world from within the cached pool thread" );
              }});
  
    System.out.println( "Waiting for task completion" );
    while ( !task.isDone() ) {
    }
    System.out.println( "Task is completed" );

    cachePool.shutdown();

    System.out.println( "Waiting for pool shutdown" );
    while ( !cachePool.isTerminated() ) {
    }
    System.out.println( "Pool shutdown successful" );
  }
}

