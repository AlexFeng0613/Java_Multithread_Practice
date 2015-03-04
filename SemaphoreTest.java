package def.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ExecutorService service = Executors.newCachedThreadPool();
		final  Semaphore sp = new Semaphore(3); 
		for(int i=0;i<10;i++){
			Runnable runnable = new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try{
						sp.acquire();
					}catch(Exception e){
						
					}
					System.out.println("�߳�"+Thread.currentThread().getName()+
							"���룬��ǰ����"+(3-sp.availablePermits())+"������");
					try{
						Thread.sleep((long)(Math.random()*10000));
					}catch(Exception e){
						
					}
					System.out.println("�߳�"+Thread.currentThread().getName()+
							"�����뿪");
					sp.release();
					System.out.println("�߳�"+Thread.currentThread().getName()+
							"���뿪����ǰ����"+(3-sp.availablePermits())+"������");
				}
				
			};
			service.execute(runnable);
		}
	}

}
