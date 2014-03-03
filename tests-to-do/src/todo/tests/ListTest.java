package todo.tests;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class ListTest {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10000; i++) {
			list.add(i);
		}

		synchronized (list) {
			Iterator<Integer> iter = list.iterator();
			while (iter.hasNext()) {
				@SuppressWarnings("unused")
				int val = iter.next();
				
				if ((int)(Math.random() * 10) % 2 == 0) {
					iter.remove();
				}
			}
		}

		System.out.println(list.size());
	}
}
