package ouch.study.fpe.domain.rule;

import java.util.Collection;
import java.util.LinkedList;

import oripa.util.collection.AbstractRule;
import oripa.util.collection.Rule;

public class ConjunctionRule<Variable> extends AbstractRule<Variable> {

	private final Collection<Rule<Variable>> rules = new LinkedList<>();

	/**
	 * returns always true.
	 */
	public ConjunctionRule() {
	}

	public ConjunctionRule(Collection<Rule<Variable>> rules) {
		this.rules.addAll(rules);
	}

	public ConjunctionRule(Rule<Variable> rule) {
		this.rules.add(rule);
	}

	public void addRule(Rule<Variable> r) {
		rules.add(r);
	}

	public void addAllRules(Collection<Rule<Variable>> r) {
		rules.addAll(r);
	}

	@Override
	public boolean holds(Variable var) {
		for (Rule<Variable> rule : rules) {
			if (rule.violates(var)) {
				return false;
			}
		}

		return true;
	}
}
