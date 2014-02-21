package ouch.study.fpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ouch.study.fpe.domain.EnumeratorFactory;

@Component
@Configurable
public class EnumerationRunnerFactory {

	@Autowired
	@Qualifier("puzzling")
	private EnumeratorFactory puzzlingFactory;

	@Autowired
	@Qualifier("bruteForce")
	private EnumeratorFactory bruteForceFactory;

	public EnumerationRunner createBruteForceRunner(
			final IntegerGettable divisionSizeHolder,
			final FlapPatternsSettable patternHolder) {

		if (bruteForceFactory == null) {
			throw new IllegalStateException("DI failed.");
		}

		return new EnumerationRunnerTemplate(bruteForceFactory.create(),
				divisionSizeHolder, patternHolder);
	}

	public EnumerationRunner createPuzzlingRunner(
			final IntegerGettable divisionSizeHolder,
			final FlapPatternsSettable patternHolder) {

		return new EnumerationRunnerTemplate(puzzlingFactory.create(),
				divisionSizeHolder, patternHolder);

	}
}
