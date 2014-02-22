package ouch.study.fpe.domain;

import ouch.study.fpe.domain.rule.FlapPatternEnumerator;

public interface EnumeratorFactory {

	FlapPatternEnumerator create();
}
