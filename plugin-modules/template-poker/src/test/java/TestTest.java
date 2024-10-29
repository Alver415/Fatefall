import javafx.beans.property.SimpleStringProperty;

import java.util.concurrent.atomic.AtomicInteger;

public class TestTest {

	public static void main(String... args) throws Exception {
		new TestTest().test();
	}

	public void test() throws Exception {

		AtomicInteger a = new AtomicInteger();
		AtomicInteger b = new AtomicInteger();
		AtomicInteger c = new AtomicInteger();
		AtomicInteger d = new AtomicInteger();
		AtomicInteger e = new AtomicInteger();
		new SimpleStringProperty("")
				.map(z -> print("a" + a.getAndIncrement()))
				.map(z -> print("b" + b.getAndIncrement()))
				.map(z -> print("c" + c.getAndIncrement()))
				.map(z -> print("d" + d.getAndIncrement()))
				.subscribe(z -> print("e" + e.getAndIncrement()));

//		ObjectProperty<StringProperty> p = new SimpleObjectProperty<>(this, "object");
//		p.setValue(new SimpleStringProperty(this, "string", "example"));
//
//		p.subscribe(this::print);
//		p.map(StringExpression::getValue).subscribe(this::print);
//		p.flatMap(a -> a).subscribe(this::print);
//
//		print("-----");
//
//		p.get().set("new string");
//
//		print("-----");
//
//		p.set(new SimpleStringProperty(this,"another", "final"));

	}

	private <T> T print(T o) {
		System.out.println(o);
		return o;
	}
}
