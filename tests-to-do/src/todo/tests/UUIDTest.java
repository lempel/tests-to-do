package todo.tests;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class UUIDTest extends Thread {
    private static final Map<String, String> map = new HashMap<>();
    private static final AtomicInteger cnt = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new UUIDTest().start();
        }
    }

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
