package def.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreeConditionCommunication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final Business business = new Business();
		
		new Thread(
				new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						for(int i=0;i<50;i++){
							business.sub2(i);
						}
					}
					
				}
		).start();

		new Thread(
				new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						for(int i=0;i<50;i++){
							business.sub3(i);
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
		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		Condition condition3 = lock.newCondition();
		private int bShouldSub = 1;
		
		public  void sub2(int j){
			lock.lock();
			try{
				while(bShouldSub!=2){
					try{
						condition2.await();
					}
					catch(Exception e){
						
					}
				}
				for(int i=0;i<10;i++){
					System.out.println("sub2 thread sequence of "+i+" , loop of " +j);
				}
				bShouldSub=3;
				condition3.signal();
			}finally{
				lock.unlock();
			}
		}
		
		public  void sub3(int j){
			lock.lock();
			try{
				while(bShouldSub!=3){
					try{
						condition3.await();
					}
					catch(Exception e){
						
					}
				}
				for(int i=0;i<20;i++){
					System.out.println("sub3 thread sequence of "+i+" , loop of " +j);
				}
				bShouldSub=1;
				condition1.signal();
			}finally{
				lock.unlock();
			}
		}
		
		public  void main(int j){
			lock.lock();
			try{
				while(bShouldSub!=1){
					try {
						condition1.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for(int i=0;i<100;i++){
					System.out.println("main thread sequence of "+i+" , loop of " +j);
				}
				bShouldSub=2;		
				condition2.signal();
			
			} finally{
				lock.unlock();
			}
		}
	}
}
