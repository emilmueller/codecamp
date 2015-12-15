package codecamp;

public class WaitThread extends Thread {
	private long millis;
	public WaitThread(long millis){
		this.millis=millis;
	}
	
	public void run(){
		long now = System.currentTimeMillis();
		boolean tick=false;
		while(System.currentTimeMillis()-now<=millis){
			tick = !tick;
		}
	}
}
