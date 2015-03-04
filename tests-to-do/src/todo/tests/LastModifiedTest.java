package todo.tests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LastModifiedTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        File f = new File("test.txt");
        FileOutputStream fos = new FileOutputStream(f);
        fos.close();

        System.out.println(f.lastModified());

        Thread.sleep(2000);

        fos = new FileOutputStream(f);
        fos.close();

        System.out.println(f.lastModified());
    }
}
