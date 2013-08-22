package todo.tests;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class UUIDTest extends Thread {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new UUIDTest().start();
		}
	}

	private static Map<String, String> map = new HashMap<String, String>();
	private static AtomicInteger cnt = new AtomicInteger(0);

	public void run() {
		for (int i = 0; i < 10000; i++) {
			String id = UUID.randomUUID().toString();
			String old = map.put(id, id);
			if (old != null) {
				System.out.println("dupe - " + cnt.incrementAndGet());
			}
		}
	}
}
