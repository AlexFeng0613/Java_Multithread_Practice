package def.test;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ReadWriteLockTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue3 q3 = new Queue3();
		for(int i=0;i<3;i++){
			
			new Thread(){
					public void run(){
						while(true){
							q3.get();
						}
					}
			}.start();
			
			new Thread(){
					public void run(){
						while(true){
							q3.put(new Random().nextInt(10000));
						}
					}
			}.start();
			
		}
	}	
}

class Queue3{
	private Object data = null;
	//private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	ReadWriteLock rwl = new ReentrantReadWriteLock(); 
	
	public void get() {
		// TODO Auto-generated method stub
		//rwl.readLock().lock();
		
		rwl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+" be ready to read data!");
			Thread.sleep((long)(Math.random()*1000));
			System.out.println(Thread.currentThread().getName()+" have read data :"+data);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			rwl.readLock().unlock();
		}

		//rwl.readLock().unlock();
	}
	
	public void put(Object data) {
		// TODO Auto-generated method stub

		rwl.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+" be ready to write data!");
			Thread.sleep((long)(Math.random()*1000));
			this.data=data;
			System.out.println(Thread.currentThread().getName()+" have written data:"+data);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			rwl.writeLock().unlock();
		}
		
		//rwl.writeLock().unlock();
	}

}