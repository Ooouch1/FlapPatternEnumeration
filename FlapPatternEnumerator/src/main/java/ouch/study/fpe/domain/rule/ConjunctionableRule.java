package ouch.study.fpe.domain.rule;

import java.util.Collection;
import java.util.LinkedList;

import oripa.util.collection.AbstractRule;
import oripa.util.collection.Rule;

public class ConjunctionableRule<Variable> extends AbstractRule<Variable> {

	private final Collection<Rule<Variable>> rules = new LinkedList<>();

	/**
	 * returns always true.
	 */
	public ConjunctionableRule() {
	}

	public ConjunctionableRule(final Collection<Rule<Variable>> rules) {
		this.rules.addAll(rules);
	}

	public ConjunctionableRule(final Rule<Variable> rule) {
		this.rules.add(rule);
	}

	/**
	 * 
	 * @param r
	 * @return
	 *         this instance after adding r.
	 */
	public ConjunctionableRule<Variable> addRule(final Rule<Variable> r) {
		rules.add(r);
		return this;
	}

	public void addAllRules(final Collection<Rule<Variable>> r) {
		rules.addAll(r);
	}

	@Override
	public boolean holds(final Variable var) {
		for (Rule<Variable> rule : rules) {
			if (rule.violates(var)) {
				return false;
			}
		}

		return true;
	}
}
