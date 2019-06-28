/**
 * 
 */
package com.swapnil.warekar.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author swapnilwarekar
 *
 */
public class TestThreadPool {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService executer = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			Runnable testThread = new TestThread(i);
			executer.execute(testThread);
		}
		executer.shutdown();
	}

}

class TestThread implements Runnable {
	
	private int count;
	
	public TestThread(int count) {
		this.count = count;
	}

	@Override
	public void run() {
		String currentThread = Thread.currentThread().getName();
		System.out.println(currentThread+"  Begin-"+count);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(currentThread+"  End");
	}
}
