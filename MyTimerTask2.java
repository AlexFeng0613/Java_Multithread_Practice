package def.test;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask2 extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Booming2");
		new Timer().schedule(new MyTimerTask1(),5000);
	}

}
