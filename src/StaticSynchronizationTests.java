import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class StaticSynchronizationTests {
	private static int count;
	private ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {
		if (false) { // Class synchronization and a deadlock
			ExecutorService x = Executors.newFixedThreadPool(32);
			System.out.println(StaticSynchronizationTests.count);
			for (int y = 0; y <= 64; y++) {
				x.submit(new StaticSynchronizationTests()::incrementAndDeadlock);
			}
			System.out.println(StaticSynchronizationTests.count);
			x.shutdown();
		}
		if (!false) { // Reentrant locking
			ExecutorService x = Executors.newFixedThreadPool(32);
			System.out.println(StaticSynchronizationTests.count);
			for (int y = 0; y <= 64; y++) {
				x.submit(new StaticSynchronizationTests()::incrementReentrant);
			}
			System.out.println(StaticSynchronizationTests.count);
			x.shutdown();
		}
	}
		
	public void incrementAndDeadlock() {
		synchronized(StaticSynchronizationTests.class) {
			count++;
		}
	}

	public void incrementReentrant() {
		try {
			while (lock.tryLock(1, TimeUnit.SECONDS)) {
				if (count >= 64) return;
				count++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
