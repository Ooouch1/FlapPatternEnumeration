package ouch.study.fpe.domain;

import java.util.Collection;
import java.util.List;

public interface FlapPatternEnumerator {
	Collection<AngleUnitFlapPattern> enumerateUniquePatterns(int divisionSize,
			Collection<List<LineType>> partialPatterns);
}
