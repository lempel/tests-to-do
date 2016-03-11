package todo.tests;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lempel on 2016-01-14.
 */
public class ObjectStreamTest {
    public static void main(String[] args) throws IOException {
        final ServerSocket ssock = new ServerSocket(9999);

        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket sock = new Socket("localhost", 9999);
                    ObjectInputStream oin = new ObjectInputStream(sock.getInputStream());

                    while (true) {
                        System.out.println(oin.readObject().getClass());
                    }
                } catch (IOException e) {
                    e.printStackTrace(System.out);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace(System.out);
                }
            }
        });
        t0.start();

        while (true) {
            try {
                final Socket sock = ssock.accept();

                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ObjectOutputStream oout = new ObjectOutputStream(sock.getOutputStream());
                            oout.writeObject(new String[]{"a", "b", "c"});
                            oout.writeObject(new String[]{"a", "b", "c"});
                            oout.writeObject(new String[]{"a", "b", "c"});
                            oout.writeObject(new String[]{"a", "b", "c"});
                            sock.close();
                        } catch (IOException e) {
                            e.printStackTrace(System.out);
                        }
                    }
                });
                t1.start();
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}
