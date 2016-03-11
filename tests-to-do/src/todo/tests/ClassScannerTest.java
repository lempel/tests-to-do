package todo.tests;

import blueprint.sdk.util.reflect.Crowbar;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by lempel on 2016-03-11.
 */
public class ClassScannerTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ClassLoader loader = ClassLoader.getSystemClassLoader();

        Field field1 = Crowbar.getField(ClassLoader.class, "classes");
        Vector<Class<?>> classes1 = (Vector<Class<?>>) field1.get(loader);
        for (Class<?> clazz : classes1) {
            System.out.println(clazz);
        }
        System.out.println("------------------------------------------");

        Field field2 = Crowbar.getField(ClassLoader.class, "packages");
        HashMap<String, Package> classes2 = (HashMap<String, Package>) field2.get(loader);
        for (String pkg : classes2.keySet()) {
            System.out.println(pkg);
        }
        System.out.println("------------------------------------------");
    }
}
