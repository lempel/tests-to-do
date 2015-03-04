package todo.tests;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ConcurrentFileIOTest extends Thread {
    private static final CountDownLatch latch = new CountDownLatch(4);
    private final String my;

    private ConcurrentFileIOTest() {
        StringBuilder bld = new StringBuilder();
        bld.append(hashCode()).append("     ");
        for (int i = 0; i < 500; i++) {
            String base = "1234567890";
            bld.append(base);
        }
        my = bld.toString();
    }

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("test.txt");

        ConcurrentFileIOTest t1 = new ConcurrentFileIOTest();
        ConcurrentFileIOTest t2 = new ConcurrentFileIOTest();
        ConcurrentFileIOTest t3 = new ConcurrentFileIOTest();
        ConcurrentFileIOTest t4 = new ConcurrentFileIOTest();

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fos.close();
    }

    public void run() {
        for (int i = 0; i < 200; i++) {
            try {
                FileOutputStream fos = new FileOutputStream("test.txt");
                fos.write(my.getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        latch.countDown();
    }
}
