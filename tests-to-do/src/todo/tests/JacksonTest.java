package todo.tests;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {
	public static void main(String[] args) {
		int items = 10000;
		int repeat = 1000;

		ObjectMapper mapper = new ObjectMapper();
		Map<Integer, String> map = new HashMap<Integer, String>();
		String message = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";

		long start = System.currentTimeMillis();
		for (int i = 0; i < repeat; i++) {
			for (int j = 0; j < items; j++) {
				map.put(j, message);
			}

			// String json = "";
			try {
				// json = mapper.writeValueAsString(map);
				mapper.writeValue(new File("e:\\json.txt"), map);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				@SuppressWarnings("rawtypes")
				HashMap newMap = null;
				// newMap = mapper.readValue(json, HashMap.class);
				newMap = mapper.readValue(new File("e:\\json.txt"), HashMap.class);
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
}
