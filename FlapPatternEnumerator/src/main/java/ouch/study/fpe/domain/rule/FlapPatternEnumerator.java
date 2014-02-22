package ouch.study.fpe.domain.rule;

import java.util.Collection;
import java.util.List;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.LineType;

public interface FlapPatternEnumerator {
	Collection<AngleUnitFlapPattern> enumerateUniquePatterns(int divisionSize,
			Collection<List<LineType>> partialPatterns);
}
