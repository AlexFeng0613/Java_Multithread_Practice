package def.test;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask1 extends TimerTask {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Booming1");
		new Timer().schedule(new MyTimerTask2(),3000);
	}

}
