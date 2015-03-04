package def.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	public static void main(String[] args){
		new LockTest().init();
	}
	
	private void init(){
		final Outputer out = new Outputer();
		new Thread(new Runnable(){
			public void run(){
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.output("DEMO CASE");
				}
			}
		}).start();
		
		new Thread(new Runnable(){
			public void run(){
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.output("DEMO CASE2");
				}
			}
		}).start();
		
	}
	
	static class Outputer{
		Lock lock = new ReentrantLock();
		public void output(String name){
			int length = name.length();
			lock.lock();
			try{
				for(int i=0;i<length;i++){
					System.out.print(name.charAt(i));
				}
				System.out.println("");				
			}
			finally{
				lock.unlock();
			}
			
		}
		
	}
}
