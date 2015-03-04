package todo.tests;

import blueprint.sdk.core.filesystem.CachedFileSystem;

import java.io.IOException;
import java.io.PrintStream;

public class CachedFileSystemTest extends CachedFileSystem {
    private static final int TTL = 10 * 60 * 1000;

    private static final int WORK_DURATION = 60 * 1000;
    private static final int IDLE_DURATION = 10 * 1000;
    @SuppressWarnings("FieldCanBeLocal")
    private static final boolean verbose = true;
    private static final PrintStream out = System.out;

    @SuppressWarnings("SameParameterValue")
    private CachedFileSystemTest(long ttl) {
        super(ttl);
    }

    public static void main(String[] args) {
        new CachedFileSystemTest(TTL).test();
    }

    void test() {
        TestThread[] threads = new TestThread[20];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new TestThread(i, this, WORK_DURATION, IDLE_DURATION);
        }

        for (TestThread thread : threads) {
            thread.start();
        }
    }

    class TestThread extends Thread {
        private static final String PATH1 = "e:\\10mb.txt";
        private static final String PATH2 = "e:\\10mb.org.txt";
        final CachedFileSystemTest fs;
        final long work;
        final long idle;
        private final byte[] contents_10mb;

        @SuppressWarnings("SameParameterValue")
        public TestThread(int id, CachedFileSystemTest fs, long work, long idle) {
            this.fs = fs;
            this.work = work;
            this.idle = idle;

            setName(Integer.toString(id));
            byte[] proto = Integer.toString(id).getBytes();
            contents_10mb = new byte[10 * 1024 * 1024];
            for (int i = 0; i < contents_10mb.length; i += proto.length) {
                if (i + proto.length < contents_10mb.length) {
                    System.arraycopy(proto, 0, contents_10mb, i, proto.length);
                } else {
                    System.arraycopy(proto, 0, contents_10mb, i, contents_10mb.length - i);
                }

                contents_10mb[i] = proto[0];
            }
        }

        public void run() {
            long start = System.currentTimeMillis();

            while (true) {
                long now = System.currentTimeMillis();

                if (now >= start + work) {
                    System.out.println(hashCode() + " now waiting");

                    try {
                        sleep(idle);
                    } catch (InterruptedException ignored) {
                    }

                    System.out.println(hashCode() + " now working");

                    start = System.currentTimeMillis();
                }

                try {
                    if (verbose)
                        out.println(hashCode() + " write 10mb");
                    fs.writeToFile(PATH1, contents_10mb, false);
                    if (verbose)
                        out.println(hashCode() + " write 10mb - ok");

                    if (verbose)
                        out.println(hashCode() + " delete 10mb.org");
                    fs.deleteFile(PATH2);
                    if (verbose)
                        out.println(hashCode() + " delete 10mb.org - ok");

                    if (verbose)
                        out.println(hashCode() + " rename 10mb > 10mb.org");
                    fs.renameFile(PATH1, PATH2);
                    if (verbose)
                        out.println(hashCode() + " rename 10mb > 10mb.org - ok");

                    if (verbose)
                        out.println(hashCode() + " read 10mb.org");
                    byte[] data = fs.readFile(PATH2);
                    if (verbose)
                        out.println(hashCode() + " read 10mb.org - ok");

                    if (data == null) {
                        throw new NullPointerException("null file");
                    }
                    if (data.length != contents_10mb.length) {
                        throw new IOException("mismatched read size. data.length=" + data.length);
                    }
                } catch (IOException e) {
                    out.println(hashCode() + " - " + e);
                    e.printStackTrace(out);
                    System.exit(100);
                }
            }
        }
    }
}
