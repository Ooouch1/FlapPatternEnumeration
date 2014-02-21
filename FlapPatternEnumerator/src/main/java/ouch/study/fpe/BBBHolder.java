package ouch.study.fpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class BBBHolder {
	@Autowired
	BBB b;

	public BBB get() {
		return b;
	}
}
