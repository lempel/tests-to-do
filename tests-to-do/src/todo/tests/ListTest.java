package todo.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            iter.next();

            if ((int) (Math.random() * 10) % 2 == 0) {
                iter.remove();
            }
        }

        System.out.println(list.size());
    }
}
