package todo.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import blueprint.sdk.core.filesystem.CachedFileSystem;

public class CachedFileSystemTest extends CachedFileSystem {
	private static final int TTL = 10 * 60 * 1000;

	private static final int WORK_DURATION = 60 * 1000;
	private static final int IDLE_DURATION = 10 * 1000;

	public CachedFileSystemTest(long ttl) {
		super(ttl);
	}

	@Override
	public boolean deleteFile(String path) {
		boolean result = super.deleteFile(path);
		return result;
	}

	/** boom! */
	@Override
	public boolean renameFile(String orgPath, String newPath) {
		boolean result = super.renameFile(orgPath, newPath);
		return result;
	}

	@Override
	public byte[] readFile(String path) throws IOException {
		byte[] result = super.readFile(path);
		return result;
	}

	@Override
	public void writeToFile(String path, byte[] contents, boolean append) throws IOException {
		super.writeToFile(path, contents, append);
	}

	public static void main(String[] args) {
		CachedFileSystemTest cfst = new CachedFileSystemTest(TTL);

		for (int i = 0; i < 100; i++) {
			new TestThread(cfst, WORK_DURATION, IDLE_DURATION).start();
		}
	}
}

class TestThread extends Thread {
	private static final String PATH1 = "10mb.txt";
	private static final String PATH2 = "10mb.org.txt";

	private static boolean verbose = true;
	private static PrintStream out;

	private static byte[] contents_10mb;

	static {
		out = System.out;

		contents_10mb = new byte[10 * 1024 * 1024];
		for (int i = 0; i < contents_10mb.length; i++) {
			contents_10mb[i] = 'A';
		}
	}

	CachedFileSystemTest fs;

	long work;
	long idle;

	public TestThread(CachedFileSystemTest fs, long work, long idle) {
		this.fs = fs;
		this.work = work;
		this.idle = idle;
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
			} catch (IOException e) {
				out.println(hashCode() + " - " + e);
				e.printStackTrace(out);
				System.exit(100);
			}

			if (verbose)
				out.println(hashCode() + " delte 10mb.org");
			fs.deleteFile(PATH2);
			if (verbose)
				out.println(hashCode() + " delte 10mb.org - ok");

			if (verbose)
				out.println(hashCode() + " rename 10mb > 10mb.org");
			fs.renameFile(PATH1, PATH2);
			if (verbose)
				out.println(hashCode() + " rename 10mb > 10mb.org - ok");

			try {
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
				for (int i = 0; i < contents_10mb.length; i++) {
					if (data[i] != contents_10mb[i]) {
						throw new IOException("mismatched read data. offset=" + i);
					}
				}
			} catch (FileNotFoundException ignored) {
				// can happen naturally
			} catch (Exception e) {
				out.println(hashCode() + " - " + e);
				e.printStackTrace(out);
				System.exit(200);
			}
		}
	}
}
