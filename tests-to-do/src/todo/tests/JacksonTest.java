package todo.tests;

import blueprint.sdk.util.map.KeyValueStore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JacksonTest {
    private static final int items = 10000;
    private static final int repeat = 100;
    private static final String jsonFileName = "json.txt";

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        ObjectMapper mapper = new ObjectMapper();
        Map<Integer, Object> map = new HashMap<>();
        String message = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";

        long start = System.currentTimeMillis();
        for (int i = 0; i < repeat; i++) {
            for (int j = 0; j < items; j++) {
                map.put(j, message);
            }

            try {
                mapper.writeValue(new File(jsonFileName), map);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                @SuppressWarnings("rawtypes")
                HashMap newMap = mapper.readValue(new File(jsonFileName), HashMap.class);
                if (newMap.size() != map.size()) {
                    throw new RuntimeException("Can't read properly");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();

        long total = end - start;
        long average = total / repeat;
        System.out.println("total = " + total + " msec");
        System.out.println("average = " + average + " msec");
    }

    private static void test2() {
        KeyValueStore<String> store = new KeyValueStore<>(jsonFileName);
        String message = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";

        long start = System.currentTimeMillis();
        for (int i = 0; i < repeat; i++) {
            for (int j = 0; j < items; j++) {
                store.put(Integer.toString(j), message);
            }

            try {
                store.save();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                store.load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();

        long total = end - start;
        long average = total / repeat;
        System.out.println("total = " + total + " msec");
        System.out.println("average = " + average + " msec");
    }
}
