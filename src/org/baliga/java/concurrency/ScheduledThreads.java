package org.baliga.java.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreads {
	private ScheduledExecutorService scheduledExecutorService;
	private Future<?> task;
	static public void main(String[] args) {
		ScheduledThreads p = new ScheduledThreads();
		p.run();
		p.waitForWorker();
		p.runAtFixedRate();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			
		}
		System.out.println( "Sending cancel signal to the task");
		p.cancelWorker();
		p.waitForWorker();
		p.shutdownPoll();
	}
	
	public ScheduledThreads() {
		scheduledExecutorService = Executors.newScheduledThreadPool(2);
		
	}
	
	public void run() {
		System.out.println( "Scheduling a thread to run after 1 second");
		task = scheduledExecutorService.schedule(new Worker(), 2000, TimeUnit.MILLISECONDS);
	}
	
	public void runAtFixedRate() {
		System.out.println( "Scheduling a thread like cron job. Run the task at every 1 second");
		task = scheduledExecutorService.scheduleAtFixedRate(new Worker(), 0, 1000, TimeUnit.MILLISECONDS);
	}
	
	public void cancelWorker() {
		task.cancel(false);
	}
	
	public void waitForWorker() {
		System.out.println( "Waiting for worker to finish the work");
		while (!task.isDone()) {
		}
		System.out.println( "Worker finished the job");
	}
	
	public void shutdownPoll() {
		System.out.println("Waiting for pool shutdown");
		scheduledExecutorService.shutdown();
		while (!scheduledExecutorService.isShutdown()) {
		}
		System.out.print("Pool shutdown successful");
	}

	class Worker implements Runnable {
		public void run() {
			System.out.println( "Hello world from within Scheduled Thread");
		}
	}
}
