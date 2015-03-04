package def.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueCommunication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final Business business = new Business();
		
		new Thread(
				new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						for(int i=0;i<50;i++){
							business.sub(i);
						}
					}					
				}
		).start();
				
		for(int j=0;j<50;j++){
			business.main(j);
		}	
		
	}
	

	static class Business{
		BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
		BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);
		
		 {
			try {
				queue2.put(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private boolean bShouldSub = true;
		public  void sub(int j){
			try {
				queue1.put(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<10;i++){
				System.out.println("sub thread sequence of "+i+" , loop of " +j);
			}
			try {
				queue2.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		public  void main(int j){
			try {
				queue2.put(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<100;i++){
				System.out.println("main thread sequence of "+i+" , loop of " +j);
			}
			try {
				queue1.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
}
