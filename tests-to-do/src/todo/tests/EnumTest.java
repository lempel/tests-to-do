package todo.tests;

public class EnumTest {
    public static void main(String[] args) {
        new EnumTest().test1();
    }

    private void test1() {
        test2(EnumType.values(), "AA");
        test2(EnumType.values(), "BB");
        test2(EnumType.values(), "DD");

        try {
            System.out.println(EnumType.valueOf("AA"));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        try {
            System.out.println(EnumType.valueOf("DD"));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        try {
            System.out.println(Enum.valueOf(EnumType.class, "AA"));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        try {
            System.out.println(Enum.valueOf(EnumType.class, "DD"));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        System.out.println(EnumType.class.getName());
    }

    private void test2(Enum<?>[] enumTypes, String target) {
        for (Enum<?> enumType : enumTypes) {
            if (enumType.toString().equals(target)) {
                System.out.println(target + " found");
            }
        }
    }

    public enum EnumType {
        AA, BB, CC
    }
}
