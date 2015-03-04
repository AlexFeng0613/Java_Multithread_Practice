package def.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final BlockingQueue queue = new ArrayBlockingQueue(6);
		for(int i=0;i<2;i++){
			new Thread(){
					public void run(){
						while(true){
							try {
								Thread.sleep((long)(Math.random()*1000));
								System.out.println(Thread.currentThread().getName() 
										+ "׼��������!");						
								queue.put(1);
								System.out.println(Thread.currentThread().getName() 
										+ "�Ѿ��������ݣ�" + 							
										"����Ŀǰ��" + queue.size() + "������");
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
			}.start();
		}
		
		new Thread(){
			public void run(){
				while(true){
					try {
						Thread.sleep((long)(Math.random()*1000));
						System.out.println(Thread.currentThread().getName() 
								+ "׼��ȡ����!");						
						queue.take();
						System.out.println(Thread.currentThread().getName() 
								+ "�Ѿ�ȡ�����ݣ�" + 							
								"����Ŀǰ��" + queue.size() + "������");
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}.start();
	}

}
