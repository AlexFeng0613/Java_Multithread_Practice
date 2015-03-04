package def.test;

public class TraditionalThread2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread = new Thread(){
			public void run(){
				while(true){
					try{
						Thread.sleep(500);
						
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					System.out.println("1: " + Thread.currentThread().getName());
					System.out.println("2: " + this.getName());
				}
			}
		};
		
		thread.start();
		
		Thread thread2 = new Thread(new Runnable(){
			public void run(){
				while(true){
					try{
						Thread.sleep(500);
						
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					System.out.println("1: " + Thread.currentThread().getName());
					
				}
			}
		});
		
		thread2.start();
	}

}
