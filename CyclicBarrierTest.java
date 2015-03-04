package def.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService service = Executors.newCachedThreadPool();
		final CyclicBarrier cb = new CyclicBarrier(3);
		
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep((long)(Math.random()*100));
						System.out.println("�߳�"+Thread.currentThread().getName()
								+"�������Ｏ�ϵص�1����ǰ����"+(cb.getNumberWaiting()+1)
								+(cb.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));
						cb.await();
						
						Thread.sleep((long)(Math.random()*100));
						System.out.println("�߳�"+Thread.currentThread().getName()
								+"�������Ｏ�ϵص�2����ǰ����"+(cb.getNumberWaiting()+1)
								+(cb.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));
						cb.await();

						Thread.sleep((long)(Math.random()*100));
						System.out.println("�߳�"+Thread.currentThread().getName()
								+"�������Ｏ�ϵص�3����ǰ����"+(cb.getNumberWaiting()+1)
								+(cb.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));
						cb.await();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
				}

			};
			service.execute(runnable);
		}
		service.shutdown();
	}

}
