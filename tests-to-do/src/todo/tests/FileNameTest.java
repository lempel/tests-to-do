package todo.tests;

import java.io.File;
import java.io.IOException;

public class FileNameTest {
	public static void main(String[] args) throws IOException {
		File test = new File("src/todo/tests/FileNameTest.java");
		System.out.println("getName() = " + test.getName());
		System.out.println("getPath() = " + test.getPath());
		System.out.println("getAbsolutePath() = " + test.getAbsolutePath());
		System.out.println("getCanonicalPath() = " + test.getCanonicalPath());
	}
}
