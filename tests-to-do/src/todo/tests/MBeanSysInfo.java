package todo.tests;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.StringTokenizer;

public class MBeanSysInfo {
	public static void main(String[] args) {
		RuntimeMXBean rm = ManagementFactory.getRuntimeMXBean();

		String name = rm.getName();
		System.out.println(name);

		System.out.println("--------------------------------------");

		StringTokenizer stk = new StringTokenizer(name, "@.;:");
		while (stk.hasMoreTokens()) {
			System.out.println(stk.nextToken());
		}

		System.out.println("--------------------------------------");

		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
		}
	}
}
