package todo.tests;

import blueprint.sdk.util.queue.H2Queue;
import org.h2.jdbcx.JdbcDataSource;

import javax.xml.xpath.XPathExpressionException;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

public class H2QueueTest {
    public static void main(String[] args) throws XPathExpressionException, InstantiationException,
            IllegalAccessException, ClassNotFoundException, SQLException {
        JdbcDataSource datasrc = new JdbcDataSource();
        datasrc.setURL("jdbc:h2:data/h2-test");

        final H2Queue queue = new H2Queue(datasrc);
        queue.init();
        System.out.println("queue size = " + queue.size());

        final int threads = 2;

        for (int i = 0; i < 1000; i++) {
            final CountDownLatch latch = new CountDownLatch(threads * 2);
            long start = System.currentTimeMillis();

            for (int j = 0; j < threads; j++) {
                Thread t1 = new Thread() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 2000; i++) {
                            try {
                                queue.push(Integer.toString(new Object().hashCode()));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        latch.countDown();
                    }
                };
                t1.start();
            }

            for (int j = 0; j < threads; j++) {
                Thread t1 = new Thread() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 2000; i++) {
                            try {
                                queue.take();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        latch.countDown();
                    }
                };
                t1.start();
            }

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            queue.clear();

            long elapsed = System.currentTimeMillis() - start;
            System.out.println(i + " : " + elapsed + " msec => " + (elapsed / 2000) + " msec/item");
            System.out.println("queue size = " + queue.size());
        }
    }
}
