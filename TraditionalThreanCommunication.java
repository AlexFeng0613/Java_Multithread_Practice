package def.test;

public class TraditionalThreanCommunication {

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
}

class Business{
	
	private boolean bShouldSub = true;
	public synchronized void sub(int j){
		while(!bShouldSub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0;i<10;i++){
			System.out.println("sub thread sequence of "+i+" , loop of " +j);
		}
		bShouldSub=false;
		this.notify();
	}
	
	public synchronized void main(int j){
		while(bShouldSub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0;i<100;i++){
			System.out.println("main thread sequence of "+i+" , loop of " +j);
		}
		bShouldSub=true;		
		this.notify();
	}
}