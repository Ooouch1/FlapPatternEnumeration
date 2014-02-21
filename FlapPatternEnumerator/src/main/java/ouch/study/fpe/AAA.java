package ouch.study.fpe;

import org.springframework.stereotype.Component;

@Component
public class AAA {
	public void test() {
		System.out.println("TESTTESTEST");
	}

	private BBBHolder holder = new BBBHolder();

	public void hello() {
		// BBBHolder holder = new BBBHolder();

		holder.get().hello();
	}
}
