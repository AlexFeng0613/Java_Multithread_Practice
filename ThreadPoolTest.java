package def.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ExecutorService threadPool = Executors.newFixedThreadPool(3);
		//ExecutorService threadPool = Executors.newCachedThreadPool();
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
				
		for(int i=1;i<=10;i++){
			final int task = i;
			threadPool.execute(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int j=1;j<=10;j++){
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " is looping of "+j+" for task "+task);
					
				}
			}
			
		});
		}
		System.out.println("all of 10 tasks have been submitted");
		//threadPool.shutdown();
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(
				new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("Booming");
					}
					
				}
				, 10, 
				2, TimeUnit.SECONDS);
	}

}
