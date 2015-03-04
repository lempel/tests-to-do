package todo.tests;

import java.util.HashMap;
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

        test3();
    }

    private static void test1() {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put(i, i);
        }

        for (Integer key : map.keySet()) {
            if ((int) (Math.random() * 10) % 2 == 0) {
                map.remove(key);
            }
        }

        Set<Integer> keyset = map.keySet();
        for (int key : keyset) {
            if ((int) (Math.random() * 10) % 2 == 0) {
                map.remove(key);
            }
        }

        System.out.println(map.size());
    }

    private static void test2() {
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put(i, i);
        }

        for (Integer key : map.keySet()) {
            if ((int) (Math.random() * 10) % 2 == 0) {
                map.remove(key);
            }
        }

        Set<Integer> keyset = map.keySet();
        for (int key : keyset) {
            if ((int) (Math.random() * 10) % 2 == 0) {
                map.remove(key);
            }
        }

        System.out.println(map.size());
    }

    private static void test3() {
        Map<String[], String> map = new HashMap<>();

        String[] arr1 = new String[]{"a", "b", "c"};
        String[] arr2 = new String[]{"a", "b", "c"};
        String[] arr3 = new String[]{"a", "b"};

        map.put(arr1, "arr1");

        System.out.println(map.get(arr1));
        System.out.println(map.get(arr2));
        System.out.println(map.get(arr3));
    }
}
