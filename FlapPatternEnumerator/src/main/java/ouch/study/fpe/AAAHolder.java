package ouch.study.fpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AAAHolder {
	private AAA a;

	@Autowired
	public void set(final AAA instance) {
		a = instance;
	}

	public AAA get() {
		return a;
	}
}
