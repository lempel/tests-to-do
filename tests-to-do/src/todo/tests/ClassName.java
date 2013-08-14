package todo.tests;

public class ClassName {
	public static void main(String[] args) {
		ClassA a = new ClassA();
		ClassB b = new ClassB();
		ClassC c = new ClassC();

		System.out.println(a.getName());
		System.out.println(b.getName());
		System.out.println(c.getName());
	}
}

class ClassA extends Thread {
	public ClassA() {
		super();
		setName(getClass().getSimpleName() + "#" + hashCode());
	}
}

class ClassB extends ClassA {
	public ClassB() {
		super();
		setName(getClass().getSimpleName() + "#" + hashCode());
	}
}

class ClassC extends ClassA {
	public ClassC() {
		super();
	}
}