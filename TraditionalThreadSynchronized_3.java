package def.test;

public class TraditionalThreadSynchronized_3 {
	public static void main(String[] args){
		new TraditionalThreadSynchronized_3().init();
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
					out.output3("DEMO CASE");
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
		public void output(String name){
			int length = name.length();
			synchronized(Outputer.class)
			{
				for(int i=0;i<length;i++){
					System.out.print(name.charAt(i));
				}
				System.out.println("");
			}
		}
		

		public synchronized void output2(String name){
			int length = name.length();
			for(int i=0;i<length;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println("");
			
		}
		
		public static synchronized void output3(String name){
			int length = name.length();
			for(int i=0;i<length;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println("");
			
		}
	}
}
