import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class CarQueue {

	Queue<Integer> carQueue;
	Random random;
	
	public CarQueue() {
		
		carQueue = new ArrayDeque<Integer>();
		random = new Random();
		carQueue.add(random.nextInt(4));
		carQueue.add(random.nextInt(4));
		carQueue.add(random.nextInt(4));
		carQueue.add(random.nextInt(4));
		carQueue.add(random.nextInt(4));
	}
	
	public Integer deleteQueue() {
		return carQueue.remove();
	}
	
	public void addToQueue() {
		class QueueRunnable implements Runnable {
			@Override
			public void run() {
				try {
					while (true) {
						carQueue.add(random.nextInt(4));
						Thread.sleep(200);
					}
				} catch (InterruptedException exception) {
					// uhh
				} finally {
					// uhh
				}
			}
		}
		
		Runnable runnable = new QueueRunnable();
		Thread thread = new Thread(runnable);
		thread.start();
		
	}
}
