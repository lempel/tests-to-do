package todo.tests;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
	public static void main(String[] args) {
		try {
			test1();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			test2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void test1() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < 1000; i++) {
			map.put(i, i);
		}

		synchronized (map) {
			Iterator<Integer> keyiter = map.keySet().iterator();
			while (keyiter.hasNext()) {
				int key = keyiter.next();
				if ((int) (Math.random() * 10) % 2 == 0) {
					map.remove(key);
				}
			}
		}

		synchronized (map) {
			Set<Integer> keyset = map.keySet();
			for (int key : keyset) {
				if ((int) (Math.random() * 10) % 2 == 0) {
					map.remove(key);
				}
			}
		}

		System.out.println(map.size());
	}

	private static void test2() {
		Map<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
		for (int i = 0; i < 1000; i++) {
			map.put(i, i);
		}

		synchronized (map) {
			Iterator<Integer> keyiter = map.keySet().iterator();
			while (keyiter.hasNext()) {
				int key = keyiter.next();
				if ((int) (Math.random() * 10) % 2 == 0) {
					map.remove(key);
				}
			}
		}

		synchronized (map) {
			Set<Integer> keyset = map.keySet();
			for (int key : keyset) {
				if ((int) (Math.random() * 10) % 2 == 0) {
					map.remove(key);
				}
			}
		}

		System.out.println(map.size());
	}
}
