package ouch.study.fpe.domain.rule;

import oripa.util.collection.AbstractRule;

@SuppressWarnings("rawtypes")
public class AlwaysTrue extends AbstractRule {

	@Override
	public boolean holds(Object var) {
		return true;
	}
}
