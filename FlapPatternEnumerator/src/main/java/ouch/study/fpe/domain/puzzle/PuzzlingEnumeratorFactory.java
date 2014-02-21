package ouch.study.fpe.domain.puzzle;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ouch.study.fpe.domain.EnumeratorFactory;
import ouch.study.fpe.domain.FlapPatternEnumerator;

@Component
@Qualifier("puzzling")
public class PuzzlingEnumeratorFactory implements EnumeratorFactory {

	@Override
	public FlapPatternEnumerator create() {
		return new PuzzlingFlapPatternEnumerator();
	}

}
