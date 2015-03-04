package def.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionCommunication {

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
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		private boolean bShouldSub = true;
		
		public  void sub(int j){
			lock.lock();
			try{
				while(!bShouldSub){
					try{
						condition.await();
					}
					catch(Exception e){
						
					}
				}
//				while(!bShouldSub){
//					try {
//						int x = 3/1;
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
				for(int i=0;i<10;i++){
					System.out.println("sub thread sequence of "+i+" , loop of " +j);
				}
				bShouldSub=false;
				condition.signal();
			}finally{
				lock.unlock();
			}
		}
		
		public  void main(int j){
			lock.lock();
			try{
				while(bShouldSub){
					try {
						condition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
//				while(bShouldSub){
//					try {
//						//this.wait();
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
				for(int i=0;i<100;i++){
					System.out.println("main thread sequence of "+i+" , loop of " +j);
				}
				bShouldSub=true;		
				condition.signal();
			
			} finally{
				lock.unlock();
			}
		}
	}
}
