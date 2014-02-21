package ouch.study.fpe.domain.bf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ouch.study.fpe.domain.EnumeratorFactory;
import ouch.study.fpe.domain.FlapPatternEnumerator;

@Component
@Qualifier("bruteForce")
public class BruteForceEnumeratorFactory implements EnumeratorFactory {

	@Override
	public FlapPatternEnumerator create() {
		return new BruteForceFlapPatternEnumerator();
	}

}
