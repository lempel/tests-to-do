package todo.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import blueprint.sdk.util.stream.StreamExhauster;

public class PropertiesTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		Properties props = System.getProperties();
		Set<Object> keys = props.keySet();

		for (Object key : keys) {
			System.out.println(key + " = " + props.getProperty(key.toString()));
		}

		String cmdStr = "javac -version";

		List<String> cmd = new ArrayList<String>();
		StringTokenizer stk = new StringTokenizer(cmdStr, "\u0020\u0009");
		while (stk.hasMoreTokens()) {
			cmd.add(stk.nextToken());
		}

		ProcessBuilder pb = new ProcessBuilder(cmd);
		pb.directory(new File(System.getProperty("user.home")));

		Process proc = pb.start();

		final Thread ex1 = new StreamExhauster(proc.getErrorStream(), System.out);
		final Thread ex2 = new StreamExhauster(proc.getInputStream(), System.out);
		ex1.start();
		ex2.start();

		proc.waitFor();
	}
}
